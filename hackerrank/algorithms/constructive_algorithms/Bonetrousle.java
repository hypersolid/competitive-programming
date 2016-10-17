





import java.io.*;
import java.util.*;
import java.util.Arrays;

public class Solution {
  static int T, B;
  static long N, K;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    T = scanner.nextInt();
    for (int t = 0; t < T; t++) {
      N = scanner.nextLong();
      K = scanner.nextLong();
      B = scanner.nextInt();
      if (B > K) System.out.println("-1");
      else printCombination();
    }
  }

  static void printCombination() {
    long[] pointers = new long[B];

    long sum = 0;
    int lastPtr = B - 1;
    for (int b = 0; b < lastPtr; b++) {
      pointers[b] = b + 1;
      sum += pointers[b];
    }
    pointers[lastPtr] = Math.min(N, K);
    sum += pointers[lastPtr];

    long diff, prevSum = -1;
    while (sum != N) {
      if (sum > N) {
        diff = Math.min(sum - N, pointers[lastPtr] - pointers[lastPtr - 1] - 1);
        pointers[lastPtr] -= diff;
        sum -= diff;
      } else {
        for (int b = lastPtr - 1; b >= 0; b--) {
          if (N - sum == 0) {
            printArray(pointers);
            return;
          }
          diff = Math.min(N - sum, pointers[b + 1] - pointers[b] - 1);
          pointers[b] += diff;
          sum += diff;
        }
      }
      if (prevSum == sum) {
        System.out.println("-1");
        return;
      } else {
        prevSum = sum;
      }
    }

    printArray(pointers);
  }

  static void printArray(long[] array) {
    System.out.println(Arrays.toString(array).replaceAll("\\[|\\]|,", ""));
  }
}
