import java.io.*;
import java.util.*;

public class Solution {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int T = scanner.nextInt();
    for (int t = 0; t < T; t++) {
      int N = scanner.nextInt();
      if (N % 3 == 0 && N >= 3) for (int i = 0; i < N; i++) System.out.print('5');
      else {
        if (N % 3 == 2 && N >= 5) {
          for (int i = 0; i < N - 5; i++) System.out.print('5');
          for (int i = 0; i < 5; i++) System.out.print('3');
        } else {
          if (N % 3 == 1 && N >= 10) {
            for (int i = 0; i < N - 10; i++) System.out.print('5');
            for (int i = 0; i < 10; i++) System.out.print('3');
          } else {
            System.out.print("-1");
          }
        }
      }
      System.out.println();
    }
  }
}
