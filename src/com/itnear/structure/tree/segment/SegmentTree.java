package com.itnear.structure.tree.segment;

/**
 * 描述：线段树
 * 作者：NearJC
 * 时间：2020/02/05
 */
public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;

        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     * 在根节点索引treeIndex的位置创建表示区间[l...r]的线段树
     *
     * @param treeIndex 根节点索引
     * @param l         左端点
     * @param r         右端点
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /**
     * 返回区间[queryL,queryR]的值
     *
     * @param queryL 查询左端点
     * @param queryR 查询右端点
     * @return 区间值
     */
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("索引异常");
        }

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在根节点索引treeIndex的线段树中[l...r]的范围里，搜索区间[queryL,queryR]
     *
     * @param treeIndex 根节点索引
     * @param l         左端点
     * @param r         右端点
     * @param queryL    查询左端点
     * @param queryR    查询右端点
     * @return 区间值
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }

        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    /**
     * 根据索引index设置值e
     *
     * @param index 索引
     * @param e     值
     */
    public void set(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("数组下标越界");
        }

        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    /**
     * 在根节点索引treeIndex的线段树中[l...r]的范围里，根据索引index设置值e
     *
     * @param treeIndex 根节点索引
     * @param l         左端点
     * @param r         右端点
     * @param index     索引
     * @param e         值
     */
    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (index >= mid + 1) {
            set(rightTreeIndex, mid + 1, r, index, e);
        } else {
            set(leftTreeIndex, l, mid, index, e);
        }
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /**
     * 获取数组元素个数
     *
     * @return 元素个数
     */
    public int getSize() {
        return data.length;
    }

    /**
     * 根据索引获取元素
     *
     * @param index 索引
     * @return 元素
     */
    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("数组下标越界");
        }
        return data[index];
    }

    /**
     * 获取右子树索引
     *
     * @param index 父索引
     * @return 右子树索引
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 获取左子树索引
     *
     * @param index 父索引
     * @return 左子树索引
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                res.append(tree[i]);
            } else {
                res.append("null");
            }

            if (i != tree.length - 1) {
                res.append(", ");
            }
        }

        return res.toString();
    }

    public static void main(String[] args) {
        Integer[] numbers = {-2, 0, 3, -5, 2, -1};

        SegmentTree<Integer> segmentTree = new SegmentTree<>(numbers, new Merger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                return a + b;
            }
        });
        System.out.println(segmentTree);

        // 修改
        segmentTree.set(0, 100);
        System.out.println(segmentTree);

        // 查询[0,4]区间的值
        Integer result = segmentTree.query(0, 4);
        System.out.println("区间[0,4]的和为：" + result);
    }
}
