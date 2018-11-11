import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Solution {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int t1 = scanner.nextInt();
    int t2 = scanner.nextInt();
    int N = scanner.nextInt() - 2;
    BigInteger tMinus2 = new BigInteger(Integer.toString(t1));
    BigInteger tMinus1 = new BigInteger(Integer.toString(t2));
    BigInteger current = new BigInteger("-1");

    for (int i = 0; i < N; i++) {
      current = tMinus2.add(tMinus1.multiply(tMinus1));
      tMinus2 = tMinus1;
      tMinus1 = current;
    }

    System.out.println(current.toString());
  }
}
