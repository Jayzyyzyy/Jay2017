package Graph.UndirectedGraphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 广度优先搜索（队列），求最短路径
 */
public class BreadthFirstPaths {
    private boolean[] marked;  //到达该顶点的最短路径已知吗？或者是否连通，最短路尽已知
    private int[] edgeTo;   //到达该顶点最短路径上的最后一个顶点
    private final int s;   //起点

    public BreadthFirstPaths(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        validateVertex(s);
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s){
        Queue<Integer> queue = new Queue<Integer>();
        marked[s] = true;   //标记起点
        queue.enqueue(s);  //起点入队列

        while(!queue.isEmpty()){
            int v = queue.dequeue();  //出队列
            for (int w : G.adj(v)) {
                if(!marked[w]){    //如果相邻的顶点未被标记
                    edgeTo[w] = v;   //保存最短路径的最后一条边
                    marked[w] = true; //标记连通
                    queue.enqueue(w); //入队列
                }
            }
        }
    }

    public boolean hasPathTo(int v){
        validateVertex(v);
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        validateVertex(v);
        if(!marked[v]) return null; //不连通

        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        BreadthFirstPaths dfs = new BreadthFirstPaths(G, s);  //图，起点

        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {  //s与v连通
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : dfs.pathTo(v)) {   //stack
                    if (x == s) StdOut.print(x);
                    else        StdOut.print("-" + x);
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }

        }
    }

}
