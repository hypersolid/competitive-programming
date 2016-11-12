import java.io.*;
import java.util.*;

public class Solution {
  static final int SIZE = 100, FACES = 6, NOT_VISITED = -1;
  static int[] dp, ladders;

  static void solve(int position, int step) {
    if (position >= SIZE) return;
    position = ladders[position];

    if (dp[position] != NOT_VISITED && dp[position] <= step) return;

    dp[position] = step;
    for (int i = FACES; i >= 1; i--) solve(position + i, step + 1);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int T = scanner.nextInt();

    for (int t = 0; t < T; t++) {
      ladders = new int[SIZE];
      dp = new int[SIZE];
      Arrays.fill(dp, NOT_VISITED);

      for (int i = 0; i < SIZE; i++) ladders[i] = i;

      int N = scanner.nextInt();
      for (int n = 0; n < N; n++) ladders[scanner.nextInt() - 1] = scanner.nextInt() - 1;
      int M = scanner.nextInt();
      for (int m = 0; m < M; m++) ladders[scanner.nextInt() - 1] = scanner.nextInt() - 1;

      solve(0, 0);
      System.out.println(dp[SIZE - 1]);
    }
  }
}
