import java.io.*;
import java.util.*;

public class Solution {

  public static int stepCount(int diff) {
    return diff / 5 + diff % 5 / 2 + diff % 5 % 2;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int T = scanner.nextInt();
    for (int t = 0; t < T; t++) {
      int N = scanner.nextInt();
      int[] line = new int[N];
      int min = Integer.MAX_VALUE;
      for (int n = 0; n < N; n++) {
        line[n] = scanner.nextInt();
        min = Math.min(min, line[n]);
      }

      int minCount = 0;
      for (int n = 0; n < N; n++) if (line[n] == min) minCount++;

      int steps = 0;
      int steps1 = minCount;
      int steps2 = minCount;
      for (int n = 0; n < N; n++) {
        int diff = line[n] - min;
        steps += stepCount(diff);
        if (diff == 0) continue;
        int diff1 = diff + minCount;
        steps1 += stepCount(diff1);
        int diff2 = diff + minCount * 2;
        steps2 += stepCount(diff2);
      }

      System.out.println(Math.min(steps, Math.min(steps1, steps2)));
    }
  }
}
