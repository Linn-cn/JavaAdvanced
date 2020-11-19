package edu.changda.cache;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 定时清理策略
 * @author Linn-cn
 * @create 2020/11/19
 */
public class ExpireThread implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.SECONDS.sleep(10);
                expireCache();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 缓存检测和清除方法
     *
     * @author Linn-cn
     * @date 2020/11/19
     */
    public void expireCache() {
        System.out.println("检测缓存是否过期缓存");
        for (Map.Entry<String, MyCache> entry : CacheGlobal.cacheMap.entrySet()) {
            MyCache cache = entry.getValue();
            //当前时间-写入时间
            long timoutTime = System.currentTimeMillis() - cache.getWriteTime();
            if (cache.getExpireTime() > timoutTime) {
                //没过期
                continue;
            }
            //清除过期缓存
            CacheGlobal.cacheMap.remove(entry.getKey());
        }
    }
}
