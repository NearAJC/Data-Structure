package com.itnear.structure.linear.queue;

import com.itnear.structure.linear.array.Array;

/**
 * 描述：顺序队列
 * 作者：NearJC
 * 时间：2020/02/05
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue(int capacity) {
        array = new Array(capacity);
    }

    public ArrayQueue() {
        array = new Array();
    }

    /**
     * 获取队列元素个数
     *
     * @return 元素个数
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     * 判断队列是否为空
     *
     * @return 为空返回true, 否则返回false
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 入队
     *
     * @param e 元素
     */
    @Override
    public void enqueue(E e) {
        array.addFirst(e);
    }

    /**
     * 出队
     *
     * @return 元素
     */
    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    /**
     * 查看队头
     *
     * @return 元素
     */
    @Override
    public E getFront() {
        return array.getFirst();
    }

    /**
     * 获取队列容量
     *
     * @return 容量
     */
    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: 元素个数：%d，队列容量：%d\n", array.getSize(), array.getCapacity()));
        res.append("Front [");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1)
                res.append(", ");
        }

        return res.replace(res.length(), res.length() + 1, "] Tail").toString();
    }

    public static void main(String[] args) {
        // 创建队列
        Queue<String> queue = new ArrayQueue<>(10);

        // 添加
        queue.enqueue("Hello");
        queue.enqueue("Tall");
        queue.enqueue("First");
        queue.enqueue("Tail");
        queue.enqueue("Last");

        // 打印
        System.out.println(queue);

        // 出队
        String e = queue.dequeue();
        System.out.println("出队元素：" + e);

        // 打印
        System.out.println(queue);

        // 查看队头元素
        String frontValue = queue.getFront();
        System.out.println("头元素：" + frontValue);
    }
}
