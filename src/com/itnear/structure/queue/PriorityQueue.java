package com.itnear.structure.queue;

import com.itnear.structure.heap.MaxHeap;

/**
 * 描述：优先队列
 * 作者：NearJC
 * 时间：2020/02/05
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        this.maxHeap = new MaxHeap<>();
    }

    /**
     * 获取元素个数
     *
     * @return 元素个数
     */
    @Override
    public int getSize() {
        return maxHeap.getSize();
    }

    /**
     * 判断队列是否为空
     *
     * @return 空返回true，否则返回false
     */
    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    /**
     * 入队
     *
     * @param e 元素
     */
    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    /**
     * 出队
     *
     * @return 出队元素
     */
    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }

    /**
     * 查看队头元素
     *
     * @return 队头元素
     */
    @Override
    public E getFront() {
        return maxHeap.findMax();
    }
}
