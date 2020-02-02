public class Task {
  private static final int IO_BUFFERS = 1024 * 1024;
  private static final long MODULO = (long) Math.pow(10, 9) + 7;
  private static final long LIMIT = Long.MAX_VALUE >> 2;
  private static FastReader reader = new FastReader();
  private static FastWriter writer = new FastWriter();

  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    try {
      int T = 1;
      for (int t = 0; t < T; t++) new Solution();
      writer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.err.println("\ntime: " + (System.currentTimeMillis() - startTime) + "ms");
  }

  static class Solution {
    int N, K;

    public Solution() throws IOException {
      readInput();
      writer.println(String.format("%.6f", solve()));
    }

    void readInput() throws IOException {
      N = 8;
      K = reader.nextInt();
    }

    double solve() {
      int[][] rank = new int[N][N];
      for (int j = 0; j < N; j++) {
        for (int i = 0; i < N; i++) {
          if (j > 0) rank[j - 1][i]++;
          if (i > 0) rank[j][i - 1]++;
          if (j < N - 1) rank[j + 1][i]++;
          if (i < N - 1) rank[j][i + 1]++;
        }
      }

      double[][] b = new double[N][N];
      for (int u = 0; u < N; u++) Arrays.fill(b[u], 1);

      for (int u = 0; u < N; u++) {
        for (int v = 0; v < N; v++) {
          double[][] f = new double[N][N];
          f[u][v] = 1;
          for (int k = 0; k < K; k++) {
            double[][] ft = new double[N][N];
            for (int j = 0; j < N; j++) {
              for (int i = 0; i < N; i++) {
                double delta = f[j][i] / rank[j][i];
                if (j > 0) ft[j - 1][i] += delta;
                if (i > 0) ft[j][i - 1] += delta;
                if (j < N - 1) ft[j + 1][i] += delta;
                if (i < N - 1) ft[j][i + 1] += delta;
              }
            }
            f = ft;
          }
          //          System.out.println(u + " " + v);
          //          for (int i = 0; i < N; i++)
          //            System.out.println(Arrays.toString(f[i]));

          for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
              b[i][j] *= 1 - f[i][j];
            }
          }
        }
      }

      //      System.out.println(Arrays.deepToString(b));
      double result = 0;
      for (int j = 0; j < N; j++) {
        for (int i = 0; i < N; i++) {
          result += b[i][j];
        }
      }
      return result;
    }
  }

  static class HMultiset<T> extends HashMap<T, Integer> {
    void add(T key) {
      Integer count = get(key);
      put(key, count == null ? 1 : count + 1);
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    public Integer remove(Object key) {
      if (!containsKey(key)) return null;

      int count = get(key);
      if (count > 1) return put((T) key, count - 1);

      return super.remove(key);
    }
  }

  static class FastWriter {
    private final StringBuilder out;

    public FastWriter() {
      out = new StringBuilder(IO_BUFFERS);
    }

    public FastWriter print(Object object) {
      out.append(object);
      return this;
    }

    public FastWriter print(String format, Object... args) {
      out.append(String.format(format, args));
      return this;
    }

    public FastWriter println(Object object) {
      out.append(object).append("\n");
      return this;
    }

    public void close() throws IOException {
      System.out.print(out);
    }
  }

  static class FastReader {
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public FastReader() {
      din = new DataInputStream(System.in);
      buffer = new byte[IO_BUFFERS];
      bufferPointer = bytesRead = 0;
    }

    public FastReader(String file_name) throws IOException {
      din = new DataInputStream(new FileInputStream(file_name));
      buffer = new byte[IO_BUFFERS];
      bufferPointer = bytesRead = 0;
    }

    public String readLine() throws IOException {
      StringBuilder sb = new StringBuilder();
      int c;
      while ((c = read()) != -1) {
        if (c == '\n') {
          if (sb.length() > 0) break;
          else continue;
        }
        sb.append((char) c);
      }
      return sb.toString();
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
      bytesRead = din.read(buffer, bufferPointer = 0, IO_BUFFERS);
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

    int[] fillIntegerArray(int n) throws IOException {
      int[] array = new int[n];
      for (int i = 0; i < n; i++) array[i] = nextInt();
      return array;
    }

    long[] fillLongArray(int n) throws IOException {
      long[] array = new long[n];
      for (int i = 0; i < n; i++) array[i] = nextLong();
      return array;
    }
  }
}
