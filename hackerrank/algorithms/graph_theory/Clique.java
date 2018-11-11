import java.io.*;
import java.util.*;

public class Solution {

  // N.B. The hint provides us with the upper bound estimate, but not the real number of edges in
  // Turan graph
  static double turan(int n, int k) {
    k--;
    if (k < 1) return 0;
    double result = Math.pow(n, 2);
    result -= n % k * Math.pow(Math.ceil((double) n / (double) k), 2);
    result -= (k - n % k) * Math.pow(Math.floor((double) n / (double) k), 2);
    return 0.5 * result;
  }

  static int bsearch(int v, int e, int start, int finish) {
    if (start == finish) return start;
    int center = start + (finish - start) / 2;
    double minEdges = turan(v, center);
    if (minEdges == e) return center;
    if (minEdges < e) return bsearch(v, e, center + 1, finish);
    else return bsearch(v, e, start, center == start ? center : center - 1);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int T = scanner.nextInt();

    for (int t = 0; t < T; t++) {
      int V = scanner.nextInt();
      int E = scanner.nextInt();

      int estimate = bsearch(V, E, 1, V);

      if (turan(V, estimate) < E) estimate++;
      if (turan(V, estimate) >= E) estimate--;

      System.out.println(estimate);
    }
  }
}
