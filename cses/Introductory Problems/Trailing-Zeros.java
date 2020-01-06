import java.util.Scanner;

public class Solution {
  private static Scanner scanner = new Scanner(System.in);
  private static StringBuilder sb = new StringBuilder();
  private static int N;

  private static void readInput() {
    N = scanner.nextInt();
  }

  private static void solve() {
    long zeroes = 0;
    long divisor = 5;
    for (int i = 1; i <= 64; i++) {
      zeroes += N / divisor;
      divisor *= 5;
    }
    sb.append(zeroes);
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
