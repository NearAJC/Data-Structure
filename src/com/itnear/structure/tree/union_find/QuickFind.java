package com.itnear.structure.tree.union_find;

/**
 * 描述：并查集 - 快速查询版本
 * 作者：NearJC
 * 时间：2020/02/08
 */
public class QuickFind implements UF {

    /**
     * 存储元素编号
     */
    private int[] id;

    public QuickFind(int size) {
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
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
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) {
            return;
        }

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) {
                id[i] = qID;
            }
        }
    }

    /**
     * 获取并查集元素个数
     *
     * @return 元素个
     */
    @Override
    public int getSize() {
        return id.length;
    }

    /**
     * 查找元素p所对应的集合编号
     *
     * @param p 元素
     * @return 集合编号
     */
    private int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("元素不在范围内");
        }
        return id[p];
    }
}
