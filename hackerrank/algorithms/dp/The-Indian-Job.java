import java.util.*;

public class Main {
  static int N, G, T, S, J, dJ;
  static int[] time;
  static boolean[][] dp;

  public static boolean solve() {
    dp = new boolean[N][G + 1];

    for (int n = 0; n < N; n++) dp[n][0] = true;
    dp[0][time[0]] = true;

    for (int s = 1; s <= G; s++) {
      for (int n = 1; n < N; n++) {
        if (dp[n - 1][s]) dp[n][s] = true;
        else {
          if (s >= time[n]) dp[n][s] = dp[n - 1][s - time[n]];
        }
      }
    }

    J = -1;
    dJ = Integer.MAX_VALUE;
    for (int s = 0; s <= G; s++) {
      if (!dp[N - 1][s]) continue;

      int diff = Math.abs(S - s * 2);
      if (diff < dJ) {
        J = s;
        dJ = diff;
      }
    }

    if (J < 0) return false;
    return J <= G && S - J <= G;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    T = scanner.nextInt();

    for (int t = 0; t < T; t++) {
      N = scanner.nextInt();
      G = scanner.nextInt();

      S = 0;
      time = new int[N];
      for (int n = 0; n < N; n++) {
        time[n] = scanner.nextInt();
        S += time[n];
      }

      if (S / 2 > G) {
        System.out.println("NO");
        continue;
      } else {
        System.out.println(solve() ? "YES" : "NO");
      }
    }
  }
}
