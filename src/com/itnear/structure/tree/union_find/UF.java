package com.itnear.structure.tree.union_find;

/**
 * 描述：并查集接口
 * 作者：NearJC
 * 时间：2020/02/08
 */
public interface UF {

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);

    int getSize();
}
