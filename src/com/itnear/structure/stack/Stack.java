package com.itnear.structure.stack;

/**
 * 描述：栈接口
 * 作者：NearJC
 * 时间：2020/02/05
 */
public interface Stack<E> {

    int getSize();

    boolean isEmpty();

    void push(E e);

    E pop();

    E peek();
}
