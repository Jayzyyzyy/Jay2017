package Grapth.UndirectedGrapths;

/**
 * 深度优先搜索
 */
public class DepthFirstSearch implements Search{
    private boolean[] marked;  //是否连通标记，s-v
    private int count; //连通总数

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        validateVertex(s); //验证s
        dfs(G, s);
    }

    private void dfs(Graph G, int v){
        marked[v] = true;
        count ++;
        for (int w : G.adj(v)) {
            if(!marked[w]) dfs(G, w);
        }
    }

    @Override
    public boolean marked(int w) {
        validateVertex(w); //验证w
        return marked[w];
    }

    @Override
    public int count() {
        return count;
    }

    //验证顶点数值是否有效
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

}
