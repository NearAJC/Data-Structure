package com.itnear.structure.tree.red_black;

import com.itnear.util.FileOperation;

import java.util.ArrayList;

/**
 * 描述：红黑树
 * 作者：NearJC
 * 时间：2020/02/10
 */
public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    /**
     * 节点结构
     */
    private class Node {
        /**
         * 键
         */
        private K key;

        /**
         * 值
         */
        private V value;

        /**
         * 左右孩子节点
         */
        private Node left, right;

        /**
         * 颜色
         */
        private boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.color = RED;
        }
    }

    /**
     * 根节点
     */
    private Node root;

    /**
     * 节点个数
     */
    private int size;

    public RBTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * 向红黑树中添加新的元素(key, value)
     *
     * @param key   键
     * @param value 值
     */
    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK; // 最终根节点为黑色节点
    }

    /**
     * 向以node为根的红黑树中插入元素(key, value)，递归算法
     *
     * @param node  节点
     * @param key   键
     * @param value 值
     * @return 返回插入新节点后红黑树的根
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

        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    /**
     * 从红黑树中删除键为key的节点
     *
     * @param key 键
     * @return 值
     */
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    /**
     * 以节点node为根并根据键key删除节点，递归算法
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

            // 待删除节点左右子树均不为空的情况
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    /**
     * 返回以node为根的二分搜索树的最小值所在的节点
     *
     * @param node 节点
     * @return 返回最小节点
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 删除掉以node为根的二分搜索树中的最小节点
     *
     * @param node 节点
     * @return 返回删除节点后新的红黑树的根
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
     * 根据键key获取节点值
     *
     * @param key 键
     * @return 节点值
     */
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    /**
     * 根据键key修改值
     *
     * @param key      键
     * @param newValue 新值
     */
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + "不存在!");
        }
        node.value = newValue;
    }

    /**
     * 根据键key判断键是否在红黑树中
     *
     * @param key 键
     * @return 存在返回true，否则返回false
     */
    public boolean contains(K key) {
        return getNode(root, key) != null;
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
     * 判断红黑树是否为空
     *
     * @return 为空返回true，否则返回false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断节点的颜色
     *
     * @param node 节点
     * @return 节点为null时返回黑色，否则返回节点颜色
     */
    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }

        return node.color;
    }

    /**
     * //   node                     x
     * //  /   \     左旋转         /  \
     * // T1   x   --------->   node   T3
     * //     / \              /   \
     * //    T2 T3            T1   T2
     *
     * @param node 节点
     * @return 左旋转的节点
     */
    private Node leftRotate(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    /**
     * //     node                   x
     * //    /   \     右旋转       /  \
     * //   x    T2   ------->   y   node
     * //  / \                       /  \
     * // y  T1                     T1  T2
     *
     * @param node 节点
     * @return 右旋转的节点
     */
    private Node rightRotate(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    /**
     * 颜色翻转
     *
     * @param node 节点
     */
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    /**
     * 返回以node为根节点的二分搜索树中，key所在的节点
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

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            RBTree<String, Integer> map = new RBTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }
}
