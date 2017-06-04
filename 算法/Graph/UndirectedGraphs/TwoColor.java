package Graph.UndirectedGraphs;

/**
 *  二分图判断(双色问题)
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;  //顶点颜色
    private boolean isTwoColorable = true; //默认为二分图

    public TwoColor(Graph G){
        marked = new boolean[G.V()];
        color = new boolean[G.V()];

        for (int s = 0; s < G.V(); s++) {
            if(!marked[s]){
                dfs(G, s);
            }
        }
    }

    private void dfs(Graph G, int v){
        marked[v] = true;
        for (int w : G.adj(v)) {
            if(!marked[w]){
                color[w] = !color[v];
                dfs(G, w);
            }else {
                if(color[w] == color[v]) isTwoColorable = false; //相邻情况下已经标记过了，判断是否颜色相同
            }
        }
    }

    public boolean isBipartite(){
        return isTwoColorable;
    }

}
