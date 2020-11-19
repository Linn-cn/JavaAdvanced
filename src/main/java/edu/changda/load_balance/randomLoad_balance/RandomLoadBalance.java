package edu.changda.load_balance.randomLoad_balance;

import java.util.*;

/**
 * 模拟多个服务节点
 * 基于权重的负载均衡算法
 * @author Linn-cn
 * @create 2020/09/20
 */
public class RandomLoadBalance {
    // 服务器地址
    public static final List<String> ips = new ArrayList<String>() {{
        add("192.168.1.1");
        add("192.168.1.2");
        add("192.168.1.3");
    }};

    // 权重
    public static final Map<String, Integer> weight = new HashMap<String, Integer>() {{
        put("192.168.1.1", 5);
        put("192.168.1.2", 3);
        put("192.168.1.3", 2);
    }};

    /**
     * 坐标轴思路实现
     * 0---5---8---10
     *   A   B   C
     */
    public static String getServer() {
        int length = 0;
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : weight.entrySet()) {
            length += entry.getValue();
            treeMap.put(length, entry.getKey());
        }
        int randomNumber = new Random().nextInt(length);
        int index = treeMap.firstKey();
        for (Integer key : treeMap.navigableKeySet()) {
            if (randomNumber > key) {
                index = key;
            }
        }
        return treeMap.get(index);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }
    }
}
