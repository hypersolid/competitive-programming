import java.util.Scanner;

public class Solution {
  private static Scanner scanner = new Scanner(System.in);
  private static StringBuilder sb = new StringBuilder();
  private static int N;
  private static long sum1, sum2, diff;
  private static int[] apples;

  private static void readInput() {
    N = scanner.nextInt();
    apples = new int[N];
    for (int i = 0; i < N; i++) apples[i] = scanner.nextInt();
  }

  private static void recursive(int i) {
    if (i == apples.length) {
      diff = Math.min(diff, Math.abs(sum1 - sum2));
      return;
    }

    sum1 += apples[i];
    recursive(i + 1);
    sum1 -= apples[i];

    sum2 += apples[i];
    recursive(i + 1);
    sum2 -= apples[i];
  }

  private static void solve() {
    sum1 = 0;
    sum2 = 0;
    diff = Integer.MAX_VALUE;

    recursive(0);

    sb.append(diff);
  }

  public static void main(String[] args) {
    int T = 1;
    for (int t = 0; t < T; t++) {
      readInput();
      solve();
      sb.append("\n");
    }
    System.out.print(sb.toString());
  }
}
