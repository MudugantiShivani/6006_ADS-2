import java.util.Scanner;
class Percolation {
    private int vertices;
    public boolean percolation(final boolean[][] grid, final Graph graphObj, final int vertices1) {
        vertices = vertices1;
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (grid[i][j]) {
                    int tmp = simplify(i, j);
                    if (i == 0) {

                        graphObj.addEdge(tmp, vertices * vertices);
                    }
                    if (i == vertices - 1) {
                        graphObj.addEdge(tmp, vertices * vertices + 1);
                    }
                    if (i - 1 >= 0
                        && grid[i - 1][j]) {
                        graphObj.addEdge(tmp, simplify(i - 1, j));
                    }
                    if (i + 1 < vertices
                        && grid[i + 1][j]) {
                        graphObj.addEdge(tmp, simplify(i + 1, j));
                    }
                    if (j - 1 >= 0
                        && grid[i][j - 1]) {
                        graphObj.addEdge(tmp, simplify(i, j - 1));
                    }
                    if (j + 1 < vertices
                        && grid[i][j + 1]) {
                        graphObj.addEdge(tmp, simplify(i, j + 1));
                    }

                }
            }
        }
        DepthFirstPaths dp = new DepthFirstPaths(graphObj, vertices * vertices);
        return dp.hasPathTo(vertices * vertices + 1);
    }
    public int simplify(final int row, final int column) {
        return row * vertices + column;
    }
}
final class Solution {
    private Solution() {

    }
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        Percolation obj = new Percolation();
        boolean[][] grid = new boolean[vertices][vertices];
        Graph gObj = new Graph(vertices * vertices + 2);
        try {
            while (scan.hasNext()) {
                int row = scan.nextInt(), col = scan.nextInt();
                grid[row - 1][col - 1] = true;

            }
        } catch (Exception e) {

        } finally {
            System.out.println(obj.percolation(grid, gObj, vertices));
        }
    }
}