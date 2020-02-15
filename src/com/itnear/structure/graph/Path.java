package com.itnear.structure.graph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 描述：目标路径问题
 * 作者：NearJC
 * 时间：2020/02/15
 */
public class Path {

    /**
     * 图
     */
    private AdjSet adjSet;

    /**
     * 存储顶点是否被访问
     */
    private boolean[] visited;

    /**
     * 源顶点
     */
    private int source;

    /**
     * 目标路径
     */
    private int target;

    /**
     * 存储前一顶点
     */
    private int[] pre;

    public Path(AdjSet adjSet, int source, int target) {
        adjSet.validateVertex(source);
        adjSet.validateVertex(target);

        this.adjSet = adjSet;
        this.source = source;
        this.target = target;

        this.visited = new boolean[adjSet.V()];
        this.pre = new int[adjSet.V()];
        for (int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }
        dfs(source, source);
    }

    /**
     * 深度优先遍历
     *
     * @param v      顶点
     * @param parent 前一顶点
     */
    private boolean dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;
        if (v == target) {
            return true;
        }
        for (Integer w : adjSet.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断目标顶点target与源顶点是否相连
     *
     * @return 相连返回true，否则返回false
     */
    public boolean isConnected() {
        return visited[target];
    }

    /**
     * 获取从源顶点到目标顶点target的路径
     *
     * @return 路径
     */
    public Iterable<Integer> path() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnected()) {
            return res;
        }
        int cur = target;
        while (cur != source) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(source);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        AdjSet adjSet = new AdjSet("k.txt");
        Path path = new Path(adjSet, 0,5);
        System.out.println("0 -> 6是否相连：" + path.isConnected());
        System.out.println("0 -> 6：" + path.path());
    }
}
