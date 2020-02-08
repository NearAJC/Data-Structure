package com.itnear.structure.tree.union_find;

/**
 * 描述：并查集 - 快速合并rank优化版本
 * 作者：NearJC
 * 时间：2020/02/08
 */
public class QuickUnion implements UF {

    /**
     * 存储父节点索引
     */
    private int[] parent;

    /**
     * 存储元素层数
     */
    private int[] rank;

    public QuickUnion(int size) {
        this.parent = new int[size];
        this.rank = new int[size];
        for (int i = 0; i < size; i++) {
            this.parent[i] = i;
            this.rank[i] = 1;
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
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[qRoot] < rank[pRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[qRoot] += 1;
        }
    }

    /**
     * 获取并查集元素个数
     *
     * @return 元素个
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
            parent[p] = parent[parent[p]]; // 路径压缩
            p = parent[p];
        }
        return p;
    }

    /**
     * 根据元素p找到父节点索引，递归算法
     *
     * @param p 元素
     * @return 父节点索引
     */
    private int find1(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("元素不在范围内");
        }

        if (parent[p] != p) {
            parent[p] = find1(parent[p]);
        }
        return parent[p];
    }
}
