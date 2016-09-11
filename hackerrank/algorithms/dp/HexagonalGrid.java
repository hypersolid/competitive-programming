import java.io.*;
import java.util.*;

public class Solution {
  static int N;
  static int[][] dp;
  static final int L = 10;
  static final int block = 7;
  static HashMap<Long, Boolean> hash = new HashMap<>();

  // folds field into long
  static long fold(int[][] dp) {
    int h = N;
    for (int j = 0; j < 2; j++)
      for (int i = 0; i < N; i++) {
        if (dp[j][i] > 0) h |= 1 << (i + j * L + L);
      }
    return h;
  }

  // checks for tiling policy compliance =)
  static boolean applies0(int[][] dp, int i, int j) {
    return dp[(j + 1) % 2][i] == 0;
  }

  static boolean applies1(int[][] dp, int i, int j) {
    return i + 1 < N && dp[j][i + 1] == 0;
  }

  static boolean applies2(int[][] dp, int i, int j) {
    if (j == 0) return i - 1 >= 0 && dp[1][i - 1] == 0;
    return i + 1 < N && dp[0][i + 1] == 0;
  }

  static boolean applies(int[][] dp, int i, int j, int tile) {
    switch (tile) {
      case 0:
        return applies0(dp, i, j);
      case 1:
        return applies1(dp, i, j);
      case 2:
        return applies2(dp, i, j);
    }
    return false;
  }

  // actual tiling
  static int[][] apply(int[][] dp, int i, int j, int tile) {
    int[][] dpC = new int[2][N];
    for (int l = 0; l < N; l++) {
      dpC[0][l] = dp[0][l];
      dpC[1][l] = dp[1][l];
    }

    dpC[j][i] = tile + 1;

    switch (tile) {
      case 0:
        dpC[(j + 1) % 2][i] = tile + 1;
        break;
      case 1:
        dpC[j][i + 1] = tile + 1;
        break;
      case 2:
        {
          if (j == 0) dpC[1][i - 1] = tile + 1;
          else dpC[0][i + 1] = tile + 1;
        }
    }

    return dpC;
  }

  static boolean tile(int[][] dp, int depth) {
    long folded = fold(dp);
    if (hash.containsKey(folded)) return true;

    for (int j = 0; j < 2; j++)
      for (int i = 0; i < N; i++)
        if (dp[j][i] == 0) {
          boolean match = false;
          for (int k = 0; k < 3; k++)
            if (applies(dp, i, j, k)) {
              match |= tile(apply(dp, i, j, k), depth + 1); // there's a tiling option
            }
          if (match) hash.put(folded, true);
          return match; // can't tile this slot
        }
    hash.put(folded, true);
    return true; // no slots left
  }

  // main method
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int T = scanner.nextInt();

    for (int t = 0; t < T; t++) {
      N = scanner.nextInt();
      String s1 = scanner.next();
      String s2 = scanner.next();

      dp = new int[2][N];
      for (int j = 0; j < N; j++) dp[0][j] = (s1.charAt(j) - '0') * block;
      for (int j = 0; j < N; j++) dp[1][j] = (s2.charAt(j) - '0') * block;

      System.out.println(tile(dp, 1) ? "YES" : "NO");
    }
  }
}
