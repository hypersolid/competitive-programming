import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

  // Complete the riddle function below.
  static long[] riddle(long[] arr) {
    int N = arr.length;
    long[] result = new long[N];
    long[] span = new long[N];

    ArrayDeque<Long> deckValues = new ArrayDeque<>();
    ArrayDeque<Long> deckIndexes = new ArrayDeque<>();
    deckIndexes.push(-1L);

    // count number of ge elements to the left
    for (int i = 0; i < N; i++) {
      while (!deckValues.isEmpty() && deckValues.peek() >= arr[i]) {
        deckValues.pop();
        deckIndexes.pop();
      }
      span[i] = i - deckIndexes.peek() - 1;
      deckValues.push(arr[i]);
      deckIndexes.push((long) i);
    }

    // count number of ge elements to the right
    deckValues.clear();
    deckIndexes.clear();
    deckIndexes.push((long) N);
    for (int i = N - 1; i >= 0; i--) {
      while (!deckValues.isEmpty() && deckValues.peek() >= arr[i]) {
        deckValues.pop();
        deckIndexes.pop();
      }
      span[i] += deckIndexes.peek() - i - 1;
      deckValues.push(arr[i]);
      deckIndexes.push((long) i);
    }

    // fill results
    for (int i = 0; i < N; i++) {
      result[(int) span[i]] = Math.max(result[(int) span[i]], arr[i]);
    }

    // fill the gaps
    for (int i = N - 2; i >= 0; i--) {
      result[i] = Math.max(result[i], result[i + 1]);
    }

    return result;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    long[] arr = new long[n];

    String[] arrItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < n; i++) {
      long arrItem = Long.parseLong(arrItems[i]);
      arr[i] = arrItem;
    }

    long[] res = riddle(arr);

    for (int i = 0; i < res.length; i++) {
      bufferedWriter.write(String.valueOf(res[i]));

      if (i != res.length - 1) {
        bufferedWriter.write(" ");
      }
    }

    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
