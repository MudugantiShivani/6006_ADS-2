import java.util.Scanner;
class DirectedCycle {
    private boolean[] marked;
    private boolean[] onStack;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    DirectedCycle(final DiGraph g) {
        marked  = new boolean[g.vertices()];
        onStack = new boolean[g.vertices()];
        edgeTo  = new int[g.vertices()];
        for (int vertices = 0; vertices < g.vertices(); vertices++) {
            if (!marked[vertices] && cycle == null) {
                dfs(g, vertices);
            }
        }
    }
    private void dfs(final DiGraph g, final int vertices) {
        onStack[vertices] = true;
        marked[vertices] = true;
        for (int w : g.adj(vertices)) {
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = vertices;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = vertices; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(vertices);
            }
        }
        onStack[vertices] = false;

    }
    public boolean hasCycle() {
        return cycle != null;
    }
}
class DiGraph {
    private int vertices;
    private int edges;
    private int edge = 0;
    private int indegree = 0;
    private Bag<Integer>[] adj;
    DiGraph(final Scanner scan) {
        vertices = Integer.parseInt(scan.nextLine());
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Integer>();
        }
        edges = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < edges; i++) {
            addEdge(scan.nextInt(), scan.nextInt());
        }
    }
    public void addEdge(final int vertexOne,
                        final int vertexTwo) {
        if (!hasEdge(vertexOne, vertexTwo)) {
            edge++;
        }
        adj[vertexTwo].add(vertexOne);
    }
    public boolean hasEdge(final int vertexOne,
                           final int vertexTwo) {
        for (int each : adj(vertexOne))  {
            if (each == vertexTwo) {
                return true;
            }
        }
        return false;
    }
    public int vertices() {
        return vertices;
    }
    public int edges() {
        return edges;
    }
    public Iterable<Integer> adj(final int vertex) {
        return adj[vertex];
    }
}
final class Solution {
    private Solution() {

    }
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        DiGraph g = new DiGraph(scan);
        DirectedCycle obj = new DirectedCycle(g);
        if (obj.hasCycle()) {
            System.out.println("Cycle exists.");
        } else {
            System.out.println("Cycle doesn't exists.");
        }
    }
}