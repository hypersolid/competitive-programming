import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  static int N;
  static int[] diffHistogram, histogram;
  static String gene;
  static int v = 4;
  static int result = Integer.MAX_VALUE;

  public static int codeAt(int idx) {
    switch (gene.charAt(idx)) {
      case 'A':
        return 0;
      case 'C':
        return 1;
      case 'T':
        return 2;
      case 'G':
        return 3;
    }
    return -1;
  }

  public static void constructDiff() {
    int target = N / v;
    for (int i = 0; i < N; i++) diffHistogram[codeAt(i)]++;
    for (int i = 0; i < v; i++) diffHistogram[i] = Math.max(0, diffHistogram[i] - target);
  }

  public static boolean compareHistograms() {
    for (int i = 0; i < v; i++) if (histogram[i] < diffHistogram[i]) return false;
    return true;
  }

  public static void slidingWindow() {
    int head = 0;
    int tail = 0;
    histogram[codeAt(0)]++;

    while (tail < N && head <= tail) {
      if (compareHistograms()) {
        result = Math.min(result, tail - head + 1);
        histogram[codeAt(head)]--;
        head++;
      } else {
        tail++;
        if (tail < N) histogram[codeAt(tail)]++;
      }
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    N = in.nextInt();
    gene = in.next();

    diffHistogram = new int[N];
    histogram = new int[N];

    constructDiff();

    if (compareHistograms()) result = 0;
    else slidingWindow();

    System.out.println(result);
  }
}
