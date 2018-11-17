import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;
public class Solution {

	// Don't modify this method.
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String cases = scan.nextLine();

		switch (cases) {
		case "loadDictionary":
			// input000.txt and output000.txt
			BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
			while (scan.hasNextLine()) {
				String key = scan.nextLine();
				System.out.println(hash.get(key));
			}
			break;

		case "getAllPrefixes":
			// input001.txt and output001.txt
			T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
			while (scan.hasNextLine()) {
				String prefix = scan.nextLine();
				for (String each : t9.getAllWords(prefix)) {
					System.out.println(each);
				}
			}
			break;

		case "potentialWords":
			// input002.txt and output002.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			int count = 0;
			while (scan.hasNextLine()) {
				String t9Signature = scan.nextLine();
				for (String each : t9.potentialWords(t9Signature)) {
					count++;
					System.out.println(each);
				}
			}
			if (count == 0) {
				System.out.println("No valid words found.");
			}
			break;

		case "topK":
			// input003.txt and output003.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			Bag<String> bag = new Bag<String>();
			int k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				bag.add(line);
			}
			for (String each : t9.getSuggestions(bag, k)) {
				System.out.println(each);
			}

			break;

		case "t9Signature":
			// input004.txt and output004.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			bag = new Bag<String>();
			k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				for (String each : t9.t9(line, k)) {
					System.out.println(each);
				}
			}
			break;

		default:
			break;

		}
	}

	// Don't modify this method.
	public static String[] toReadFile(String file) {
		In in = new In(file);
		return in.readAllStrings();
	}

	public static BinarySearchST<String, Integer> loadDictionary(String file) {
		BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
		// your code goes here
		String[] wordsdictionary = toReadFile(file);
		for(int i = 0; i<wordsdictionary.length; i++) {
			String word = wordsdictionary[i].toLowerCase();
			if(!st.contains(word)) {
				st.put(word, 1);
			} else {
				st.put(word, st.get(word) + 1);
			}
		}
		return st;
	}

}

class T9 {
	TST<Integer> tst;
	public T9(BinarySearchST<String, Integer> st) {
		// your code goes here
		tst = new TST();
		for(String str : st.keys()) {
			tst.put(str, st.get(str));
		}
	}

	// get all the prefixes that match with given prefix.
	public Iterable<String> getAllWords(String prefix) {
		// your code goes here
		return tst.keysWithPrefix(prefix);
	}

	public Iterable<String> potentialWords(String t9Signature) {
		// your code goes here
		ArrayList<String> arrays1 = new ArrayList<>();
        for (String wrd : tst.keys()) {
            String[] strarray = wrd.split("");
            String txt = "";
            for (String ch : strarray) {
                if (ch.equals("a") || ch.equals("b") || ch.equals("c")) {
                    txt = txt + "2";
                }
                if (ch.equals("d") || ch.equals("e") || ch.equals("f")) {
                    txt = txt + "3";
                }
                if (ch.equals("g") || ch.equals("h") || ch.equals("i")) {
                    txt = txt + "4";
                }
                if (ch.equals("j") || ch.equals("k") || ch.equals("l")) {
                    txt = txt + "5";
                }
                if (ch.equals("m") || ch.equals("n") || ch.equals("o")) {
                    txt = txt + "6";
                }
                if (ch.equals("p") || ch.equals("q") || ch.equals("r")
                        || ch.equals("s")) {
                    txt = txt + "7";
                }
                if (ch.equals("t") || ch.equals("u") || ch.equals("v")) {
                    txt = txt + "8";
                }
                if (ch.equals("w") || ch.equals("x") || ch.equals("y")
                        || ch.equals("z")) {
                    txt = txt + "9";
                }
            }
            if (txt.equals(t9Signature)) {
                arrays1.add(wrd);
            }
        }
        return arrays1;
	}

	// return all possibilities(words), find top k with highest frequency.
	public Iterable<String> getSuggestions(Iterable<String> words, int k) {
		// your code goes here
        MaxPQ<Integer> max = new MaxPQ<>();
        for (String key : words) {
            max.insert((Integer)tst.get(key));
        }
        TreeSet<String> suggets = new TreeSet<String>();
        for(int i=0 ;i<k; i++) {
        	int value = max.delMax();
        	for(String word : words) {
        		if(value == (Integer)tst.get(word)) {
        			suggets.add(word);
        		}
        	}
        } 
        return suggets;
	}

	// final output
	// Don't modify this method.
	public Iterable<String> t9(String t9Signature, int k) {
		return getSuggestions(potentialWords(t9Signature), k);
	}
}
