import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

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
    int N, A, B;
    BigDecimal factorials[] = new BigDecimal[605];

    public Solution() throws IOException {
      readInput();
      solve();
    }

    void readInput() throws IOException {
      N = reader.nextInt();
      A = reader.nextInt();
      B = reader.nextInt();

      factorials[0] = new BigDecimal(1);
      factorials[1] = new BigDecimal(1);
      for (int i = 2; i < 605; i++) factorials[i] = factorials[i - 1].multiply(new BigDecimal(i));
    }

    BigDecimal c(int n, int k) {
      return factorials[n].divide(
          factorials[k].multiply(factorials[n - k]), 100, RoundingMode.HALF_EVEN);
    }

    void solve() {
      // (s-n)/x
      // SUM  (-1)^k * C(n,k) * C(s-x*k-1,n-1)
      // k=0

      BigDecimal result = new BigDecimal(0);
      for (int s = A; s <= B; s++) {
        BigDecimal sign = new BigDecimal(-1);
        for (int k = 0; k <= (s - N) / 6; k++) {
          sign = sign.negate();
          result = result.add(sign.multiply(c(N, k)).multiply(c(s - k * 6 - 1, N - 1)));
        }
      }
      writer.println(result.divide(new BigDecimal(6).pow(N), 6, RoundingMode.HALF_DOWN));
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
