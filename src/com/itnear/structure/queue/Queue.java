package com.itnear.structure.queue;

/**
 * 描述：队列接口
 * 作者：NearJC
 * 时间：2020/02/05
 */
public interface Queue<E> {

    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}
