class Solution {
  final long MODULO = (long) Math.pow(10, 9) + 7;
  final int MAX_SIZE = 20000 + 1;

  public int sumSubseqWidths(int[] A) {
    int N = A.length;
    if (N <= 1) {
      return 0;
    }

    long sum = 0;
    long power = 1;
    long[] powers = new long[N];
    powers[0] = 0;

    int[] sort = new int[MAX_SIZE];

    for (int i = 0; i < N; i++) {
      sort[A[i]]++;
    }
    int p = 0;
    for (int i = 0; i < MAX_SIZE && p < N; i++) {
      for (int j = 0; j < sort[i]; j++) {
        A[p++] = i;
      }
    }

    for (int i = 1; i < N; i++) {
      powers[i] = (powers[i - 1] + power) % MODULO;
      power = power * 2 % MODULO;
    }

    long acc = 0;
    for (int i = 1; i < N; i++) {
      acc += powers[N - 1 - (i - 1)] - powers[i - 1];
      sum = (sum + (A[i] - A[i - 1]) * acc) % MODULO;
    }

    return (int) sum;
  }
}
