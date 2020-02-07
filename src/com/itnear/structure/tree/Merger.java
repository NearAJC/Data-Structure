package com.itnear.structure.tree;

/**
 * 描述：合并业务接口
 * 作者：NearJC
 * 时间：2020/02/05
 */
public interface Merger<E> {

    E merge(E a, E b);
}
