package com.itnear.structure.tree.binary_search;

import com.itnear.structure.queue.LinkedListQueue;
import com.itnear.structure.queue.Queue;

/**
 * 描述：二分搜索树
 * 作者：NearJC
 * 时间：2020/02/05
 */
public class BST<E extends Comparable<E>> {

    /**
     * 节点结构
     */
    private class Node {
        /**
         * 节点数据
         */
        public E e;

        /**
         * 左子树、右子树
         */
        public Node left, right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
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

    public BST() {
        this.root = null;
        this.size = 0;
    }

    /**
     * 获取二分搜索树的节点个数
     *
     * @return 节点个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断二分搜索树是否为空
     *
     * @return 空返回true, 否则返回false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向二分搜索树中添加元素e
     *
     * @param e 元素
     */
    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 向以node为根的二分搜索树中添加元素e，递归算法
     *
     * @param node 节点
     * @param e    元素
     * @return 根
     */
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }

        return node;
    }

    /**
     * 判断二分搜索树是否包含元素e
     *
     * @param e 元素
     * @return 包含返回true，否则返回false
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 以node为根的二分搜索树中判断是否包含元素e，递归算法
     *
     * @param node 节点
     * @param e    元素
     * @return 包含返回true，否则返回false
     */
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 以node为根的二分搜索树进行前序遍历
     *
     * @param node 节点
     */
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 以node为根的二分搜索树进行中序遍历
     *
     * @param node 节点
     */
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }

        preOrder(node.left);
        System.out.println(node.e);
        preOrder(node.right);
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 以node为根的二分搜索树进行中序遍历
     *
     * @param node 节点
     */
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }

        preOrder(node.left);
        preOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 层序遍历
     */
    public void levelOrder() {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedListQueue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node curNode = queue.dequeue();
            System.out.println(curNode.e);

            if (curNode.left != null) {
                queue.enqueue(curNode.left);
            }

            if (curNode.right != null) {
                queue.enqueue(curNode.right);
            }
        }
    }

    /**
     * 寻找二分搜索树中的最小元素
     *
     * @return 最小元素
     */
    public E minimum() {
        if (isEmpty()) {
            throw new IllegalArgumentException("操作失败，树为空");
        }

        Node minNode = minimum(root);
        return minNode.e;
    }

    /**
     * 向以node为根的二分搜索树中寻找最小元素
     *
     * @param node 节点
     * @return 最小元素
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }

        return minimum(node.left);
    }

    /**
     * 寻找二分搜索树中的最大元素
     *
     * @return 最大元素
     */
    public E maximum() {
        if (isEmpty()) {
            throw new IllegalArgumentException("操作失败，树为空");
        }

        Node maxNode = maximum(root);
        return maxNode.e;
    }

    /**
     * 向以node为根的二分搜索树中寻找最大元素
     *
     * @param node 节点
     * @return 最小元素
     */
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }

        return maximum(node.right);
    }

    /**
     * 从二分搜索树中删除最小值所在节点，返回最小值
     *
     * @return 最小值
     */
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    /**
     * 以node为根的二分搜索树中删除最小值所在节点，返回最小值，递归算法
     *
     * @param node 节点
     * @return 最小值
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
     * 从二分搜索树中删除最大值所在节点，返回最大值
     *
     * @return 最大值
     */
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    /**
     * 以node为根的二分搜索树中删除最大值所在节点，返回最大值，递归算法
     *
     * @param node 节点
     * @return 最大值
     */
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMin(node.right);
        return node;
    }

    /**
     * 删除任意的元素
     *
     * @param e 元素
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 以node为根的二分搜索树中删除任意的元素，递归算法
     *
     * @param node 节点
     * @return 最小值
     */
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 节点两边都有孩子节点
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;

            return successor;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateString(root, 0, res);
        return res.toString();
    }

    /**
     * 生成以node为根节点，深度为depth的描述二叉树的字符串
     *
     * @param node  节点
     * @param depth 深度
     * @param res
     */
    private void generateString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateString(depth)).append("null\n");
            return;
        }

        res.append(generateString(depth)).append(node.e).append("\n");
        generateString(node.left, depth + 1, res);
        generateString(node.right, depth + 1, res);
    }

    private String generateString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();

        tree.add(123);
        tree.add(12);
        tree.add(423);
        tree.add(423);
        tree.add(423);
        tree.add(123);
        tree.add(2345);
        tree.add(2344);
        tree.add(1);

        // 前序遍历
        tree.inOrder();

        // 层次遍历
        tree.levelOrder();

        // 获取最小值
        Integer minimum = tree.minimum();
        System.out.println("最小值：" + minimum);

        // 获取最大值
        Integer maximum = tree.maximum();
        System.out.println("最大值：" + maximum);

        // 移除最小值
        Integer minEle = tree.removeMin();
        System.out.println("移除最小值：" + minEle);

        // 移除最小值
        Integer maxEle = tree.removeMax();
        System.out.println("移除最大值：" + maxEle);

        // 移除元素2344
        tree.remove(2344);

        // 遍历
        System.out.println(tree);
    }
}
