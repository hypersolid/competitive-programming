import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {
  static int T, N, M, K;
  static int[][] matrix, visited;
  static int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
  static ArrayDeque<Step> queue = new ArrayDeque<>();

  static class Step {
    public int row, column, step, magic;

    public Step(int row, int column, int magic) {
      this.row = row;
      this.column = column;
      this.magic = magic;
    }
  }

  static void solve() {
    while (!queue.isEmpty()) {
      Step step = queue.pollFirst();
      if (matrix[step.row][step.column] == 1) {
        System.out.println(step.magic == K ? "Impressed" : "Oops!");
        return;
      }
      int magic = 0;
      for (int i = 0; i < directions.length; i++) {
        int row = step.row + directions[i][0];
        int column = step.column + directions[i][1];
        if (matrix[row][column] != -1 && visited[row][column] == 0) magic++;
      }
      magic = magic > 1 ? 1 : 0;

      for (int i = 0; i < directions.length; i++) {
        int row = step.row + directions[i][0];
        int column = step.column + directions[i][1];
        if (matrix[row][column] != -1 && visited[row][column] == 0) {
          visited[row][column] = 1;
          queue.addLast(new Step(row, column, step.magic + magic));
        }
      }
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    T = scanner.nextInt();

    for (int t = 0; t < T; t++) {
      N = scanner.nextInt();
      M = scanner.nextInt();

      queue.clear();
      matrix = new int[N + 2][M + 2];
      visited = new int[N + 2][M + 2];
      for (int n = 0; n < N + 2; n++) {
        for (int m = 0; m < M + 2; m++) {
          if (n == 0 || n == N + 1) matrix[n][m] = -1;
          if (m == 0 || m == M + 1) matrix[n][m] = -1;
        }
      }

      for (int n = 0; n < N; n++) {
        String row = scanner.next();
        for (int m = 0; m < M; m++) {
          switch (row.charAt(m)) {
            case 'M':
              {
                visited[n + 1][m + 1] = 1;
                queue.addLast(new Step(n + 1, m + 1, 0));
                break;
              }
            case 'X':
              {
                matrix[n + 1][m + 1] = -1;
                break;
              }
            case '*':
              {
                matrix[n + 1][m + 1] = 1;
                break;
              }
          }
        }
      }

      K = scanner.nextInt();

      solve();
    }
  }
}
