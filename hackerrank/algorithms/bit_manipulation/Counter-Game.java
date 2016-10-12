import java.io.*;
import java.util.*;

public class Solution {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int T = scanner.nextInt();

    for (int t = 0; t < T; t++) {
      Long N = Long.parseUnsignedLong(scanner.next());

      int steps = 0;
      boolean powerOfTwo;
      int highestBit = 0;

      while (N != 1) {
        powerOfTwo = false;

        for (int i = 0; i < 64; i++) {
          if ((N >>> i & 1) == 1) highestBit = i;
          if (Long.compareUnsigned(N, 1L << i) == 0) {
            N >>>= 1;
            powerOfTwo = true;
            break;
          }
        }

        if (!powerOfTwo) N -= 1L << highestBit;

        steps++;
      }
      System.out.println(steps % 2 == 0 ? "Richard" : "Louise");
    }
  }
}
