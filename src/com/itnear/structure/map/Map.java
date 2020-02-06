package com.itnear.structure.map;

/**
 * 描述：映射接口
 * 作者：NearJC
 * 时间：2020/02/06
 */
public interface Map<K, V> {

    void add(K key, V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key, V newValue);

    int getSize();

    boolean isEmpty();
}
