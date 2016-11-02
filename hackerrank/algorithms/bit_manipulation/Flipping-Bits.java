import java.io.*;
import java.util.*;

public class Solution {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt();
    long mask = (1L << 32) - 1;
    for (int i = 0; i < N; i++) {
      long number = scanner.nextLong();
      System.out.println(number ^ mask);
    }
  }
}
