import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Solution {
  private static FastReader scanner = new FastReader();
  private static FastWriter log = new FastWriter();

  private static int N, T;
  private static ArrayList<Pair> tasks;

  private static void readInput() throws IOException {
    N = scanner.nextInt();

    tasks = scanner.fillPairList(N);
  }

  private static void solve() throws IOException {
    Collections.sort(tasks, (a, b) -> Integer.compare(a.val1, b.val1));
    //    System.out.println(tasks);
    long result = 0;
    long time = 0;
    for (Pair task : tasks) {
      time += task.val1;
      result += task.val2 - time;
    }
    log.println(result);
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
    //    System.err.println("\ntime: " + (System.currentTimeMillis() - startTime) + "ms");
  }

  /** Competition output utility class */
  static class FastWriter {
    private final StringBuilder bw;

    public FastWriter() {
      this.bw = new StringBuilder(1 << 24);
    }

    public void print(Object object) throws IOException {
      bw.append(object);
    }

    public void print(int i) throws IOException {
      bw.append(i);
    }

    public void print(long i) throws IOException {
      bw.append(i);
    }

    public void print(String s) throws IOException {
      bw.append(s);
    }

    public void print(String format, Object... args) throws IOException {
      bw.append(String.format(format, args));
    }

    public void println(Object object) throws IOException {
      print(object);
      print("\n");
    }

    public void close() throws IOException {
      System.out.print(bw);
    }
  }

  /** Competition input parser utility class */
  static class FastReader {
    private final int BUFFER_SIZE = 1 << 24;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public FastReader() {
      din = new DataInputStream(System.in);
      buffer = new byte[BUFFER_SIZE];
      bufferPointer = bytesRead = 0;
    }

    public FastReader(String file_name) throws IOException {
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

    //    String[] fillStringArray(int n) throws IOException {
    //      String[] array = new String[n];
    //      for (int i = 0; i < n; i++) array[i] = next();
    //      return array;
    //    }

    ArrayList<Pair> fillPairList(int n) throws IOException {
      ArrayList<Pair> list = new ArrayList<Pair>(n);
      for (int i = 0; i < n; i++) list.add(new Pair(nextInt(), nextInt()));
      return list;
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

    <T extends List<Long>> T fillList(T list, int n) throws IOException {
      for (int i = 0; i < n; i++) list.add(nextLong());
      return list;
    }
  }

  /** Usefull utility class * */
  static class Multiset extends TreeMap<Long, Long> {
    void add(Long value) {
      Long count = get(value);
      put(value, count == null ? 1 : count + 1);
    }

    @java.lang.Override
    public Long remove(Object key) {
      if (!containsKey(key)) return null;

      Long count = get(key);
      if (count > 1) return put((Long) key, count - 1);

      return super.remove(key);
    }

    public Long count() {
      return entrySet().stream().map((v) -> v.getValue()).reduce(0L, Long::sum);
    }
  }

  /** Usefull utility class * */
  static class IMultiset extends TreeMap<Integer, Integer> {
    void add(Integer value) {
      Integer count = get(value);
      put(value, count == null ? 1 : count + 1);
    }

    @java.lang.Override
    public Integer remove(Object key) {
      if (!containsKey(key)) return null;

      Integer count = get(key);
      if (count > 1) return put((Integer) key, count - 1);

      return super.remove(key);
    }

    public Integer count() {
      return entrySet().stream().map((v) -> v.getValue()).reduce(0, Integer::sum);
    }
  }

  static class ALI extends ArrayList<Integer> {}

  /** General purpose Pair utility class */
  static class Quad {
    int val1;
    int val2;
    int val3;
    int val4;

    public Quad(int val1, int val2, int val3, int val4) {
      this.val1 = val1;
      this.val2 = val2;
      this.val3 = val3;
      this.val4 = val4;
    }

    @java.lang.Override
    public java.lang.String toString() {
      return "Quad{" + "val1=" + val1 + ", val2=" + val2 + ", val3=" + val3 + ", val4=" + val4
          + '}';
    }
  }

  /** General purpose Pair utility class */
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
}
