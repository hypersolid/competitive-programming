import java.util.*;

public class Solution {
  static char[] chars1, chars2;
  static int N;
  static int[][] matrix;

  static int dp(int p1, int p2) {
    if (p1 < 0 || p2 < 0) return 0;
    if (matrix[p1][p2] != -1) return matrix[p1][p2];

    if (chars1[p1] == chars2[p2]) matrix[p1][p2] = 1 + dp(p1 - 1, p2 - 1);
    else matrix[p1][p2] = Math.max(dp(p1 - 1, p2), dp(p1, p2 - 1));

    return matrix[p1][p2];
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    chars1 = scanner.nextLine().trim().toCharArray();
    chars2 = scanner.nextLine().trim().toCharArray();
    N = chars1.length;
    matrix = new int[N][N];
    for (int i = 0; i < N; i++) Arrays.fill(matrix[i], -1);

    dp(N - 1, N - 1);

    System.out.println(matrix[N - 1][N - 1]);
  }
}
