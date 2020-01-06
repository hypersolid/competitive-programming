public class Solution {
  private static Reader scanner = new Reader();
  private static FastWriter log = new FastWriter();
  private static int INF = Integer.MAX_VALUE - 1;

  private static int N;
  private static ArrayList<Pair> pairs;

  private static void readInput() throws IOException {
    N = scanner.nextInt();

    pairs = new ArrayList<>(N);
    for (int i = 0; i < N; i++) pairs.add(new Pair(scanner.nextInt(), scanner.nextInt()));
  }

  private static void solve() throws IOException {
    Collections.sort(pairs, (p1, p2) -> Integer.compare(p1.val1, p2.val1));
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    int max = 0;

    for (Pair pair : pairs) {
      while (!pq.isEmpty() && pq.peek() <= pair.val1) pq.poll();

      pq.add(pair.val2);

      max = Math.max(max, pq.size());
    }

    log.print(max);
  }

  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    try {
      int T = 1;
      for (int t = 0; t < T; t++) {
        readInput();

        solve();
      }
      log.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (NoSuchElementException e) {
      throw new RuntimeException("WRONG INPUT");
    }
    System.err.println("\ntime: " + (System.currentTimeMillis() - startTime) + "ms");
  }

  static class FastWriter {
    private final StringBuilder bw;

    public FastWriter() {
      this.bw = new StringBuilder(1024 * 1024);
    }

    public void print(Object object) throws IOException {
      bw.append(object.toString());
    }

    public void print(int i) throws IOException {
      bw.append(Integer.toString(i));
    }

    public void print(long i) throws IOException {
      bw.append(Long.toString(i));
    }

    public void print(String s) throws IOException {
      bw.append(s);
    }

    public void println(Object object) throws IOException {
      print(object);
      bw.append("\n");
    }

    public void close() throws IOException {
      System.out.println(bw);
    }
  }

  static class Reader {
    private final int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public Reader() {
      din = new DataInputStream(System.in);
      buffer = new byte[BUFFER_SIZE];
      bufferPointer = bytesRead = 0;
    }

    public Reader(String file_name) throws IOException {
      din = new DataInputStream(new FileInputStream(file_name));
      buffer = new byte[BUFFER_SIZE];
      bufferPointer = bytesRead = 0;
    }

    public String readLine() throws IOException {
      byte[] buf = new byte[64]; // line length
      int cnt = 0, c;
      while ((c = read()) != -1) {
        if (c == '\n') break;
        buf[cnt++] = (byte) c;
      }
      return new String(buf, 0, cnt);
    }

    public int nextInt() throws IOException {
      int ret = 0;
      byte c = read();
      while (c <= ' ') c = read();
      boolean neg = (c == '-');
      if (neg) c = read();
      do {
        ret = ret * 10 + c - '0';
      } while ((c = read()) >= '0' && c <= '9');

      if (neg) return -ret;
      return ret;
    }

    public long nextLong() throws IOException {
      long ret = 0;
      byte c = read();
      while (c <= ' ') c = read();
      boolean neg = (c == '-');
      if (neg) c = read();
      do {
        ret = ret * 10 + c - '0';
      } while ((c = read()) >= '0' && c <= '9');
      if (neg) return -ret;
      return ret;
    }

    public double nextDouble() throws IOException {
      double ret = 0, div = 1;
      byte c = read();
      while (c <= ' ') c = read();
      boolean neg = (c == '-');
      if (neg) c = read();

      do {
        ret = ret * 10 + c - '0';
      } while ((c = read()) >= '0' && c <= '9');

      if (c == '.') {
        while ((c = read()) >= '0' && c <= '9') {
          ret += (c - '0') / (div *= 10);
        }
      }

      if (neg) return -ret;
      return ret;
    }

    private void fillBuffer() throws IOException {
      bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
      if (bytesRead == -1) buffer[0] = -1;
    }

    private byte read() throws IOException {
      if (bufferPointer == bytesRead) fillBuffer();
      return buffer[bufferPointer++];
    }

    public void close() throws IOException {
      if (din == null) return;
      din.close();
    }

    int[] fillArray(int n) throws IOException {
      int[] array = new int[n];
      for (int i = 0; i < n; i++) array[i] = nextInt();
      return array;
    }

    <T extends List> T fillList(T list, int n) throws IOException {
      for (int i = 0; i < n; i++) list.add(nextInt());
      return list;
    }
  }

  static class Pair {
    int val1;
    int val2;

    public Pair(int val1, int val2) {
      this.val1 = val1;
      this.val2 = val2;
    }

    @java.lang.Override
    public java.lang.String toString() {
      return "Pair{" + "val1=" + val1 + ", val2=" + val2 + '}';
    }
  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in), 1024 * 1024);
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

    int[] fillArray(int n) {
      int[] array = new int[n];
      for (int i = 0; i < n; i++) array[i] = nextInt();
      return array;
    }

    <T extends List> T fillList(T list, int n) {
      for (int i = 0; i < n; i++) list.add(nextInt());
      return list;
    }
  }
}
