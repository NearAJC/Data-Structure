package com.itnear.structure.heap;

import com.itnear.structure.array.Array;

import java.util.Random;

/**
 * 描述：优先队列（最大堆）
 * 作者：NearJC
 * 时间：2020/02/05
 */
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity) {
        this.data = new Array<>(capacity);
    }

    public MaxHeap() {
        this(10);
    }

    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * 添加元素
     *
     * @param e 元素
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 元素上浮
     *
     * @param k 索引
     */
    private void siftUp(int k) {
        while (k > 0 && data.get(k).compareTo(data.get(parent(k))) > 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    /**
     * 查看堆中最大元素
     *
     * @return 最大元素
     */
    public E findMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }
        return data.getFirst();
    }

    /**
     * 取出堆中最大元素
     *
     * @return 最大元素
     */
    public E extractMax() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);

        return ret;
    }

    /**
     * 元素下沉
     *
     * @param k 索引
     */
    private void siftDown(int k) {
        int j = leftChild(k);
        while (j < data.getSize()) {
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j++;
            }

            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }

            data.swap(k, j);
            k = j;
        }
    }

    /**
     * 取出堆中最大元素，并且替换成元素e
     *
     * @param e 元素
     * @return 最大元素
     */
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

    /**
     * 获取堆中元素个数
     *
     * @return 元素个数
     */
    public int getSize() {
        return data.getSize();
    }

    /**
     * 判断堆中元素是否为空
     *
     * @return 空返回true，否则返回false
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 根据该索引index获取父节点索引
     *
     * @param index 索引
     * @return 父节点索引
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("索引0没有父索引");
        }
        return (index - 1) / 2;
    }

    /**
     * 根据索引index返回左孩子的索引
     *
     * @param index 索引
     * @return 左孩子索引
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 根据索引index返回右孩子的索引
     *
     * @param index 索引
     * @return 右孩子索引
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("优先队列 元素个数：%d \n[", data.getSize()));
        for (int i = 0; i < data.getSize(); i++) {
            res.append(data.get(i)).append(",");
        }
        return res.replace(res.length() - 1, res.length(), "]").toString();
    }

    public static void main(String[] args) {
        Random random = new Random();
        MaxHeap maxHeap = new MaxHeap();
        for (int i = 0; i < 100; i++) {
            maxHeap.add(random.nextInt(100));
        }

        // 遍历
        System.out.println(maxHeap);

        // 获取最大值
        System.out.println("堆中最大值：" + maxHeap.findMax());

        // 移除最大值
        for (int i = 0; i < 10; i++) {
            maxHeap.extractMax();
        }
        System.out.println(maxHeap);
    }
}
