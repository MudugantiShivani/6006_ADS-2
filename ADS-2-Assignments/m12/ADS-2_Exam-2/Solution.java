import java.util.Scanner;
/**
 * Class for solution.
 */
public class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * {main class for solution}.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        // Self loops are not allowed...
        // Parallel Edges are allowed...
        // Take the Graph input here...
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        EdgeWeighted edgeGraph = new EdgeWeighted(vertices);
        for (int i = 0; i < edges; i++) {
            String[] tokens = scan.nextLine().split(" ");
            edgeGraph.addEdge(new Edge(Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                             Double.parseDouble(tokens[2])));
            // edgeGraph.addEdge(newedge);
        }

        String caseToGo = scan.nextLine();
        switch (caseToGo) {
        case "Graph":
            //Print the Graph Object.
            System.out.println(edgeGraph.toString());
            break;

        case "DirectedPaths":
            // Handle the case of DirectedPaths, where two integers are given.
            // First is the source and second is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            
            String[] item = scan.nextLine().split(" ");
            Dijk dijk = new Dijk(edgeGraph, Integer.parseInt(item[0]));
            double destination = dijk.distanceTo(Integer.parseInt(
                                            item[1]));
            if (destination == 0.0) {
                System.out.println("No Path Found.");
            } else {
                System.out.println(destination);
            }
            break;

        case "ViaPaths":
            // Handle the case of ViaPaths, where three integers are given.
            // First is the source and second is the via is the one where
            // path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            System.out.println("No Path Found.");
            break;

        default:
            break;
        }

    }
}
