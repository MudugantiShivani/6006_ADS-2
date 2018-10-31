import java.util.Scanner;
class AdjacencyMatrix {
    private String[] tokens;
    private int[][] matrix;
    private int vertices;
    private int edges;
    AdjacencyMatrix() {
        edges = 0;
    }
    AdjacencyMatrix(final Scanner scan) {
        vertices = Integer.parseInt(scan.nextLine());
        matrix = new int[vertices][vertices];
        int edge = Integer.parseInt(scan.nextLine());
        tokens = scan.nextLine().split(",");
        for (int i = 0; i < edge; i++) {
            String[] inputs = scan.nextLine().split(" ");
            addEdge(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
    }
    public void addEdge(final int vertexOne, final int vertexTwo) {
        if (vertexOne != vertexTwo) {
            if (!hasEdge(vertexOne, vertexTwo)) {
                matrix[vertexOne][vertexTwo] = 1;
                matrix[vertexTwo][vertexOne] = 1;
                edges++;
            }
        }
    }
    public boolean hasEdge(final int vertexOne, final int vertexTwo) {
        if (matrix[vertexOne][vertexTwo] == 1) {
            return true;
        }
        return false;
    }
    public void printMat() {
        String str = "";
        str += vertices + " vertices, " + edges + " edges" + "\n";
        if (edges > 0) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    str += matrix[i][j] + " ";
                }
                str += "\n";
            }
            System.out.println(str);
        } else {
            str += "No edges";
            System.out.println(str);
        }
    }
}
class AdjacencyList {
    private int vertices;
    private int edges;
    private Bag<Integer>[] adj;
    private String[] tokens;
    AdjacencyList() {
    }
    AdjacencyList(final Scanner scan) {
        vertices = Integer.parseInt(scan.nextLine());
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Integer>();
        }
        int edge = Integer.parseInt(scan.nextLine());
        tokens = scan.nextLine().split(",");
        for (int i = 0; i < edge; i++) {
            String[] inputs = scan.nextLine().split(" ");
            addEdge(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
    }
    public int vertices() {
        return vertices;
    }
    public int edges() {
        return edges;
    }
    public void addEdge(final int vertexOne,
                        final int vertexTwo) {
        if (vertexOne == vertexTwo) {
            return;
        }
        if (!hasEdge(vertexOne, vertexTwo)) {
            edges++;
        }
        adj[vertexOne].add(vertexTwo);
        adj[vertexTwo].add(vertexOne);
    }
    public Iterable<Integer> adj(final int vertex) {
        return adj[vertex];
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
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(vertices + " vertices, " + edges + " edges" + "\n");
        if (edges > 0) {
            for (int i = 0; i < vertices; i++) {
                str.append(tokens[i] + ": ");
                for (int j : adj[i]) {
                    str.append(tokens[j] + " ");
                }
                str.append("\n");
            }
            return str.toString();
        } else {
            str.append("No edges");
            return str.toString();
        }
    }
}
public final class Solution {
    private Solution() {
    }
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String type = scan.nextLine();
        switch (type) {
        case "List":
            AdjacencyList al = new AdjacencyList(scan);
            System.out.println(al);
            break;
        case "Matrix":
            AdjacencyMatrix am = new AdjacencyMatrix(scan);
            am.printMat();
            break;
        default:
            break;
        }
    }
}



