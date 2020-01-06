public class Solution {
  private static FastReader scanner = new FastReader();
  private static StringBuilder sb = new StringBuilder();
  private static int INF = Integer.MAX_VALUE;
  private static int N;
  private static int[] a;

  private static void readInput() {
    N = scanner.nextInt();
    a = fillArray(N);
  }

  private static int[] fillArray(int n) {
    int[] array = new int[n];
    for (int i = 0; i < n; i++) array[i] = scanner.nextInt();
    return array;
  }

  private static void solve() {
    Set<Integer> set = new HashSet<Integer>();
    for (int i = 0; i < N; i++) set.add(a[i]);
    sb.append(set.size());
  }

  public static void main(String[] args) {
    try {
      int T = 1;
      for (int t = 0; t < T; t++) {
        readInput();
        solve();
        sb.append("\n");
      }
      System.out.print(sb.toString());
    } catch (NoSuchElementException e) {
      throw new RuntimeException("WRONG INPUT");
    }
  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
      while (st == null || !st.hasMoreElements()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }

    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }
  }
}
