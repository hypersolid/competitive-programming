import java.util.Scanner;

public class Solution {
  private static Scanner scanner = new Scanner(System.in);
  private static StringBuilder sb = new StringBuilder();
  private static int N = 8;
  private static int[][] field = new int[N][N];
  private static int result = 0;

  private static void readInput() {
    for (int j = 0; j < N; j++) {
      String line = scanner.next();
      for (int i = 0; i < N; i++) {
        field[j][i] = line.charAt(i) == '.' ? 0 : 1;
      }
    }
  }

  private static boolean clearSpot(int i, int j) {
    return field[j][i] == 0;
  }

  private static boolean clearRow(int j) {
    for (int k = 0; k < N; k++) {
      if (field[j][k] == -1) return false;
    }
    return true;
  }

  private static boolean clearColumn(int i) {
    for (int k = 0; k < N; k++) {
      if (field[k][i] == -1) return false;
    }
    return true;
  }

  private static boolean clearLeftDiagonal(int i, int j) {
    int tj = j;
    int ti = i;
    while (tj >= 0 && ti >= 0) {
      if (field[tj--][ti--] == -1) return false;
    }
    tj = j;
    ti = i;
    while (tj < N && ti < N) {
      if (field[tj++][ti++] == -1) return false;
    }

    return true;
  }

  private static boolean clearRightDiagonal(int i, int j) {
    int tj = j;
    int ti = i;
    while (tj >= 0 && ti < N) {
      if (field[tj--][ti++] == -1) return false;
    }
    tj = j;
    ti = i;
    while (tj < N && ti >= 0) {
      if (field[tj++][ti--] == -1) return false;
    }

    return true;
  }

  private static void r(int i, int j) {
    if (i >= N || j >= N) return;

    if (clearSpot(i, j) && clearColumn(i) && clearLeftDiagonal(i, j) && clearRightDiagonal(i, j)) {
      if (j == N - 1) {
        result++;
        return;
      }

      field[j][i] = -1;
      for (int k = 0; k < N; k++) r(k, j + 1);
      field[j][i] = 0;
    }
  }

  private static void solve() {
    for (int i = 0; i < N; i++) r(i, 0);
    sb.append(result);
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
