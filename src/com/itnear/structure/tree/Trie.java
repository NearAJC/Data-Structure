package com.itnear.structure.tree;

import com.itnear.util.FileOperation;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * 描述：字典树
 * 作者：NearJC
 * 时间：2020/02/05
 */
public class Trie {

    /**
     * 节点结构
     */
    private class Node {
        /**
         * 是否为单词
         */
        public boolean isWord;

        /**
         * 下一个节点
         */
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new TreeMap<>();
        }

        public Node() {
            this(false);
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

    public Trie() {
        this.root = new Node();
        this.size = 0;
    }

    /**
     * 向trie中添加一个新的单词word
     *
     * @param word 单词
     */
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            size++;
            cur.isWord = true;
        }
    }

    /**
     * 查询单词word是否在Trie中
     *
     * @param word 单词
     * @return 存在返回true，否则返回false
     */
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    /**
     * 查询是否在trie中有单词以prefix为前缀
     *
     * @param prefix 前缀
     * @return 有返回true，否则返回false
     */
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
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
     * 字典树是否为空
     *
     * @return 空时返回true，否则返回false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            long startTime = System.nanoTime();

            Trie trie = new Trie();
            for (String word : words) {
                trie.add(word);
            }

            for (String word : words)
                trie.contains(word);

            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("Trie: " + time + " s");
        }
    }
}
