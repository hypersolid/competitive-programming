import java.util.Scanner;

public class Solution {
  private static Scanner scanner = new Scanner(System.in);
  private static StringBuilder sb = new StringBuilder();
  private static long N;

  private static void readInput() {
    N = scanner.nextLong();
  }

  private static void solve() {
    if (N % 4 == 1 || N % 4 == 2) {
      sb.append("NO");
      return;
    }

    sb.append("YES\n");

    // first set
    if (N % 4 == 0) {
      sb.append(N / 2).append("\n");
    } else {
      sb.append(N / 4 * 2 + 2).append("\n");
      sb.append(1).append(" ");
      sb.append(2).append(" ");
    }
    for (int i = 1; i <= N / 4; i++) {
      sb.append(N - i * 4 + 1).append(" ");
      sb.append(N - i * 4 + 4).append(" ");
    }

    // first set
    if (N % 4 == 0) {
      sb.append("\n").append(N / 2).append("\n");
    } else {
      sb.append("\n").append(N / 4 * 2 + 1).append("\n");
      sb.append(3).append(" ");
    }
    for (int i = 1; i <= N / 4; i++) {
      sb.append(N - i * 4 + 2).append(" ");
      sb.append(N - i * 4 + 3).append(" ");
    }
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
