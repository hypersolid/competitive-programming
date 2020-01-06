import java.util.Scanner;

public class Solution {
  private static Scanner scanner = new Scanner(System.in);
  private static StringBuilder sb = new StringBuilder();
  private static int A, B;

  private static void readInput() {
    A = scanner.nextInt();
    B = scanner.nextInt();
  }

  private static void solve() {
    int g = Math.max(A, B);
    int l = Math.min(A, B);
    int diff = g - l;

    if (diff > l) {
      sb.append("NO");
    } else {
      sb.append((l - diff) % 3 == 0 ? "YES" : "NO");
    }
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
