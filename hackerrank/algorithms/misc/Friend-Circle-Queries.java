import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
  static HashMap<Integer, Integer> roots, counts;

  static int merge(int pointA, int pointB) {
    int graphA = getRoot(pointA);
    int graphB = getRoot(pointB);

    if (graphA == graphB) return graphA;

    counts.put(graphA, counts.get(graphA) + counts.get(graphB));
    roots.put(graphB, graphA);
    counts.remove(graphB);

    return graphA;
  }

  static void initPoint(int point) {
    if (roots.containsKey(point)) return;

    roots.put(point, point);
    counts.put(point, 1);
  }

  static int getRoot(int point) {
    int parent = roots.get(point);
    if (parent == point) {
      return point; // we are in root
    }

    int graph = getRoot(parent);
    roots.put(point, graph);
    return graph;
  }

  // Complete the maxCircle function below.
  static int[] maxCircle(int[][] queries) {
    roots = new HashMap<>();
    counts = new HashMap<>();
    int[] ans = new int[queries.length];
    int max = 0;

    for (int i = 0; i < queries.length; i++) {
      int pointA = queries[i][0];
      int pointB = queries[i][1];

      initPoint(pointA);
      initPoint(pointB);

      int graph = merge(pointA, pointB);

      max = Math.max(max, counts.get(graph));
      ans[i] = max;
    }

    return ans;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int q = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    int[][] queries = new int[q][2];

    for (int i = 0; i < q; i++) {
      String[] queriesRowItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int j = 0; j < 2; j++) {
        int queriesItem = Integer.parseInt(queriesRowItems[j]);
        queries[i][j] = queriesItem;
      }
    }

    int[] ans = maxCircle(queries);

    for (int i = 0; i < ans.length; i++) {
      bufferedWriter.write(String.valueOf(ans[i]));

      if (i != ans.length - 1) {
        bufferedWriter.write("\n");
      }
    }

    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
