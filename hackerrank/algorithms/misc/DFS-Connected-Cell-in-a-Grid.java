import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
  private static ArrayDeque<int[]> queue = new ArrayDeque<>();
  static int[][] grid;
  static int n, m;

  static int checkBoundariesAndAddToQueue(int i, int j) {
    if (i < 0 || i >= n || j < 0 || j >= m) {
      return 0;
    }
    if (grid[i][j] == 0) {
      return 0;
    }
    queue.add(new int[] {i, j});
    grid[i][j] = 0;
    return 1;
  }

  static int dfs(int i, int j) {
    int counter = checkBoundariesAndAddToQueue(i, j);

    while (!queue.isEmpty()) {
      int[] pair = queue.remove();
      counter += checkBoundariesAndAddToQueue(pair[0] - 1, pair[1]);
      counter += checkBoundariesAndAddToQueue(pair[0], pair[1] - 1);
      counter += checkBoundariesAndAddToQueue(pair[0] + 1, pair[1]);
      counter += checkBoundariesAndAddToQueue(pair[0], pair[1] + 1);
      counter += checkBoundariesAndAddToQueue(pair[0] - 1, pair[1] - 1);
      counter += checkBoundariesAndAddToQueue(pair[0] + 1, pair[1] + 1);
      counter += checkBoundariesAndAddToQueue(pair[0] - 1, pair[1] + 1);
      counter += checkBoundariesAndAddToQueue(pair[0] + 1, pair[1] - 1);
    }

    return counter;
  }

  // Complete the maxRegion function below.
  static int maxRegion() {
    int result = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        result = Math.max(result, dfs(i, j));
      }
    }
    return result;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    n = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    m = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    grid = new int[n][m];

    for (int i = 0; i < n; i++) {
      String[] gridRowItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int j = 0; j < m; j++) {
        int gridItem = Integer.parseInt(gridRowItems[j]);
        grid[i][j] = gridItem;
      }
    }

    int res = maxRegion();

    bufferedWriter.write(String.valueOf(res));
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
