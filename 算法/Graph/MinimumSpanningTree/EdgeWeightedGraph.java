package Graph.MinimumSpanningTree;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 *  加权无向图
 */
public class EdgeWeightedGraph {
    private final int V;  //顶点总数
    private int E;  //边数
    private Bag<Edge>[] adj; //邻接表

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
        }
    }

    //未完成
    public EdgeWeightedGraph(In in){
        this.V = in.readInt();

    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(Edge e){
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E ++;
    }

    public Iterable<Edge> adj(int v){
        return adj[v];
    }

    /**
     * 返回加权无向图中的所有边
     * @return
     */
    public Iterable<Edge> edges(){
        Bag<Edge> b = new Bag<Edge>();
        for (int v = 0; v < V; v++) {
            for (Edge e : adj[v]) {
                if(e.other(v) > v) b.add(e); //保证只放入边的一个引用
            }
        }
        return b;
    }

}
