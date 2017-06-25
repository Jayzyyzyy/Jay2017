package Graph.DirectedGraphs.Ex;

import Graph.DirectedGraphs.Digraph;
import Graph.DirectedGraphs.KosarajuSCC;
import edu.princeton.cs.algs4.In;

/**
 *  MediumDG中的强连通分量数  10个
 */
public class MediumDGStrongComponent {
    public static void main(String[] args) {

        Digraph G = new Digraph(new In(args[0]));

        KosarajuSCC scc = new KosarajuSCC(G);


        System.out.println(scc.count());

    }
}
