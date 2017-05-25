package Grapth.UndirectedGrapths;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 *  无向图
 */
public class Graph {
    private final int V;  //顶点数
    private int E;  //边数
    private Bag<Integer>[] adj; //邻接表

    public Graph(int V){
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");

        this.V = V;this.E = 0;
        adj= (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {   //初始化
            adj[v] = new Bag<Integer>();
        }
    }

    public Graph(In in){
        this(in.readInt());
        this.E = in.readInt();
        if (E < 0) throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
        for (int i = 0; i < E; i++) {  //添加边
            if(in.hasNextLine()){
                int v = in.readInt();
                int w = in.readInt();
                validateVertex(v);  //对输入的顶点做验证
                validateVertex(w);
                addEdge(v, w);
            }
        }
    }

    public void addEdge(int v, int w){
        validateVertex(v);  //验证顶点数值
        validateVertex(w);
        adj[v].add(w);
        adj[w].add(v);
        E ++;
    }

    public int E(){
        return E;
    }

    public int V(){
        return V;
    }

    public Iterable<Integer> adj(int v){
        validateVertex(v);  //验证顶点数值
        return adj[v];
    }

    //计算顶点的度
    public int degree(int v){
        validateVertex(v);
        return adj[v].size();
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    //toString()方法
    public String toString(){
        StringBuilder s = new StringBuilder(V + " vertices, " + E + " edges\n");
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for(int w : this.adj(v)){
                s.append(w).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

}
