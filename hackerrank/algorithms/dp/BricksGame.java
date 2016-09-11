import java.io.*;
import java.util.*;

public class Solution {
  static long[] memo, stack, sums;
  static int N;

  public static long dp(int index) {
    if (memo[index] != -1) return memo[index];
    long maxProfit = 0;

    long brick1 = stack[index];
    long brick2 = brick1 + stack[index + 1];
    long brick3 = brick2 + stack[index + 2];

    maxProfit = Math.max(maxProfit, brick1 + sums[index + 1] - dp(index + 1));
    maxProfit = Math.max(maxProfit, brick2 + sums[index + 2] - dp(index + 2));
    maxProfit = Math.max(maxProfit, brick3 + sums[index + 3] - dp(index + 3));

    memo[index] = maxProfit;
    return maxProfit;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int T = scanner.nextInt();
    for (int t = 0; t < T; t++) {
      N = scanner.nextInt();
      stack = new long[N + 3];
      memo = new long[N + 3];
      sums = new long[N + 3];
      Arrays.fill(memo, -1);
      for (int n = 0; n < N; n++) stack[n] = scanner.nextInt();
      for (int n = N - 1; n >= 0; n--) sums[n] = sums[n + 1] + stack[n];

      memo[N - 1] = stack[N - 1];
      memo[N] = 0;
      memo[N + 1] = 0;
      memo[N + 2] = 0;

      System.out.println(dp(0));
    }
  }
}
