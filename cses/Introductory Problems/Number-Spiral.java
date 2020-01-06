import java.util.Scanner;

public class Solution {
  private static Scanner scanner = new Scanner(System.in);
  private static StringBuilder sb = new StringBuilder();
  private static long i, j;

  private static void readInput() {
    j = scanner.nextLong();
    i = scanner.nextLong();
  }

  private static long triangleSize(long triangle) {
    return triangle + (triangle - 1);
  }

  private static long triangleSum(long triangle) {
    return (triangle * (triangle + 1) + (triangle - 1) * triangle) / 2;
  }

  private static void solve() {
    long triangle = Math.max(i, j);
    long offset = Math.min(i, j);
    boolean even = (triangle % 2 == 0) || triangle == 1;
    boolean vertical = j < i;

    long start = triangleSum(triangle - 1);

    if ((vertical && even) || (!vertical && !even)) sb.append(start + offset);
    else sb.append(start + triangleSize(triangle) - offset + 1);
  }

  public static void main(String[] args) {
    int T = scanner.nextInt();
    for (int t = 0; t < T; t++) {
      readInput();
      solve();
      sb.append("\n");
    }
    System.out.print(sb.toString());
  }
}
