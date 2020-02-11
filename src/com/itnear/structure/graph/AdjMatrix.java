package com.itnear.structure.graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 描述：图 - 邻接矩阵
 * 作者：NearJC
 * 时间：2020/02/11
 */
public class AdjMatrix {

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
    private int[][] adj;

    public AdjMatrix(String fileName) {
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            this.V = scanner.nextInt();
            if (this.V < 0) {
                throw new IllegalArgumentException("顶点必须为非负数！");
            }
            this.adj = new int[V][V];
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
                if (adj[a][b] == 1) {
                    throw new IllegalArgumentException("检测到平行边");
                }

                adj[a][b] = 1;
                adj[b][a] = 1;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
        return adj[v][w] == 1;
    }

    /**
     * 根据顶点v获取与它相邻的顶点
     *
     * @param v 顶点
     * @return 相邻的顶点
     */
    public ArrayList<Integer> adj(int v) {
        validateVertex(v);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (adj[v][i] == 1) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 根据顶点v获取顶点的度
     *
     * @param v 顶点
     * @return 顶点的度
     */
    public int degree(int v) {
        return adj(v).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d，E = %d", E, V));
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                sb.append(String.format("%d ", adj[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("g.txt");
        System.out.println(adjMatrix);

        System.out.println("顶点的边：" + adjMatrix.adj(0));
        System.out.println("顶点的度：" + adjMatrix.degree(0));
        System.out.println("顶点是否相邻：" + adjMatrix.hasEdge(0, 1));
    }
}
