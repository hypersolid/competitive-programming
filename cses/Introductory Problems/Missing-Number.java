class Wrapper {
  public static void main(String[] args) {
    (new Solution()).solve();
  }

  private static class Solution {
    Scanner scanner = new Scanner(System.in);

    public void solve() {
      int N = scanner.nextInt();
      long target = N * (N + 1L) / 2L;
      for (int i = 0; i < N - 1; i++) target -= scanner.nextInt();
      System.out.println(target);
    }
  }
}
