public class Solution {
  private static FastReader scanner = new FastReader();
  private static StringBuilder sb = new StringBuilder();
  private static int INF = Integer.MAX_VALUE;
  private static int N, M, K;
  private static int[] desired, apts;
  private static ArrayList<Integer> desiredList = new ArrayList<>();
  private static ArrayList<Integer> aptsList = new ArrayList<>();

  private static void readInput() {
    N = scanner.nextInt();
    M = scanner.nextInt();
    K = scanner.nextInt();

    desired = fillArray(N);
    apts = fillArray(M);
  }

  private static int[] fillArray(int n) {
    int[] array = new int[n];
    for (int i = 0; i < n; i++) array[i] = scanner.nextInt();
    return array;
  }

  private static boolean matches(int apt, int person) {
    return desiredList.get(person) >= aptsList.get(apt) - K
        && desiredList.get(person) <= aptsList.get(apt) + K;
  }

  private static boolean tooSmall(int apt, int person) {
    return aptsList.get(apt) + K < desiredList.get(person);
  }

  private static void solve() {
    //        Arrays.sort(desired);
    //        Arrays.sort(apts);
    // java.util.DualPivotQuicksort.sort(DualPivotQuicksort.java:472)
    for (int i = 0; i < N; i++) desiredList.add(desired[i]);
    for (int i = 0; i < M; i++) aptsList.add(apts[i]);
    Collections.sort(desiredList);
    Collections.sort(aptsList);

    int apt = 0;
    int person = 0;
    int counter = 0;

    while (apt < M && person < N) {
      if (matches(apt, person)) {
        counter++;
        person++;
        apt++;
      } else {
        if (tooSmall(apt, person)) apt++;
        else person++;
      }
    }

    sb.append(counter);
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
