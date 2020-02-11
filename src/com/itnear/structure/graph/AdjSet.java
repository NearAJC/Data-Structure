package com.itnear.structure.graph;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 描述：图 - 邻接表(红黑树)
 * 作者：NearJC
 * 时间：2020/02/11
 */
public class AdjSet {

    /**
     * 顶点
     */
    private int V;

    /**
     * 边
     */
    private int E;

    /**
     * 图结构
     */
    private TreeSet<Integer>[] adj;

    public AdjSet(String fileName) {
        File file = new File(fileName);

        try (Scanner scanner = new Scanner(file)) {
            this.V = scanner.nextInt();
            if (this.V < 0) {
                throw new IllegalArgumentException("顶点必须为非负数！");
            }
            adj = new TreeSet[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeSet<>();
            }
            this.E = scanner.nextInt();
            if (this.E < 0) {
                throw new IllegalArgumentException("边数必须为非负数！");
            }
            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);
                if (a == b) {
                    throw new IllegalArgumentException("检测到自环边");
                }
                if (adj[a].contains(b)) {
                    throw new IllegalArgumentException("检测到平行边");
                }

                adj[a].add(b);
                adj[b].add(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取顶点数
     *
     * @return 顶点数
     */
    public int V() {
        return V;
    }

    /**
     * 获取边数
     *
     * @return 边数
     */
    public int E() {
        return E;
    }

    /**
     * 判断两个顶点之间是否有边
     *
     * @param v 顶点
     * @param w 顶点
     * @return 存在返回true，否则返回false
     */
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    /**
     * 根据顶点v获取与它相邻的顶点
     *
     * @param v 顶点
     * @return 相邻的顶点
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * 根据顶点v获取顶点的度
     *
     * @param v 顶点
     * @return 顶点的度
     */
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * 检测顶点合法性
     *
     * @param v 顶点
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + " is invalid");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d，E = %d\n", E, V));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d :", v));
            for (int w : adj[v]) {
                sb.append(String.format("%d ", w));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjSet adjSet = new AdjSet("g.txt");
        System.out.println(adjSet);
        System.out.println("顶点的边：" + adjSet.adj(1));
        System.out.println("顶点的度：" + adjSet.degree(1));
        System.out.println("顶点是否相邻：" + adjSet.hasEdge(1, 5));
    }
}
