package com.itnear.structure.linear.stack;

import com.itnear.structure.linear.array.Array;

/**
 * 描述：顺序栈
 * 作者：NearJC
 * 时间：2020/02/05
 */
public class ArrayStack<E> implements Stack<E> {

    /**
     * 数据容器
     */
    private Array<E> array;

    public ArrayStack(int capacity) {
        array = new Array(capacity);
    }

    public ArrayStack() {
        array = new Array();
    }

    /**
     * 获取栈元素个数
     *
     * @return 元素个数
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     * 判断栈是否为空
     *
     * @return 为空时返回true, 否则返回false
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 元素入栈
     *
     * @param e 元素
     */
    @Override
    public void push(E e) {
        array.addLast(e);
    }

    /**
     * 元素出栈
     *
     * @return 元素
     */
    @Override
    public E pop() {
        return array.removeLast();
    }

    /**
     * 查看栈顶元素
     *
     * @return 元素
     */
    @Override
    public E peek() {
        return array.getLast();
    }

    /**
     * 获取栈存储容量
     *
     * @return 容量
     */
    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        res.append(String.format(">>Stack：栈元素个数：%d，栈容量：%d \n[", getSize(), getCapacity()));
        for (int i = 0; i < getSize(); i++) {
            res.append(array.get(i)).append(",");
        }

        return res.replace(res.length() - 1, res.length(), "] Top").toString();
    }

    public static void main(String[] args) {
        ArrayStack<String> arrayStack = new ArrayStack<>();

        // 入栈
        arrayStack.push("monkey");
        arrayStack.push("key");
        arrayStack.push("tell");
        arrayStack.push("geek");
        arrayStack.push("right");
        arrayStack.push("wrong");

        // 遍历查看
        System.out.println(arrayStack);

        // 出栈
        arrayStack.pop();

        // 遍历查看
        System.out.println(arrayStack);

        // 查看元素
        String value = arrayStack.peek();
        System.out.println("查看的元素为：" + value);
    }
}
