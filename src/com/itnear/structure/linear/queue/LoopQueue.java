package com.itnear.structure.linear.queue;

public class LoopQueue<E> implements Queue<E> {

    /**
     * 数据存储容器
     */
    private E[] data;

    /**
     * 队头、队尾索引
     */
    private int front, tail;

    /**
     * 元素个数
     */
    private int size;

    public LoopQueue(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.front = 0;
        this.tail = 0;
        this.size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    /**
     * 队列元素个数
     *
     * @return 元素个数
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 判断队列是否为空
     *
     * @return 为空返回true，否则返回false
     */
    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    /**
     * 入队
     *
     * @param e 元素
     */
    @Override
    public void enqueue(E e) {
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    /**
     * 出队
     *
     * @return 元素
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("出队失败，队列为空");
        }

        E ret = data[front];
        front = (front + 1) % data.length;
        size--;

        return ret;
    }

    /**
     * 查看队头
     *
     * @return 元素
     */
    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("出队失败，队列为空");
        }

        return data[front];
    }

    /**
     * 返回队列容量
     *
     * @return 队列容量
     */
    public int getCapacity() {
        return data.length;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        res.append(String.format("LoopQueue: 元素个数：%d，容量：%d\n", getSize(), getCapacity()));
        res.append("Front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]).append(", ");
        }

        return res.replace(res.length() - 2, res.length(), "] Tail").toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
