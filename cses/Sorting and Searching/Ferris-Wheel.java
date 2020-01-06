public class Solution {
  private static FastReader scanner = new FastReader();
  private static StringBuilder sb = new StringBuilder();
  private static int INF = Integer.MAX_VALUE;
  private static int N, X;
  private static ArrayList<Integer> weights;

  private static void readInput() {
    N = scanner.nextInt();
    X = scanner.nextInt();

    weights = fillArrayList(N);
  }

  private static int[] fillArray(int n) {
    int[] array = new int[n];
    for (int i = 0; i < n; i++) array[i] = scanner.nextInt();
    return array;
  }

  private static ArrayList<Integer> fillArrayList(int n) {
    ArrayList<Integer> arrayList = new ArrayList<Integer>(N);
    for (int i = 0; i < n; i++) arrayList.add(scanner.nextInt());
    return arrayList;
  }

  private static void solve() {
    Collections.sort(weights);
    int p1 = 0;
    int p2 = N - 1;
    int gondolas = 0;

    while (p1 <= p2) {
      if (p1 != p2 && weights.get(p1) + weights.get(p2) <= X) p1++;
      p2--;
      gondolas++;
    }

    sb.append(gondolas);
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
