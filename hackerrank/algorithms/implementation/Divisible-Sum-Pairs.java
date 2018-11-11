import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();
    int K = in.nextInt();
    int[] A = new int[N];
    for (int i = 0; i < N; i++) A[i] = in.nextInt();

    int count = 0;
    for (int i = 0; i < N; i++) for (int j = i + 1; j < N; j++) if ((A[i] + A[j]) % K == 0) count++;
    System.out.println(count);
  }
}
