import java.util.Scanner;

class Wrapper {
  public static void main(String[] args) {
    (new Solution()).solve();
  }

  private static class Solution {
    Scanner scanner = new Scanner(System.in);

    public void solve() {
      String s = scanner.next();
      StringBuilder sb = new StringBuilder();
      int[] f = new int[26];

      for (int i = 0; i < s.length(); i++) {
        f[s.charAt(i) - 'A']++;
      }

      int odd = 0;
      for (int i = 0; i < 26; i++) {
        if (f[i] % 2 == 1) odd++;
      }
      if (odd > 1) {
        System.out.println("NO SOLUTION");
        return;
      }

      int oddIndex = -1;
      for (int i = 0; i < 26; i++) {
        if (f[i] % 2 == 1) oddIndex = i;
        else {
          for (int j = 0; j < f[i] / 2; j++) sb.append((char) ('A' + i));
        }
      }
      StringBuilder sb2 = new StringBuilder(sb);
      sb2.reverse();

      if (oddIndex != -1) {
        for (int i = 0; i < f[oddIndex]; i++) sb.append((char) ('A' + oddIndex));
      }

      sb.append(sb2);

      System.out.print(sb);
    }
  }
}
