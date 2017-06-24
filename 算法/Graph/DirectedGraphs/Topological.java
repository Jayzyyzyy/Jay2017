package Graph.DirectedGraphs;

import edu.princeton.cs.algs4.StdOut;

/**
 *  有向无环图————拓扑排序
 */
public class Topological {
    private Iterable<Integer> order; //顶点的拓扑排序

    public Topological(Digraph G){
        DirectedCycle cycleFinder = new DirectedCycle(G); //先检查是否存在有向环

        if(!cycleFinder.hasCycle()){ //是有向无环图
            DepthFirstOrder dfs = new DepthFirstOrder(G);//再得到逆后序
            order = dfs.reversePost();
        }
    }

    /**
     * 返回拓扑排序顺序
     * @return
     */
    public Iterable<Integer> order(){
        return order;
    }

    /**
     * 是否是有向无环图
     * @return
     */
    public boolean isDAG(){
        return order != null;
    }

    public static void main(String[] args) {
        String filename = args[0]; //文件名
        String separator = args[1]; //分隔符
        SymbolDigraph sg = new SymbolDigraph(filename, separator); //符号有向图

        Topological top = new Topological(sg.G()); //拓扑排序

        for (int v : top.order) {
            StdOut.println(sg.name(v)); //处数拓扑排序(栈),优先级从高到低
        }
    }
}
