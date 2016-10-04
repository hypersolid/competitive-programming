import java.io.*;
import java.util.*;

public class Main {
  static int N, M;
  static long[][] T, U;
  static final long TILES = 4;
  static final long MODULO = 1000000007;
  static final int RANGE = 1000;

  // calculate all brick combinations for the first row
  public static long calculateT(int col) {
    if (col < 0) return 0;
    if (T[1][col] != 0) return T[1][col];
    for (int k = 1; k <= TILES; k++) {
      T[1][col] += calculateT(col - k);
      T[1][col] %= MODULO;
    }

    return T[1][col];
  }

  public static void buildUpT() {
    for (int row = 2; row <= RANGE; row++)
      for (int col = 1; col <= RANGE; col++) {
        T[row][col] = T[1][col] * T[row - 1][col] % MODULO;
      }
  }

  // U[N][M] number of unbreakable walls M x N
  // T[N][M] number of all possible walls M x N
  // U[N][M] is a sum{1, M - 1} by i of U[N][M - i] * T[N]T[M - i]
  // thus we have recursive relation and can apply dynamic programming
  public static long calculateU(int col) {
    if (U[N][col] != 0) return U[N][col];

    U[N][col] = T[N][col];
    for (int i = 1; i < col; i++) {
      U[N][col] -= calculateU(col - i) * T[N][i];
      U[N][col] %= MODULO;
      if (U[N][col] < 0) U[N][col] += MODULO;
    }

    return U[N][col];
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    long J = scanner.nextLong();

    T = new long[RANGE + 1][RANGE + 1];
    T[1][0] = 1;
    T[1][1] = 1;

    calculateT(1000);
    buildUpT();

    U = new long[RANGE + 1][RANGE + 1];
    U[1][1] = 1;

    for (int j = 0; j < J; j++) {
      N = scanner.nextInt();
      M = scanner.nextInt();

      if (N == 1) {
        System.out.println(M > TILES ? 0 : 1);
      } else {
        calculateU(M);
        System.out.println(U[N][M]);
      }
    }
  }
}
