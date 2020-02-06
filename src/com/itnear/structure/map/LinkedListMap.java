package com.itnear.structure.map;

/**
 * 描述：链式映射
 * 作者：NearJC
 * 时间：2020/02/06
 */
public class LinkedListMap<K, V> implements Map<K, V> {

    /**
     * 节点结构
     */
    private class Node {
        /**
         * 键
         */
        public K key;

        /**
         * 值
         */
        public V value;

        /**
         * 下一个节点
         */
        public Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
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

    public LinkedListMap() {
        this.dummyHead = null;
        this.size = 0;
    }

    /**
     * 向映射中添加节点，如果键已存在，则修改原来的值，如果不存在则添加
     *
     * @param key   键
     * @param value 值
     */
    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else {
            node.value = value;
        }
    }

    /**
     * 根据键key删除节点，并返回节点对应的值
     *
     * @param key 键
     * @return 被删除的值
     */
    @Override
    public V remove(K key) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.key.equals(key)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Node retNode = prev.next;
            prev.next = retNode.next;
            retNode.next = null;
            size--;
            return retNode.value;
        }

        return null;
    }

    /**
     * 判断元素是否包含键Key
     *
     * @param key 键
     * @return 包含返回true，否则返回false
     */
    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    /**
     * 根据键获取值
     *
     * @param key 键
     * @return 值
     */
    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    /**
     * 根据键key设置新值newValue
     *
     * @param key      键
     * @param newValue 新值
     */
    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException("键：" + key + "不存在，修改失败！");
        }
        node.value = newValue;
    }

    /**
     * 获取集合元素个数
     *
     * @return 元素个数
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 判断集合是否为空
     *
     * @return 空返回true，否则返回false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 根据键key获取节点
     *
     * @param key 键
     * @return 节点
     */
    private Node getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }

            cur = cur.next;
        }

        return null;
    }
}
