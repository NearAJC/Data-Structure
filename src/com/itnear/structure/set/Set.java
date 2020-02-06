package com.itnear.structure.set;

/**
 * 描述：集合接口
 * 作者：NearJC
 * 时间：2020/02/06
 */
public interface Set<E> {

    void add(E e);

    boolean contains(E e);

    void remove(E e);

    int getSize();

    boolean isEmpty();
}
