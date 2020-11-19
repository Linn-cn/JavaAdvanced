package edu.changda.cache;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 缓存实体类
 *
 * @author Linn-cn
 * @create 2020/11/19
 */
@Data
@Accessors(chain = true)
public class MyCache implements Comparable<MyCache> {

    /**
     * 缓存键
     */
    private Object key;
    /**
     * 缓存值
     */
    private volatile Object value;
    /**
     * 最后访问时间
     */
    private volatile long lastTime;
    /**
     * 创建时间
     */
    private volatile long writeTime;
    /**
     * 存活时间
     */
    private volatile long expireTime;
    /**
     * 命中次数
     */
    private volatile Integer hitCount;

    @Override
    public int compareTo(MyCache o) {
        return hitCount.compareTo(o.hitCount);
    }

    @Override
    public String toString() {
        return "MyCache{" +
                "key=" + key +
                ", value=" + value +
                ", lastTime=" + lastTime +
                ", writeTime=" + writeTime +
                ", expireTime=" + expireTime +
                ", hitCount=" + hitCount +
                '}';
    }
}
