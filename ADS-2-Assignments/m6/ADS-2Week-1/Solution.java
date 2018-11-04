import java.util.Scanner;
import java.util.Arrays;
class PageRank {
    private static final int THOUSAND = 1000;
    private Digraph digraph;
    private Double[] values;
    private Double[] iter;
    PageRank(final Digraph graph) {
        this.digraph = graph;
        iter = new Double[digraph.V()];
        for (int i = 0; i < digraph.V(); i++) {
            iter[i] = 1.0 / digraph.V();
        }
        for (int i = 0; i < digraph.V(); i++) {
            if (digraph.outdegree(i) == 0) {
                for (int j = 0; j < digraph.V(); j++) {
                    if (j != i) {
                        digraph.addEdge(i, j);
                    }
                }
            }
        }
        values = new Double[digraph.V()];
        for (int i = 0; i < THOUSAND; i++) {
            for (int j = 0; j < digraph.V(); j++) {
                rank(j);
            }
            iter = Arrays.copyOf(values, values.length);
        }
    }
    public double rank(final int one) {
        double rank = 0.0;
        if (digraph.indegree(one) == 0) {
            values[one] = 0.0;
            return values[one];
        }
        for (int adj : digraph.reverse().adj(one)) {
            rank += iter[adj] / digraph.outdegree(adj);
        }
        values[one] = rank;
        return values[one];
    }
    public String toString() {
        String str = "";
        for (int i = 0; i < digraph.V() - 1; i++) {
            str += i + " - " + values[i] + "\n";
        }
        str += digraph.V() - 1 + " - " + values[digraph.V() - 1];
        return str;
    }
}
class WebSearch {

}

public final class Solution {
    private Solution() {

    }
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int input = Integer.parseInt(scan.nextLine());
        Digraph graph = new Digraph(input);
        for (int k = 0; k < input; k++) {
            String[] items = scan.nextLine().split(" ");
            for (int j = 1; j < items.length; j++) {
                graph.addEdge(Integer.parseInt(items[0]),
                              Integer.parseInt(items[j]));
            }
        }
        System.out.println(graph);
        PageRank page = new PageRank(graph);
        System.out.println(page.toString());

        // iterate count of vertices times
        // to read the adjacency list from std input
        // and build the graph


        // Create page rank object and pass the
        //graph object to the constructor

        // print the page rank object

        // This part is only for the final test case

        // File path to the web content
        String file = "WebContent.txt";

        // instantiate web search object
        // and pass the page rank object and the file
        // path to the constructor

        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky
    }
}