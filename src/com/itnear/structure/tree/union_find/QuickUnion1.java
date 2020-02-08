package com.itnear.structure.tree.union_find;

/**
 * 描述：并查集 - 快速合并size版本
 * 作者：NearJC
 * 时间：2020/02/08
 */
public class QuickUnion1 implements UF {

    /**
     * 存储父节点索引
     */
    private int[] parent;

    /**
     * 存储元素个数
     */
    private int[] sz;

    public QuickUnion1(int size) {
        this.parent = new int[size];
        this.sz = new int[size];
        for (int i = 0; i < size; i++) {
            this.parent[i] = i;
            sz[i] = 1;
        }
    }

    /**
     * 查看元素p和元素q是否所属一个集合
     *
     * @param p 元素
     * @param q 元素
     * @return 是返回true，否则返回false
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合并元素p和元素q所属的集合
     *
     * @param p 元素
     * @param q 元素
     */
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }

    /**
     * 获取并查集元素个数
     *
     * @return 元素个数
     */
    @Override
    public int getSize() {
        return parent.length;
    }

    /**
     * 根据元素p找到父节点索引
     *
     * @param p 元素
     * @return 父节点索引
     */
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("元素不在范围内");
        }

        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }
}
