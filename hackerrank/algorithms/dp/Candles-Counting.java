import java.io.*;
import java.util.*;

public class Solution {
  static int N, K;
  static int[] heights, colors;
  static final long MODULO = (long) Math.pow(10, 9) + 7;
  static final int MAX_HEIGHT = (int) (5 * Math.pow(10, 4));
  static FenwickTree tree;

  public static class FenwickTree {
    public long[][] array;

    public FenwickTree() {
      array = new long[MAX_HEIGHT + 1][1 << K];
    }

    public void update(int i, int mask, long increment) {
      while (i < array.length) {
        array[i][mask] += increment;

        array[i][mask] %= MODULO;
        i += i & (-i);
      }
    }

    public long query(int i, int mask) {
      long sum = 0;
      while (i > 0) {
        sum += array[i][mask];

        sum %= MODULO;
        i -= i & (-i);
      }
      return sum;
    }
  }

  static void step(int position) {
    long[] buffer = new long[1 << K];

    for (int m = 1; m < 1 << K; m++) buffer[m] = tree.query(heights[position] - 1, m);

    for (int m = 1; m < 1 << K; m++) {
      if (buffer[m] == 0) continue;
      tree.update(heights[position], m | colors[position], buffer[m]);
    }
    tree.update(heights[position], colors[position], 1);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    N = scanner.nextInt();
    K = scanner.nextInt();

    heights = new int[N];
    colors = new int[N];

    for (int n = 0; n < N; n++) {
      heights[n] = scanner.nextInt();
      colors[n] = 1 << (scanner.nextInt() - 1);
    }

    tree = new FenwickTree();

    for (int i = 0; i < N; i++) step(i);

    System.out.print(tree.query(MAX_HEIGHT, (1 << K) - 1));
  }
}
