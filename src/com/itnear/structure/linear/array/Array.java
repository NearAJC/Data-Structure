package com.itnear.structure.linear.array;

/**
 * 描述：数组
 * 作者：NearJC
 * 时间：2020/02/05
 */
public class Array<E> {

    /**
     * 存储元素
     */
    private E[] data;

    /**
     * 元素个数
     */
    private int size;

    public Array(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.size = 0;
    }

    public Array() {
        this(10);
    }

    /**
     * 获取数组元素个数
     *
     * @return 元素个数
     */
    public int getSize() {
        return this.size;
    }

    /**
     * 获取数组容量大小
     *
     * @return 容量大小
     */
    public int getCapacity() {
        return this.data.length;
    }

    /**
     * 判断数组是否为空
     *
     * @return 空返回true，否则返回false
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 根据索引添加元素
     *
     * @param index 索引
     * @param e     元素
     */
    public void add(int index, E e) {
        if (index < 0 || index > this.size) {
            throw new IllegalArgumentException("数组下标越界");
        }

        // 扩容
        if (this.size == this.data.length) {
            resize(this.data.length * 2);
        }

        // 添加元素
        for (int i = this.size - 1; i >= index; i--) {
            this.data[i + 1] = this.data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 头部添加元素
     *
     * @param e 元素
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 尾部添加元素
     *
     * @param e 元素
     */
    public void addLast(E e) {
        add(this.size, e);
    }

    /**
     * 根据索引删除元素
     *
     * @param index 索引
     * @return 删除元素
     */
    public E remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("数组下标越界");
        }

        // 删除元素
        E ret = this.data[index];
        for (int i = index + 1; i < this.size; i++) {
            this.data[i - 1] = this.data[i];
        }
        this.size--;

        // 缩容
        if (this.size == this.data.length / 4 && this.data.length / 2 != 0) {
            resize(this.data.length / 2);
        }

        return ret;
    }

    /**
     * 删除首元素
     *
     * @return 首元素
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除尾部元素
     *
     * @return 尾部元素
     */
    public E removeLast() {
        return remove(this.size - 1);
    }

    /**
     * 根据索引获取元素
     *
     * @param index 索引
     * @return 元素
     */
    public E get(int index) {
        if (index < 0 || index > this.size) {
            throw new IllegalArgumentException("数组下标越界");
        }

        return this.data[index];
    }

    /**
     * 获取首元素
     *
     * @return 首元素
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获取尾部元素
     *
     * @return 尾部元素
     */
    public E getLast() {
        return get(this.size - 1);
    }

    /**
     * 判断元素是否存在在数组中
     *
     * @param e 元素
     * @return 存在返回true，否则返回false
     */
    public boolean contains(E e) {
        for (int i = 0; i < this.size; i++) {
            if (this.data[i].equals(e)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 根据索引修改元素
     *
     * @param index 索引
     * @param e     元素
     */
    public void set(int index, E e) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("数组下标越界");
        }

        this.data[index] = e;
    }

    /**
     * 根据索引交换元素
     *
     * @param i 索引
     * @param j 索引
     */
    public void swap(int i, int j) {
        if (i < 0 || i >= this.size || j < 0 || j >= this.size) {
            throw new IllegalArgumentException("数组下标越界");
        }

        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /**
     * 扩容/缩容数组容量
     *
     * @param newCapacity 新容量大小
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < this.size; i++) {
            newData[i] = this.data[i];
        }
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("数组容量：%d  数组元素个数：%d\n[", this.data.length, this.size));
        for (int i = 0; i < this.size; i++) {
            res.append(data[i]).append(",");
        }
        return res.replace(res.length() - 1, res.length(), "]").toString();
    }

    public static void main(String[] args) {
        Array<Integer> array = new Array(5);

        // 添加元素
        array.addFirst(10);
        array.addFirst(10);
        array.addFirst(20);
        array.addFirst(30);
        array.addLast(60);
        array.add(3, 30);
        array.addFirst(10);

        // 遍历
        System.out.println(array.toString());

        // 删除元素
        System.out.println("删除的元素：" + array.remove(4));

        // 交换数据
        array.swap(1, 2);

        // 遍历
        System.out.println(array.toString());

        // 获取
        System.out.println("获取的元素：" + array.get(2));

        // 查看元素是否存在
        boolean contains = array.contains(100);
        System.out.println("元素是否在数组内：" + contains);
    }
}
