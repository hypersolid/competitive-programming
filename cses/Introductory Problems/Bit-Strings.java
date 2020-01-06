import java.util.Scanner;

public class Solution {
  private static Scanner scanner = new Scanner(System.in);
  private static StringBuilder sb = new StringBuilder();
  private static int N;
  private static long MODULO = 1000000007L;

  private static void readInput() {
    N = scanner.nextInt();
  }

  private static void solve() {
    long result = 1L;
    for (int i = 0; i < N; i++) result = result * 2 % MODULO;
    sb.append(result);
  }

  public static void main(String[] args) {
    int T = 1;
    for (int t = 0; t < T; t++) {
      readInput();
      solve();
    }
    System.out.print(sb.toString());
  }
}
