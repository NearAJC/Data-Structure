package com.itnear.structure.graph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 描述：单源路径问题
 * 作者：NearJC
 * 时间：2020/02/14
 */
public class SingleSourcePath {

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
     * 存储前一顶点
     */
    private int[] pre;

    public SingleSourcePath(AdjSet adjSet, int source) {
        adjSet.validateVertex(source);
        this.adjSet = adjSet;
        this.source = source;
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
    private void dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;
        for (Integer w : adjSet.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
            }
        }
    }

    /**
     * 判断目标顶点target与源顶点是否相连
     *
     * @param target 目标顶点
     * @return 相连返回true，否则返回false
     */
    public boolean isConnectedTo(int target) {
        adjSet.validateVertex(target);
        return visited[target];
    }

    /**
     * 获取从源顶点到目标顶点target的路径
     *
     * @param target 目标顶点
     * @return 路径
     */
    public Iterable<Integer> path(int target) {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnectedTo(target)) {
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
        SingleSourcePath singleSourcePath = new SingleSourcePath(adjSet, 0);
        System.out.println("0 -> 6：" + singleSourcePath.path(6));
        System.out.println("0 -> 5：" + singleSourcePath.path(5));
    }
}
