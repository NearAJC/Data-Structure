package com.itnear.structure.set;

import com.itnear.structure.tree.binary_search.BST;

/**
 * 描述：二分搜索树集合
 * 作者：NearJC
 * 时间：2020/02/06
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> data;

    public BSTSet() {
        this.data = new BST<>();
    }

    /**
     * 添加元素e
     *
     * @param e 元素
     */
    @Override
    public void add(E e) {
        data.add(e);
    }

    /**
     * 判断元素e是否存在集合中
     *
     * @param e 元素
     * @return 存储返回true，否则返回false
     */
    @Override
    public boolean contains(E e) {
        return data.contains(e);
    }

    /**
     * 删除元素e
     *
     * @param e 元素
     */
    @Override
    public void remove(E e) {
        data.remove(e);
    }

    /**
     * 获取集合元素个数
     *
     * @return 元素个数
     */
    @Override
    public int getSize() {
        return data.getSize();
    }

    /**
     * 判断集合是否为空
     *
     * @return 空返回true，否则返回false
     */
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
