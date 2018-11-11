import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    long n = in.nextLong();
    int zeroBits = 0;
    boolean start = false;

    for (int i = 63; i >= 0; i--) {
      if ((1L << i & n) == 0) {
        if (start) zeroBits++;
      } else {
        start = true;
      }
    }

    System.out.println(1L << (zeroBits - 1) + 1);
  }
}
