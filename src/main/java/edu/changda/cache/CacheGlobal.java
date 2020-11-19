package edu.changda.cache;

import java.util.concurrent.*;

/**
 * 全局缓存类
 *
 * @author Linn-cn
 * @create 2020/11/19
 */
public class CacheGlobal {

    /**
     * 全局缓存对象
     * ConcurrentHashMap 只保证了 MyCache不会有并发问题，但 MyCache 里面的属性值却不能保证。
     * 并发情况下会导致 MyCache 属性的值混乱
     */
    public static ConcurrentMap<String, MyCache> cacheMap = new ConcurrentHashMap<>();

    /**
     * 添加缓存
     */
    public void put(String key, Object value, long expire) {
        // 非空判断
        if (key == null) return;
        // 当缓存存在时，更新缓存
        MyCache myCache = CacheGlobal.cacheMap.computeIfPresent(key, (k, v) -> {
            v.setHitCount(v.getHitCount() + 1);
            System.out.println("put命中次数：" + v.getHitCount());
            v.setLastTime(System.currentTimeMillis());
            v.setExpireTime(expire);
            v.setValue(value);
            return v;
        });
        // 缓存不存在时为null，则新增缓存
        if (myCache == null) {
            MyCache cache = CacheGlobal.cacheMap.putIfAbsent(key, new MyCache()
                    .setKey(key)
                    .setValue(value)
                    .setWriteTime(System.currentTimeMillis())
                    .setLastTime(System.currentTimeMillis())
                    .setHitCount(1)
                    .setExpireTime(expire));
            if (cache == null) {
                System.out.println("put成功");
            }
        }
    }

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        // 非空判断
        if (key == null) return null;
        MyCache cache = CacheGlobal.cacheMap.get(key);
        if (cache == null) return null;
        // 惰性删除，判断缓存是否过期
        long timoutTime = System.currentTimeMillis() - cache.getWriteTime();
        // 缓存过期
        if (cache.getExpireTime() <= timoutTime) {
            // 清除过期缓存
            CacheGlobal.cacheMap.remove(key);
            return null;
        }
        cache.setHitCount(cache.getHitCount() + 1);
        System.out.println("get命中次数：" + cache.getHitCount());
        cache.setLastTime(System.currentTimeMillis());
        return cache.getValue();
    }

    /**
     * 测试线程安全
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        CacheGlobal cacheGlobal = new CacheGlobal();
        CountDownLatch latch = new CountDownLatch(16);
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        new Thread(() -> {
            for (int i = 0; i < 8; i++) {
                int finalI = i;
                threadPool.execute(() -> {
                    cacheGlobal.put("1", finalI, 10000);
                    latch.countDown();
                });
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 8; i++) {
                int finalI = i;
                threadPool.execute(() -> {
                    System.out.println(finalI + "：" + cacheGlobal.get("1"));
                    latch.countDown();
                });
            }
        }).start();
        latch.await();
        threadPool.shutdown();
        System.out.println(cacheMap);
    }

}
