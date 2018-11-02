import java.util.Scanner;
class Bipartite {
    private boolean[] marked;
    private boolean[] color;
    private boolean flag;
    private int[] edgeTo;
    Bipartite(final Graph g) {
        int vertices = g.vertices();
        marked = new boolean[vertices];
        color = new boolean[vertices];
        edgeTo = new int[vertices];
        flag = true;
        for (int i = 0; i < vertices; i++) {
            if (!marked[i]) {
                dfs(g, i);
            }
        }
    }
    public void dfs(final Graph g, final int vertices) {
        marked[vertices] = true;
        for (int w : g.adj(vertices)) {
            if (!flag) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = vertices;
                color[w] = !color[vertices];
                dfs(g, w);
            } else if (color[w] == color[vertices]) {
                flag = false;
            }
        }
    }
    public boolean isBipartite() {
        return flag;
    }
}
final class Solution {
    private Solution() {

    }
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        Graph g = new Graph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] t = scan.nextLine().split(" ");
            g.addEdge(Integer.parseInt(
                          t[0]), Integer.parseInt(t[1]));
        }
        Bipartite obj = new Bipartite(g);
        if (obj.isBipartite()) {
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph is not a bipartite");
        }
    }
}