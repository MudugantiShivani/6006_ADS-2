import java.util.Scanner;
public final class Solution {
    private Solution() {

    }
    public static void main(final String[] args) {
        String[] words = loadWords();
        Scanner scan = new Scanner(System.in);
        String token = scan.nextLine();
        TST tst = new TST();
        for (String each : words) {
            for (int i = 0; i < each.length(); i++) {
                tst.put(each.substring(i, each.length()), 0);
            }
        }
        System.out.println(tst.keysWithPrefix(token));
    }
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}
class TST<Value> {
    private int n;
    private Node<Value> root;
    class Node<Value> {
        private char character;
        private Node<Value> left;
        private Node<Value> middle;
        private Node<Value> right;
        private Value value;
    }
    TST() {

    }
    public boolean contains(final String one) {
        return get(one) != null;
    }
    public Value get(final String one) {
        Node<Value> node = get(root, one, 0);
        if (node == null) {
            return null;
        }
        return node.value;
    }
    public Node<Value> get(final Node<Value> node,
        final String one, final int d) {
        if (node == null) {
            return null;
        }
        char ch = one.charAt(d);
        if (ch < node.character) {
            return get(node.left,  one, d);
        } else if (ch > node.character) {
            return get(node.right, one, d);
        } else if (d < one.length() - 1) {
            return get(node.middle, one, d + 1);
        } else {
            return node;
        }
    }
    public void put(final String one, final Value value) {
        if (!contains(one)) {
            n++;
        }
        root = put(root, one, value, 0);
    }
    public Node<Value> put(final Node<Value> node,
        final String one, final Value value,
        final int d) {
        Node node1 = node;
        char ch = one.charAt(d);
        if (node1 == null) {
            node1 = new Node<Value>();
            node1.character = ch;
        }
        if (ch < node1.character) {
            node1.left  = put(node1.left,  one, value, d);
        } else if (ch > node1.character) {
            node1.right = put(node1.right, one, value, d);
        } else if (d < one.length() - 1) {
            node1.middle   = put(node1.middle, one, value, d + 1);
        } else {
            node1.value   = value;
        }
        return node1;
    }
    public Iterable<String> keysWithPrefix(final String one) {
        Queue<String> que = new Queue<String>();
        Node<Value> x = get(root, one, 0);
        if (x == null) {
            return que;
        }
        if (x.value != null) {
            que.enqueue(one);
        }
        collect(x.middle, new StringBuilder(one), que);
        return que;
    }
    public void collect(final Node<Value> node,
        final StringBuilder one, final Queue<String> que) {
        if (node == null) {
            return;
        }
        collect(node.left,  one, que);
        if (node.value != null) {
            que.enqueue(one.toString()
            + node.character);
        }
        collect(node.middle, one.append(
            node.character), que);
        one.deleteCharAt(one.length() - 1);
        collect(node.right, one, que);
    }
}