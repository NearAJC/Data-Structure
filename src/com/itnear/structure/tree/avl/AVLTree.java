package com.itnear.structure.tree.avl;

import com.itnear.util.FileOperation;

import java.util.ArrayList;

/**
 * 描述：AVL树 - 自平衡二叉树
 * 作者：NearJC
 * 时间：2020/02/08
 */
public class AVLTree<K extends Comparable<K>, V> {

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
         * 左右子树节点
         */
        public Node left, right;

        /**
         * 节点高度
         */
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
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

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * 向二分搜索树添加新的元素（key,value）
     *
     * @param key   键
     * @param value 值
     */
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 向以node为根的二分搜索树中插入元素（key,value），递归算法
     *
     * @param node  节点
     * @param key   键
     * @param value 值
     * @return 节点
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

        // 更新高度height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            System.out.println("平衡因子：" + balanceFactor);
        }

        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /**
     * 对节点y进行向右旋转操作，返回旋转后新的根节点x
     * //        y                              x
     * //       / \                           /   \
     * //      x   T4     向右旋转 (y)        z     y
     * //     / \       - - - - - - - ->    / \   / \
     * //    z   T3                       T1  T2 T3 T4
     * //   / \
     * // T1   T2
     *
     * @param y 节点
     * @return 旋转后的节点
     */
    private Node rightRotate(Node y) {
        // 向右旋转过程
        Node x = y.left;
        Node t3 = x.right;
        x.right = y;
        y.left = t3;

        // 更新高度height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /**
     * 对节点y进行向左旋转操作，返回旋转后新的根节点x
     * //    y                             x
     * //  /  \                          /   \
     * // T1   x      向左旋转 (y)       y     z
     * //     / \   - - - - - - - ->   / \   / \
     * //   T2  z                     T1 T2 T3 T4
     * //      / \
     * //     T3 T4
     *
     * @param y 节点
     * @return 旋转后的节点
     */
    private Node leftRotate(Node y) {
        // 向左旋转
        Node x = y.right;
        Node t2 = x.left;
        x.left = y;
        y.right = t2;

        // 更新高度height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /**
     * 判断键是否在平衡二叉树中
     *
     * @param key 键
     * @return 有返回true，否则返回false
     */
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    /**
     * 根据键获取节点值
     *
     * @param key 键
     * @return 值
     */
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    /**
     * 根据键设置新值
     *
     * @param key      键
     * @param newValue 值
     */
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException("操作失败，键不存在！");
        }
        node.value = newValue;
    }

    /**
     * 返回以node为根的二分搜索树的最小值所在的节点
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
     * 删除掉以node为根的二分搜索树中的最小节点
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
     * 根据键key删除节点
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
     * 删除掉以node为根的二分搜索树中的节点，递归算法
     *
     * @param node 节点
     * @return 节点
     */
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {   // key.compareTo(node.key) == 0
            if (node.left == null) {// 待删除节点左子树为空的情况
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (node.right == null) {  // 待删除节点右子树为空的情况
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }

        if (retNode == null)
            return null;
        // 更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotate(retNode);

        // RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            return leftRotate(retNode);

        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }

    /**
     * 获取树节点个数
     *
     * @return 节点个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断树是否为空
     *
     * @return 空返回true，否则返回false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断该树是否是一棵二分搜索树
     *
     * @return 是返回true，否则返回false
     */
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 中序遍历，递归算法
     *
     * @param node 节点
     * @param keys 存储键列表
     */
    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null) {
            return;
        }

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    /**
     * 判断该二叉树是否是一棵平衡二叉树
     *
     * @return 是返回true，否则返回false
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    /**
     * 判断以node为根的二叉树是否是一棵平衡二叉树，递归算法
     *
     * @param node 节点
     * @return 是返回true，否则返回false
     */
    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }

        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * 获取平衡因子
     *
     * @param node 节点
     * @return 平衡因子
     */
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 获取树的高度
     *
     * @param node 节点
     * @return 高度
     */
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * 返回以node为根节点的二分搜索树中key所在节点
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
            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
            System.out.println("删除: " + map.remove("pride"));

            System.out.println("is BST：" + map.isBST());
            System.out.println("is Balanced：" + map.isBalanced());
        }

        System.out.println();
    }
}
