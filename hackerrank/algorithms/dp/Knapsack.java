import java.io.*;
import java.util.*;

public class Solution {
  static int[] weights;
  static int[][] dp;

  public static int dp(int idx, int target) {
    if (idx < 0) return target;
    if (dp[idx][target] != -1) return dp[idx][target];

    int minDiff = Integer.MAX_VALUE;
    for (int t = target; t >= 0; t -= weights[idx]) {
      minDiff = Math.min(minDiff, dp(idx - 1, t));
      if (minDiff == 0) break;
    }

    dp[idx][target] = minDiff;
    return minDiff;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int T = scanner.nextInt();
    for (int t = 0; t < T; t++) {
      int N = scanner.nextInt();
      int K = scanner.nextInt();

      dp = new int[N][K + 1];
      for (int n = 0; n < N; n++) Arrays.fill(dp[n], -1);

      weights = new int[N];
      for (int n = 0; n < N; n++) weights[n] = scanner.nextInt();

      System.out.println(K - dp(N - 1, K));
    }
  }
}
