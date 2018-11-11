import java.io.*;
import java.util.*;

public class Solution {
  static int T, M, N, maxN = 0;
  static int[] coordinates;
  static int[] dimensions;
  static int[][] walks;
  static int[][] combinations;
  static int[][] routes;
  static final int modulo = (int) Math.pow(10, 9) + 7;

  public static void walk(int n) {
    int[] buffer1 = new int[dimensions[n]];
    int[] buffer2 = new int[dimensions[n]];
    buffer1[coordinates[n]] = 1;

    for (int m = 0; m < M; m++) {
      for (int i = 0; i < dimensions[n]; i++) {
        if (i > 0) buffer2[i - 1] += buffer1[i];
        if (i < dimensions[n] - 1) buffer2[i + 1] += buffer1[i];
      }
      for (int i = 0; i < dimensions[n]; i++) {
        buffer1[i] = buffer2[i];
        walks[n][m] += buffer2[i];
        buffer2[i] = 0;
      }
    }
  }

  public static void combine(int N, int K) {
    int[] factorials = new int[Math.max(N, K) + 1];
    factorials[0] = 1;
    factorials[1] = 1;

    for (int n = 1; n <= Math.max(N, K); n++) {
      factorials[n] *= factorials[n - 1];
      factorials[n] %= modulo;
    }

    combinations = new int[N + 1][K + 1];
    for (int n = 1; n <= N; n++) {
      for (int k = 1; k <= K; k++) {
        if (K < N) {
          combinations[n][k] = factorials[n] / ((factorials[k] * factorials[n - k]) % modulo);
          combinations[n][k] %= modulo;
        } else {
          combinations[n][k] = 0;
        }
      }
    }
  }

  public static void route() {
    for (int m = 0; m < M; m++) routes[0][m] = walks[0][m];

    for (int n = 1; n < N; n++) {
      for (int m = 0; m < M; m++) {
        for (int i = 0; i <= m; i++) {
          routes[n][m] += combinations[m][i] * walks[n - 1][i] * walks[n][m - i] % modulo;
          routes[n][m] %= modulo;
        }
      }
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    T = scanner.nextInt();

    // 1) For each dimension d: use a dynamic programming algorithm to find the number of possible
    // walks for each number of steps. This algorithm focuses on a single dimension. Results saved
    // in, say: result_per_dimension(d, s), d=dimension, s=number_of_steps.
    // 2) Calculate combinations c(n,k) for n = 0 to M, for k = 0 to n. Results saved in say:
    // c(n,k).
    // 3) Use another dynamic programing algorithm to calculate the final results, per dimension and
    // per number of steps. results[d,s] =
    // sum{i>=0,i<=s}(c(s,i)*results(d-1,i)*result_per_dimension(d,s-i))
    // 4) return results(numDimensions, M)

    combine(100, 300);

    for (int t = 0; t < T; t++) {
      N = scanner.nextInt();
      M = scanner.nextInt();
      coordinates = new int[N];
      dimensions = new int[N];
      walks = new int[N][M];
      routes = new int[N][M];

      // for (int n = 0; n < N; n++) coordinates[n] = scanner.nextInt();
      // for (int n = 0; n < N; n++) {
      //     dimensions[n] = scanner.nextInt();
      //     maxN = Math.max(maxN, dimensions[n]);
      // }

      for (int n = 0; n < N; n++) walk(n);

      for (int n = 0; n < N; n++) {
        System.out.println();
        for (int m = 0; m < M; m++) {
          System.out.print(walks[n][m] + " ");
        }
      }

      route();

      routes = new int[N + 1][M + 1];
      System.out.println(routes[N][M]);
    }
  }
}
