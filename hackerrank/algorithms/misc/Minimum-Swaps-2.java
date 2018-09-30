import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        int N = arr.length;
        HashMap<Integer, Integer> positions = new HashMap<Integer, Integer>(10000);
        int swaps = 0;
        for (int i = 0; i < N; i++) positions.put(arr[i]-1, i);
        for (int i = 0; i < N-1; i++) {
            if (arr[i] == i + 1) continue;

            int val1 = arr[i];
            int val2 = arr[positions.get(i)];

            int pos1 = i;
            int pos2 = positions.get(i);

            arr[pos1] = val2;
            arr[pos2] = val1;

            positions.put(val1-1, pos2);
            positions.put(val2-1, pos1);
            swaps++;
        }
        return swaps;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
