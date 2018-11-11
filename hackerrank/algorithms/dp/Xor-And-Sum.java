import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String s1 = scanner.nextLine();
    String s2 = scanner.nextLine();
    int N = s1.length(), M = s2.length();

    int modulo = (int) Math.pow(10, 9) + 7;
    int shiftLimit = 314159;

    long capacitor = 0, powerCapacitor = 1;

    int[] firstNumberBit = new int[N];
    int[] secondNumberBitCount = new int[M];
    int[] secondNumberInverseBitCount = new int[M];

    for (int i = N - 1; i >= 0; i--) {
      if (s1.charAt(i) == '1') firstNumberBit[N - 1 - i] = 1;
    }
    for (int i = M - 1; i >= 0; i--) {
      int idx = M - 1 - i;
      if (s2.charAt(i) == '1') secondNumberBitCount[idx] = 1;
      if (i < M - 1) secondNumberBitCount[idx] += secondNumberBitCount[idx - 1];
    }
    for (int i = 0; i < M; i++) {
      if (s2.charAt(i) == '1') secondNumberInverseBitCount[i] = 1;
      if (i > 0) secondNumberInverseBitCount[i] += secondNumberInverseBitCount[i - 1];
    }

    for (int p = 0; p < shiftLimit; p++) {
      long bits1 = p < N ? firstNumberBit[p] : 0;
      long bits2 = p < M ? secondNumberBitCount[p] : secondNumberBitCount[M - 1];

      if (bits1 == 1) {
        capacitor += (((shiftLimit + 1) - bits2) * powerCapacitor) % modulo;
      } else {
        capacitor += (bits2 * powerCapacitor) % modulo;
      }
      capacitor %= modulo;

      powerCapacitor = powerCapacitor * 2;
      powerCapacitor %= modulo;
    }

    // extra bits of second number (positions over 314159)
    for (int i = 0; i < M; i++) {
      capacitor += (powerCapacitor * secondNumberInverseBitCount[M - 1 - i]) % modulo;
      capacitor %= modulo;
      powerCapacitor = powerCapacitor * 2;
      powerCapacitor %= modulo;
    }

    System.out.println(capacitor);
  }
}
