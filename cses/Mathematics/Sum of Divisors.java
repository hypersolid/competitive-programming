import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.stream.Collectors;

public class Solution {
  private static final int IO_BUFFERS = 128 * 1024;
  private static final long MODULO = (long) Math.pow(10, 9) + 7;
  private static final long LIMIT = (long) Math.pow(10, 14);

  private static FastReader reader = new FastReader();
  private static FastWriter writer = new FastWriter();

  private static long N;

  private static void readInput() throws IOException {
    N = reader.nextLong();
  }

  private static void solve() {
    long K = 1000000L;
    long result = 0L;

    for (long i = 1; i <= N / K; i++) {
      long diff = N / i % MODULO;
      diff %= MODULO;
      diff *= i % MODULO;
      diff %= MODULO;

      result += diff;
      result %= MODULO;
    }

    for (long i = 1L; i < K; i++) {
      long start = N / (i + 1L) + 1L;
      long end = N / i;
      if (start <= end) {
        long sum = end - start + 1L;
        long number = start + end;
        long diff =
            BigInteger.valueOf(sum)
                .multiply(BigInteger.valueOf(number))
                .divide(BigInteger.valueOf(2))
                .mod(BigInteger.valueOf(MODULO))
                .multiply(BigInteger.valueOf(i))
                .longValue();

        result += diff % MODULO;
        result %= MODULO;
      }
    }
    writer.print(result % MODULO);
  }

  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    try {
      int T = 1;
      for (int t = 0; t < T; t++) {
        readInput();
        solve();
      }
      writer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.err.println("\ntime: " + (System.currentTimeMillis() - startTime) + "ms");
  }

  static void debug(int[] a) {
    System.out.println(Arrays.toString(a));
  }

  static void debug(int[][] a) {
    System.out.println(Arrays.deepToString(a));
  }

  static void debug(long[] a) {
    System.out.println(Arrays.toString(a));
  }

  static void debug(long[][] a) {
    System.out.println(Arrays.deepToString(a));
  }

  static void debug(Object[] a) {
    System.out.println(Arrays.toString(a));
  }

  static void debug(Object[][] a) {
    System.out.println(Arrays.deepToString(a));
  }

  static void debug(Object o) {
    System.out.println(o);
  }

  /** Utility class to calculate stats on collection quickly * */
  static class Stats {
    long min = Long.MAX_VALUE;
    long max = Long.MIN_VALUE;
    long sum = 0;

    public Stats(List<? extends Number> list) {
      for (Number el : list) process(el.longValue());
    }

    private void process(long el) {
      sum += el;
      min = Math.min(min, el);
      max = Math.max(max, el);
    }
  }

  /** Output processing class for competitions */
  static class FastWriter {
    private final StringBuilder out;

    public FastWriter() {
      out = new StringBuilder(Solution.IO_BUFFERS);
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

  /** Input processing class for competitions */
  static class FastReader {
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public FastReader() {
      din = new DataInputStream(System.in);
      buffer = new byte[Solution.IO_BUFFERS];
      bufferPointer = bytesRead = 0;
    }

    public FastReader(String file_name) throws IOException {
      din = new DataInputStream(new FileInputStream(file_name));
      buffer = new byte[Solution.IO_BUFFERS];
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
      bytesRead = din.read(buffer, bufferPointer = 0, Solution.IO_BUFFERS);
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

    ArrayList<Pair<Integer>> fillPairList(int n) throws IOException {
      ArrayList<Pair<Integer>> array = new ArrayList<>();
      for (int i = 0; i < n; i++) array.add(new Pair<>(nextInt(), nextInt()));
      return array;
    }

    <T extends List<Long>> T fillList(T list, int n) throws IOException {
      for (int i = 0; i < n; i++) list.add(nextLong());
      return list;
    }
  }

  /** HMultiset utility class. It's just like TreeSet, but allows duplicate elements * */
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

  /** Tree Multiset utility class * */
  static class TMultiset<T extends Number> extends TreeMap<T, Integer> {
    private int size = 0;
    private T head = null, tail = null;

    void add(T value) {
      Integer count = get(value);
      size++;
      if (count == null) {
        put(value, 1);
        recalculateEdges();
      } else {
        put(value, count + 1);
      }
    }

    private void recalculateEdges() {
      try {
        head = firstKey();
      } catch (NoSuchElementException e) {
        head = null;
      }
      try {
        tail = lastKey();
      } catch (NoSuchElementException e) {
        tail = null;
      }
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    public Integer remove(Object key) {
      if (!containsKey(key)) {
        return null;
      }

      size--;

      Integer value = get(key);
      if (value > 1) {
        return put((T) key, value - 1);
      }

      Integer result = super.remove(key);
      recalculateEdges();
      return result;
    }

    @java.lang.Override
    public int size() {
      return size;
    }
  }

  /** Alias for ArrayList */
  static class AL<T> extends ArrayList<T> {}

  abstract static class Tuple<T extends Comparable<T>> implements Comparable<T> {
    @Override
    public int hashCode() {
      return Objects.hash(values());
    }

    private Object[] values() {
      Object[] result = new Object[fields().length];
      System.arraycopy(fields(), 0, result, 0, fields().length);
      return result;
    }

    @Override
    public String toString() {
      return Arrays.stream(fields())
          .map(
              (f) -> {
                try {
                  return f.get(this).toString();
                } catch (IllegalAccessException e) {
                  throw new RuntimeException(e);
                }
              })
          .collect(Collectors.joining(", ", "(", ")"));
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    public int compareTo(T other) {
      int result = 0;
      for (Field field : fields()) {
        try {
          result = ((T) field.get(this)).compareTo((T) field.get(other));
          if (result != 0) return result;
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }
      return result;
    }

    private Field[] fields() {
      Field[] fields;
      try {
        fields = (Field[]) this.getClass().getDeclaredField("fields").get(this.getClass());
      } catch (IllegalAccessException | NoSuchFieldException e) {
        throw new RuntimeException(e);
      }

      if (fields != null) return fields;

      fields = this.getClass().getFields();
      Arrays.sort(fields, Comparator.comparing(Field::getName));
      return fields;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      for (Field field : fields()) {
        try {
          if (!Objects.equals(field.get(this), field.get(o))) return false;
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }
      return true;
    }
  }

  static class Pair<T extends Comparable<T>> extends Tuple {
    protected static Field[] fields;

    public final T v1;
    public final T v2;

    public Pair(T v1, T v2) {
      this.v1 = v1;
      this.v2 = v2;
    }
  }

  static class Triple<T extends Comparable<T>> extends Pair<T> {
    protected static Field[] fields;

    public final T v3;

    public Triple(T v1, T v2, T v3) {
      super(v1, v2);
      this.v3 = v3;
    }
  }

  //    static {
  //        for (int i = 2; i < sieve.length; i++) if (sieve[i]) primes.add(i);
  //    }
  //
  //    private static boolean[] sieveOfEratosthenes(int n) {
  //        // Create a boolean array "prime[0..n]" and initialize
  //        // all entries it as true. A value in prime[i] will
  //        // finally be false if i is Not a prime, else true.
  //        boolean prime[] = new boolean[n+1];
  //        for(int i=0;i<n;i++)
  //            prime[i] = true;
  //
  //        for(int p = 2; p*p <=n; p++)
  //        {
  //            // If prime[p] is not changed, then it is a prime
  //            if(prime[p] == true)
  //            {
  //                // Update all multiples of p
  //                for(int i = p*p; i <= n; i += p)
  //                    prime[i] = false;
  //            }
  //        }
  //
  //        return prime;
  //    }

}
