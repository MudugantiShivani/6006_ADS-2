import java.util.Scanner;
import java.util.Arrays;
/**
 * Class for lsd.
 */
class LSD {
    /**
     * {Here we perform the sorting of string arrays in this method}.
     * the time complexity here is O(w*N)
     *
     * @param      a     {array of the strings }
     * @param      w     { fixed length of the strings}
     */
    public void sort(final String[] a, final int w) {
        int n = a.length;
        final int extendedASCII = 256;
        String[] aux = new String[n];
        for (int d = w - 1; d >= 0; d--) {
            int[] count = new int[extendedASCII + 1];
            for (int i = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            for (int r = 0; r < extendedASCII; r++) {
                count[r + 1] += count[r];
            }
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }
}
/**
 * {Solution class}.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * {This is the main method}.
     * The time complexity is (N+(N*w)) as we take N inputs and each time 
     * we call LSD function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = Integer.parseInt(sc.nextLine());
        String[] array = new String[count];
        for (int i = 0; i < count; i++) {
            array[i] = sc.nextLine();
        }
        LSD obj = new LSD();
        obj.sort(array, array[0].length());
        System.out.println(Arrays.toString(array));
    }
}