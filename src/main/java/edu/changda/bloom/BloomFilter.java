package edu.changda.bloom;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname BloomFilter
 * @description
 * @create 2020-05-15 22:41
 **/
public class BloomFilter {

    private final byte[] data;

    public BloomFilter(int initSize){
        this.data = new byte[initSize * 2];
    }

    private void add(int key){
        int location1 = Math.abs(hash1(key) % data.length);
        int location2 = Math.abs(hash2(key) % data.length);
        int location3 = Math.abs(hash3(key) % data.length);
        data[location1] = data[location2] = data[location3] = 1;
    }

    private boolean contains(int key){
        int location1 = Math.abs(hash1(key) % data.length);
        int location2 = Math.abs(hash2(key) % data.length);
        int location3 = Math.abs(hash3(key) % data.length);
        return data[location1] * data[location2] * data[location3] == 1;
    }

    private int hash1(Integer key){
        return key.hashCode();
    }

    private int hash2(Integer key){
        int hashCode = key.hashCode();
        return hashCode ^ (hashCode >>> 3);
    }

    private int hash3(Integer key){
        int hashCode = key.hashCode();
        return hashCode ^ (hashCode >>> 16);
    }
}
