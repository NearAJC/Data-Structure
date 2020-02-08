package com.itnear.structure.map;

import com.itnear.structure.tree.avl.AVLTree;

/**
 * 描述：AVL树映射
 * 作者：NearJC
 * 时间：2020/02/08
 */
public class AVLMap<K extends Comparable<K>, V> implements Map<K, V> {

    private AVLTree<K, V> tree;

    public AVLMap() {
        tree = new AVLTree<>();
    }

    /**
     * 添加元素
     *
     * @param key   键
     * @param value 值
     */
    @Override
    public void add(K key, V value) {
        tree.add(key, value);
    }

    /**
     * 删除键为key的节点
     *
     * @param key 键
     * @return 值
     */
    @Override
    public V remove(K key) {
        return tree.remove(key);
    }

    /**
     * 判断键是否包含在树中
     *
     * @param key 键
     * @return 包含返回true，否则返回false
     */
    @Override
    public boolean contains(K key) {
        return tree.contains(key);
    }

    /**
     * 根据键获取值
     *
     * @param key 键
     * @return 值
     */
    @Override
    public V get(K key) {
        return tree.get(key);
    }

    /**
     * 根据键key设置值newValue
     *
     * @param key      键
     * @param newValue 值
     */
    @Override
    public void set(K key, V newValue) {
        tree.set(key, newValue);
    }

    /**
     * 获取元素个数
     *
     * @return 元素个数
     */
    @Override
    public int getSize() {
        return tree.getSize();
    }

    /**
     * 判断映射是否为空
     *
     * @return 空返回true，否则返回false
     */
    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }
}