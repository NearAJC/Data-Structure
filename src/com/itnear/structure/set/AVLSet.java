package com.itnear.structure.set;

import com.itnear.structure.tree.avl.AVLTree;

/**
 * 描述：AVL树集合
 * 作者：NearJC
 * 时间：2020/02/08
 */
public class AVLSet<E extends Comparable<E>> implements Set<E> {

    private AVLTree<E, Object> tree;

    public AVLSet() {
        tree = new AVLTree();
    }

    /**
     * 添加元素e
     *
     * @param e 元素
     */
    @Override
    public void add(E e) {
        tree.add(e, null);
    }

    /**
     * 判断元素e是否存在集合中
     *
     * @param e 元素
     * @return 存储返回true，否则返回false
     */
    @Override
    public boolean contains(E e) {
        return tree.contains(e);
    }

    /**
     * 删除元素e
     *
     * @param e 元素
     */
    @Override
    public void remove(E e) {
        tree.remove(e);
    }

    /**
     * 获取集合元素个数
     *
     * @return 元素个数
     */
    @Override
    public int getSize() {
        return tree.getSize();
    }

    /**
     * 判断集合是否为空
     *
     * @return 空返回true，否则返回false
     */
    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }
}