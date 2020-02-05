package com.itnear.structure.linear.linkedlist;

/**
 * 描述：链表
 * 作者：NearJC
 * 时间：2020/02/05
 */
public class LinkedList<E> {

    /**
     * 链表节点结构
     */
    private class Node {
        /**
         * 节点元素
         */
        private E e;

        /**
         * 下一个节点
         */
        private Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    /**
     * 头节点
     */
    private Node dummyHead;

    /**
     * 元素个数
     */
    private int size;

    public LinkedList() {
        this.dummyHead = new Node();
        this.size = 0;
    }

    /**
     * 获取元素个数
     *
     * @return 元素个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断链表是否为空
     *
     * @return 空返回true，否则返回false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 根据索引添加元素
     *
     * @param index 索引
     * @param e     元素
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("添加失败，索引越界");
        }

        // 添加节点
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node(e, prev.next);
        size++;
    }

    /**
     * 在表头添加元素e
     *
     * @param e 元素
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在表尾添加新的元素e
     *
     * @param e 元素
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 根据索引index修改元素
     *
     * @param index 索引
     * @param e     元素
     */
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("添加失败，索引越界");
        }

        // 修改
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    /**
     * 查找链表中是否有元素e
     *
     * @param e 元素
     * @return 存在时返回true，否则返回false
     */
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }

        return false;
    }

    /**
     * 根据索引移除元素，并返回移除的元素
     *
     * @param index 索引
     * @return 移除元素
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("添加失败，索引越界");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;

        return retNode.e;
    }

    /**
     * 移除表头，并返回删除元素
     *
     * @return 删除元素
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 移除链表的最后一个元素，并返回删除元素
     *
     * @return 删除元素
     */
    public E removeLast() {
        return remove(size);
    }

    /**
     * 从链表中删除指定元素e
     *
     * @param e 元素
     */
    public void removeElement(E e) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.e.equals(e)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Node retNode = prev.next;
            prev.next = retNode.next;
            retNode.next = null;
            size--;
        }
    }

    /**
     * 根据索引index获取元素
     *
     * @param index 索引
     * @return 元素
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("添加失败，索引越界");
        }

        Node prev = dummyHead.next;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        return prev.e;
    }

    /**
     * 获取链表的第一个元素
     *
     * @return 元素
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获取最后一个元素
     *
     * @return 元素
     */
    public E getLast() {
        return get(size);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        res.append(String.format("LinkedList：链表元素个数：%d\n", getSize()));
        Node cur = dummyHead.next;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();

        // 添加元素
        linkedList.addFirst("Hello");
        linkedList.addFirst("Word");
        linkedList.addFirst("Set");
        linkedList.addFirst("advantage");
        linkedList.addFirst("sky");
        linkedList.addFirst("advice");

        // 打印
        System.out.println(linkedList);

        // 移除元素
        String removeEle = linkedList.remove(5);
        System.out.println("移除的元素：" + removeEle);

        // 修改
        linkedList.set(4, "advise");

        // 打印
        System.out.println(linkedList);

        // 判断是否包含元素'Right'
        boolean result = linkedList.contains("Right");
        if (result) {
            System.out.println("该元素在链表中");
        } else {
            System.out.println("该元素不在链表中");
        }

        // 判断链表是否为空
        boolean isEmpty = linkedList.isEmpty();
        if (isEmpty) {
            System.out.println("链表为空");
        } else {
            System.out.println("链表不为空");
        }
    }
}
