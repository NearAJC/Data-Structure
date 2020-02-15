package com.itnear.structure.graph;

/**
 * 描述：二分图检测
 * 作者：NearJC
 * 时间：2020/02/15
 */
public class BinaryDetection {

    /**
     * 存储顶点是否被访问
     */
    private boolean[] visited;

    /**
     * 图
     */
    private AdjSet adjSet;

    /**
     * 是否有环
     */
    private int[] colors;

    /**
     * 是否是二分图
     */
    private boolean isBinary = true;

    public BinaryDetection(AdjSet adjSet) {
        this.adjSet = adjSet;
        this.visited = new boolean[adjSet.V()];
        this.colors = new int[adjSet.V()];
        for (int i = 0; i < adjSet.V(); i++) {
            colors[i] = -1;
        }
        for (int v = 0; v < adjSet.V(); v++) {
            if (!visited[v]) {
                if (!dfs(v, 0)) {
                    isBinary = false;
                    break;
                }
            }
        }
    }

    /**
     * 深度优先先序遍历
     *
     * @param v 顶点
     */
    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (int w : adjSet.adj(v)) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) {
                    return false;
                }
            } else if (colors[w] == colors[v]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断图是否为二分图
     *
     * @return 是二分图返回true，否则返回false
     */
    public boolean isBinary() {
        return isBinary;
    }

    public static void main(String[] args) {
        AdjSet adjSet = new AdjSet("k2.txt");
        BinaryDetection binaryDetection = new BinaryDetection(adjSet);
        System.out.println("图是否为二分图：" + binaryDetection.isBinary());
    }
}
