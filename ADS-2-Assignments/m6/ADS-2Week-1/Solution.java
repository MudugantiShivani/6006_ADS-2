import java.util.Scanner;
class PageRank {
	Digraph dg;
	private BinarySearchST<Integer, Double> bst;
    PageRank(final Digraph dgh) {
        this.dg = dgh;
        bst = new BinarySearchST<Integer, Double>();
    }
    public void display() {
        System.out.println(dg.toString());
        for (int i : bst.keys()) {
            String str = "";
            str = String.valueOf(i) + " - " + bst.get(i);
            System.out.println(str);
        }
    }
}

class WebSearch {

}


final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //Empty constructor.
    }
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        // read the first line of the input to get the number of vertices
        int numberofvertices = Integer.parseInt(sc.nextLine());
        Digraph dg = new Digraph(numberofvertices);
        // iterate count of vertices times
        // to read the adjacency list from std input
        // and build the graph
        for (int i = 0; i < numberofvertices; i++) {
            String[] node = sc.nextLine().split(" ");
            for (int j = 1; j < node.length; j++) {
                dg.addEdge(Integer.parseInt(node[0]), Integer.parseInt(node[j]));   
            }
        }
        // Create page rank object and pass the graph object to the constructor
        PageRank pr = new PageRank(dg);
        // print the page rank object
        
        // This part is only for the final test case
        
        // File path to the web content
        String file = "WebContent.txt";
        
        // instantiate web search object
        // and pass the page rank object and the file path to the constructor
        
        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky
        
    }
}