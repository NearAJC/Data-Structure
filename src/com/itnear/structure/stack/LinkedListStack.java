package com.itnear.structure.stack;

import com.itnear.structure.linkedlist.LinkedList;

/**
 * 描述：链式栈
 * 作者：NearJC
 * 时间：2020/02/05
 */
public class LinkedListStack<E> implements Stack<E> {

    /**
     * 数据容器
     */
    private LinkedList<E> list;

    public LinkedListStack() {
        this.list = new LinkedList<>();
    }

    /**
     * 获取栈中元素个数
     *
     * @return 元素个数
     */
    @Override
    public int getSize() {
        return list.getSize();
    }

    /**
     * 判断栈是否为空
     *
     * @return 空返回true，否则返回false
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 入栈
     *
     * @param e 元素
     */
    @Override
    public void push(E e) {
        list.addLast(e);
    }

    /**
     * 出栈
     *
     * @return 元素
     */
    @Override
    public E pop() {
        return list.removeLast();
    }

    /**
     * 查看栈顶元素
     *
     * @return 元素
     */
    @Override
    public E peek() {
        return list.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        res.append(String.format(">>Stack：栈元素个数：%d \n[", getSize()));
        for (int i = 0; i < getSize(); i++) {
            res.append(list.get(i)).append(",");
        }

        return res.replace(res.length() - 1, res.length(), "] Top").toString();
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<>();

        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }
}
