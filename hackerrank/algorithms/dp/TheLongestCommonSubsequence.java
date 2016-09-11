import java.io.*;
import java.util.*;

public class Solution {
  static int N, M;
  static int[][] dp;
  static int[] a1, a2;
  static ArrayList<Integer> result = new ArrayList<>();

  public static int lcs(int i, int j) {
    if (dp[i][j] != -1) return dp[i][j];

    if (a1[i - 1] == a2[j - 1]) dp[i][j] = lcs(i - 1, j - 1) + 1;
    else dp[i][j] = Math.max(lcs(i - 1, j), lcs(i, j - 1));

    return dp[i][j];
  }

  public static void trace(int i, int j) {
    if (i == 0 || j == 0) return;

    if (a1[i - 1] == a2[j - 1]) {
      result.add(0, a1[i - 1]);
      trace(i - 1, j - 1);
    } else {
      if (dp[i - 1][j] > dp[i][j - 1]) trace(i - 1, j);
      else trace(i, j - 1);
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    N = scanner.nextInt();
    M = scanner.nextInt();
    a1 = new int[N];
    a2 = new int[M];
    dp = new int[N + 1][M + 1];
    for (int i = 0; i < N; i++) a1[i] = scanner.nextInt();
    for (int i = 0; i < M; i++) a2[i] = scanner.nextInt();
    for (int i = 1; i <= N; i++) {
      Arrays.fill(dp[i], -1);
      dp[i][0] = 0;
    }

    lcs(N, M);
    trace(N, M);

    for (int element : result) System.out.print(element + " ");
  }
}
