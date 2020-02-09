package com.itnear.structure.hash;

import java.util.TreeMap;

/**
 * 描述：哈希表
 * 作者：NearJC
 * 时间：2020/02/09
 */
public class HashTable<K, V> {

    /**
     * 容量数组
     */
    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

    /**
     * 容量上限
     */
    private static final int upperTol = 10;
    /**
     * 容量下限
     */
    private static final int lowerTol = 2;

    /**
     * 动态容量索引
     */
    private static int capacityIndex = 0;

    /**
     * 哈希表结构
     */
    private TreeMap<K, V> hashTable[];

    /**
     * 容量
     */
    private int M;

    /**
     * 元素个数
     */
    private int size;

    public HashTable() {
        this.M = capacity[capacityIndex];
        this.size = 0;
        hashTable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashTable[i] = new TreeMap<>();
        }
    }

    /**
     * 获取元素个数
     *
     * @return 元素个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 添加元素
     *
     * @param key   键
     * @param value 值
     */
    public void add(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            size++;

            // 判断是否扩容
            if (size >= upperTol * M && capacityIndex + 1 < capacity.length) {
                capacityIndex++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    /**
     * 根据键key删除元素
     *
     * @param key 键
     * @return 删除的元素值
     */
    public V remove(K key) {
        TreeMap<K, V> map = hashTable[hash(key)];
        V ret = null;
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;

            // 判断是否缩容
            if (size < lowerTol * M && capacityIndex - 1 >= 0) {
                capacityIndex--;
                resize(capacity[capacityIndex]);
            }
        }
        return ret;
    }

    /**
     * 根据键key设置值
     *
     * @param key   键
     * @param value 值
     */
    public void set(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + "不存在");
        }
        map.put(key, value);
    }

    /**
     * 根据键key判断是否包含元素
     *
     * @param key 键
     * @return 包含返回true，否则返回false
     */
    public boolean contains(K key) {
        return hashTable[hash(key)].containsKey(key);
    }

    /**
     * 根据键key获取值
     *
     * @param key 键
     * @return 值
     */
    public V get(K key) {
        return hashTable[hash(key)].get(key);
    }

    /**
     * 扩容/缩容
     *
     * @param newM 新容量
     */
    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = new TreeMap<>();
        }
        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashTable[i];
            for (K key : map.keySet()) {
                newHashTable[hash(key)].put(key, map.get(key));
            }
            this.hashTable = newHashTable;
        }
    }

    /**
     * 哈希函数
     *
     * @param key 健
     * @return 索引
     */
    private int hash(K key) {
        return key.hashCode() & 0x7fffffff % M;
    }
}
