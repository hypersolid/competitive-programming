import java.util.Scanner;

class Wrapper {
  public static void main(String[] args) {
    (new Solution()).solve();
  }

  private static class Solution {
    Scanner scanner = new Scanner(System.in);

    public void solve() {
      int N = scanner.nextInt();
      StringBuilder sb = new StringBuilder(N);

      long result = 0;
      sb.append(result).append("\n");

      for (long n = 2; n <= N; n++) {
        long same = (2 * n - 1) * (2 * n - 2) / 2 - 2;
        long split = (n - 1) * (n - 1) * (2 * n - 1) - 8 * (n - 2) + 2;
        result += same + split;
        sb.append(result).append("\n");
      }

      System.out.print(sb);
    }
  }
}
