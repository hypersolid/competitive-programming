import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
  static void union(int[] forest, int node1, int node2) {
    // if (node1 > node2) {
    //     int tmp = node1;
    //     node1 = node2;
    //     node2 = tmp;
    // }
    forest[find(forest, node2)] = find(forest, node1);
  }

  static int find(int[] forest, int node) {
    if (node != forest[node]) {
      forest[node] = find(forest, forest[node]);
    }
    return forest[node];
  }

  // Complete the roadsAndLibraries function below.
  static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] citiesMatrix) {
    if (c_lib <= c_road) {
      // it's cheaper to build libraries in each city
      return (long) c_lib * (long) n;
    }

    // find trees
    int[] forest = new int[n];
    for (int i = 0; i < n; i++) {
      forest[i] = i;
    }

    for (int[] pair : citiesMatrix) {
      union(forest, pair[0] - 1, pair[1] - 1);
    }

    // count the costs
    long cost = 0;
    for (int i = 0; i < n; i++) {
      if (find(forest, i) == i) {
        cost += (long) c_lib;
      } else {
        cost += (long) c_road;
      }
    }
    return cost;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int q = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int qItr = 0; qItr < q; qItr++) {
      String[] nmC_libC_road = scanner.nextLine().split(" ");

      int n = Integer.parseInt(nmC_libC_road[0]);

      int m = Integer.parseInt(nmC_libC_road[1]);

      int c_lib = Integer.parseInt(nmC_libC_road[2]);

      int c_road = Integer.parseInt(nmC_libC_road[3]);

      int[][] cities = new int[m][2];

      for (int i = 0; i < m; i++) {
        String[] citiesRowItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int j = 0; j < 2; j++) {
          int citiesItem = Integer.parseInt(citiesRowItems[j]);
          cities[i][j] = citiesItem;
        }
      }

      long result = roadsAndLibraries(n, c_lib, c_road, cities);

      bufferedWriter.write(String.valueOf(result));
      bufferedWriter.newLine();
    }

    bufferedWriter.close();

    scanner.close();
  }
}
