import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  static final int ALPHABET = 26;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int len = in.nextInt();
    String s = in.next();

    int[][] lengths = new int[ALPHABET][ALPHABET];
    int[][] last = new int[ALPHABET][ALPHABET];

    for (int i = 0; i < s.length(); i++) {
      int l = s.charAt(i) - 'a';

      for (int j = 0; j < ALPHABET; j++) {
        if (l == j) continue;

        int i1 = Math.min(l, j), i2 = Math.max(l, j);

        if (last[i1][i2] < 0) continue;

        if (last[i1][i2] == l + 1) last[i1][i2] = -1;
        else {
          lengths[i1][i2]++;
          last[i1][i2] = l + 1;
        }
      }
    }

    int result = 0;
    for (int i = 0; i < ALPHABET; i++) {
      for (int j = 0; j < ALPHABET; j++) {
        int i1 = Math.min(i, j), i2 = Math.max(i, j);
        if (i != j && last[i1][i2] > 0 && lengths[i1][i2] > 1)
          result = Math.max(result, lengths[i1][i2]);
      }
    }

    System.out.println(result);
  }
}
