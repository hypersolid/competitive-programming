import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

  // Complete the minimumBribes function below.
  static void minimumBribes(int[] q) {
    int minBribes = 0;
    for (int i = 0; i < q.length; i++) {
      if (q[i] - 2 > i + 1) {
        System.out.println("Too chaotic");
        return;
      }
      for (int j = i - 1; j >= q[i] - 2 && j >= 0; j--) {
        if (q[j] > q[i]) minBribes++;
      }
    }
    System.out.println(minBribes);
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    int t = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int tItr = 0; tItr < t; tItr++) {
      int n = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int[] q = new int[n];

      String[] qItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < n; i++) {
        int qItem = Integer.parseInt(qItems[i]);
        q[i] = qItem;
      }

      minimumBribes(q);
    }

    scanner.close();
  }
}
