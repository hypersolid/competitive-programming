static class Numbers {
  long[] factorials;

  private final long M;
  private HashMap<Long, Long> fibHash = new HashMap<>();

  public Numbers(long m) {
    M = m;
  }

  long f(int n) {
    if (factorials == null || factorials.length < n) generateFactorials(n);
    return factorials[n];
  }

  long c(int k, int n) {
    return factorials[n] * Numbers.modInverse(factorials[k] * factorials[n - k], MODULO) % MODULO;
  }

  static long _modInverse(long a, long b) {
    long t, q, x0 = 0, x1 = 1;
    if (b == 1) return 1;
    while (a > 1) {
      q = a / b;
      t = b;
      b = a % b;
      a = t;
      t = x0;
      x0 = x1 - q * x0;
      x1 = t;
    }
    return x1;
  }

  static long modInverse(long a, long b) {
    long result = _modInverse(a, b);
    return result < 0 ? result + b : result;
  }

  void generateFactorials(int n) {
    factorials = new long[n + 1];
    factorials[0] = 1;
    factorials[1] = 1;
    for (int i = 2; i <= n; i++) factorials[i] = factorials[i - 1] * i % M;
  }

  long[][] mpow(long[][] a, long k) {
    int s = a.length;
    long[][] result = new long[s][s];
    for (int i = 0; i < s; i++) result[i][i] = 1;

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
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < pa; ++j) {
        for (int k = 0; k < m; ++k) {
          c[i][j] = (c[i][j] + (a[i][k] * b[k][j]) % MODULO) % MODULO;
        }
      }
    }
    return c;
  }

  long fastFib(long n) {
    if (n < 0) return 0;
    if (n < 2) return 1;
    if (fibHash.containsKey(n)) return fibHash.get(n);
    long k = n / 2;
    if (n % 2 == 0) {
      fibHash.put(n, (fastFib(k) * fastFib(k) + fastFib(k - 1) * fastFib(k - 1)) % M);
    } else {
      fibHash.put(n, (fastFib(k) * fastFib(k + 1) + fastFib(k - 1) * fastFib(k)) % M);
    }
    return fibHash.get(n);
  }
}
