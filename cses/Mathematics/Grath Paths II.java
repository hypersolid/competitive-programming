import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;

public class Task {
  private static final int IO_BUFFERS = 1024 * 1024;
  private static final long MODULO = (long) Math.pow(10, 9) + 7;
  private static final BigInteger BIG_MODULO = BigInteger.valueOf(MODULO);
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
    int N, M, K, A, B;
    long[][] m;
    Numbers ns = new Numbers();

    public Solution() throws IOException {
      readInput();
      solve();
    }

    void readInput() throws IOException {
      N = reader.nextInt();
      M = reader.nextInt();
      K = reader.nextInt();

      m = new long[N][N];
      for (int i = 0; i < N; i++) Arrays.fill(m[i], LIMIT);

      for (int i = 0; i < M; i++) {
        int from = reader.nextInt();
        int to = reader.nextInt();
        int weight = reader.nextInt();
        m[from - 1][to - 1] = Math.min(m[from - 1][to - 1], weight);
      }
    }

    void solve() {
      m = ns.mpow(m, K);
      writer.println(m[0][N - 1] == LIMIT ? -1 : m[0][N - 1]);
    }
  }

  static class Numbers {
    long[][] mpow(long[][] a, long k) {
      int s = a.length;
      long[][] result = new long[s][s];
      for (int i = 0; i < s; i++) {
        Arrays.fill(result[i], LIMIT);
        result[i][i] = 0;
      }

      while (k > 0) {
        if (k % 2 == 1) result = mmul(result, a);
        a = mmul(a, a);
        k /= 2;
      }

      return result;
    }

    long[][] mmul(long[][] a, long[][] b) {
      int n = a.length, m = b[0].length, pa = a[0].length, pb = b.length;

      if (pa != pb)
        throw new RuntimeException(
            String.format("Wrong matrix dimensions %dx%d * %dx%d", n, pa, pb, m));

      long[][] c = new long[n][m];
      for (int i = 0; i < n; i++) Arrays.fill(c[i], LIMIT);

      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < pa; ++j) {
          for (int k = 0; k < m; ++k) {
            c[i][j] = Math.min(c[i][j], a[i][k] + b[k][j]);
          }
        }
      }
      return c;
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
