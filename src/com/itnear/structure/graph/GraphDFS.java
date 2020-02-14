package com.itnear.structure.graph;

import java.util.ArrayList;

/**
 * 描述：实现深度优先遍历
 * 作者：NearJC
 * 时间：2020/02/11
 */
public class GraphDFS {

    /**
     * 存储先序遍历的结果
     */
    private ArrayList<Integer> pre = new ArrayList<>();

    /**
     * 存储后序遍历的结果
     */
    private ArrayList<Integer> post = new ArrayList<>();

    /**
     * 存储顶点是否被访问
     */
    private int[] visited;

    /**
     * 图
     */
    private AdjSet adjSet;

    /**
     * 联通分量数量
     */
    private int connectedCount;

    public GraphDFS(AdjSet adjSet) {
        this.adjSet = adjSet;
        this.visited = new int[adjSet.V()];
        for (int i = 0; i < adjSet.V(); i++) {
            this.visited[i] = -1;
        }
        for (int v = 0; v < adjSet.V(); v++) {
            if (visited[v] == -1) {
                dfs(v, connectedCount);
                connectedCount++;
            }
        }
    }

    /**
     * 深度优先先序遍历
     *
     * @param v 顶点
     */
    private void dfs(int v, int connectedId) {
        visited[v] = connectedId;
        pre.add(v);
        for (int w : adjSet.adj(v)) {
            if (visited[w] == -1) {
                dfs(w, connectedId);
            }
        }
        post.add(v);
    }

    /**
     * 返回先序遍历顶点
     *
     * @return 先序顶点
     */
    public Iterable<Integer> pre() {
        return pre;
    }

    /**
     * 返回后序遍历顶点
     *
     * @return 后序顶点
     */
    public Iterable<Integer> post() {
        return post;
    }

    /**
     * 获取联通分量数量
     *
     * @return 联通分量数量
     */
    public int getConnectedCount() {
        return connectedCount;
    }

    /**
     * 判断两个顶点v和w是否相连
     *
     * @param v 顶点
     * @param w 顶点
     * @return 相连返回true，否则返回false
     */
    public boolean isConnected(int v, int w) {
        adjSet.validateVertex(v);
        adjSet.validateVertex(w);
        return visited[v] == visited[w];
    }

    /**
     * 获取图中所有的联通分量
     *
     * @return 联通分量集合
     */
    public ArrayList<Integer>[] components() {
        ArrayList<Integer>[] res = new ArrayList[connectedCount];
        for (int i = 0; i < connectedCount; i++) {
            res[i] = new ArrayList<>();
        }
        for (int v = 0; v < adjSet.V(); v++) {
            res[visited[v]].add(v);
        }
        return res;
    }

    public static void main(String[] args) {
        AdjSet adjSet = new AdjSet("k.txt");
        GraphDFS graphDFS = new GraphDFS(adjSet);
        final Iterable<Integer> pre = graphDFS.pre();
        final Iterable<Integer> post = graphDFS.post();
        System.out.println("深度优先先序遍历结果：" + pre);
        System.out.println("深度优先后序遍历结果：" + post);
        System.out.println("联通分量数量：" + graphDFS.getConnectedCount());
        System.out.println("判断0和6顶点是否相连：" + graphDFS.isConnected(0, 6));

        ArrayList<Integer>[] comp = graphDFS.components();
        for (int connectedId = 0; connectedId < comp.length; connectedId++) {
            System.out.print(connectedId + "：");
            for (Integer w : comp[connectedId]) {
                System.out.print(w + " ");
            }
            System.out.println();
        }
    }
}
