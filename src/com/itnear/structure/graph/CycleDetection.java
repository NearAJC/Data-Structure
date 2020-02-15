package com.itnear.structure.graph;

/**
 * 描述：检测环
 * 作者：NearJC
 * 时间：2020/02/15
 */
public class CycleDetection {

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
    private boolean hasCycle;

    public CycleDetection(AdjSet adjSet) {
        this.adjSet = adjSet;
        this.visited = new boolean[adjSet.V()];
        for (int v = 0; v < adjSet.V(); v++) {
            if (!visited[v]) {
                if (dfs(v, v)) {
                    hasCycle = true;
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
    private boolean dfs(int v, int parent) {
        visited[v] = true;
        for (int w : adjSet.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) {
                    return true;
                } else if (w != parent) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 返回图中是否有环
     *
     * @return 有环返回true，否则返回false
     */
    public boolean isHasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        AdjSet adjSet = new AdjSet("k.txt");
        CycleDetection cycleDetection = new CycleDetection(adjSet);
        System.out.println("是否有环：" + cycleDetection.isHasCycle());
    }
}
