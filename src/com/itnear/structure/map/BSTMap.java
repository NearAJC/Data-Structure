package com.itnear.structure.map;

/**
 * 描述：二分搜索树映射
 * 作者：NearJC
 * 时间：2020/02/06
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

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
         * 左子树、右子树
         */
        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * 根节点
     */
    private Node root;

    /**
     * 元素个数
     */
    private int size;

    public BSTMap() {
        this.root = null;
        this.size = 0;
    }

    /**
     * 添加元素
     *
     * @param key   键
     * @param value 值
     */
    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 向以node为根的二分搜索树中插入元素（key,value），递归算法
     *
     * @param node  节点
     * @param key   键
     * @param value 值
     */
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        return node;
    }

    /**
     * 删除键为key的节点
     *
     * @param key 键
     * @return 值
     */
    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }

        return null;
    }

    /**
     * 以node为根节点的以键key的节点，递归算法
     *
     * @param node 节点
     * @param key  键
     * @return 节点
     */
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            // 待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除节点左右子树不为空的情况
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            size--;

            return successor;
        }
    }

    /**
     * 返回以node为根的二分搜索树的最小值所在节点
     *
     * @param node 节点
     * @return 节点
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }

        return minimum(node.left);
    }

    /**
     * 删除以node为根的二分搜索树中最小节点
     *
     * @param node 节点
     * @return 节点
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 判断键是否包含在树中
     *
     * @param key 键
     * @return 包含返回true，否则返回false
     */
    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    /**
     * 根据键获取值
     *
     * @param key 键
     * @return 值
     */
    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    /**
     * 根据键key设置值newValue
     *
     * @param key      键
     * @param newValue 值
     */
    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException("键：" + key + "不存在，修改失败！");
        }
        node.value = newValue;
    }

    /**
     * 获取元素个数
     *
     * @return 元素个数
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 判断映射是否为空
     *
     * @return 空返回true，否则返回false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 以node为根根据键key获取节点
     *
     * @param node 节点
     * @param key  键
     * @return 节点
     */
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }
}
