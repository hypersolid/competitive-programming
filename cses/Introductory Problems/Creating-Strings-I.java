import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

class Wrapper {
  public static void main(String[] args) {
    (new Solution()).solve();
  }

  private static class Solution {
    Scanner scanner = new Scanner(System.in);
    String s;
    ArrayList<String> result = new ArrayList();
    boolean[] taken;
    char[] buffer;
    char[] letters;

    private void generate(int position) {
      if (position == s.length()) {
        result.add(new String(buffer));
        return;
      }

      int maxChar = -1;
      for (int i = 0; i < s.length(); i++) {
        if (taken[i]) continue;
        if (maxChar == letters[i]) continue;

        maxChar = letters[i];
        taken[i] = true;
        buffer[position] = letters[i];
        generate(position + 1);
        taken[i] = false;
      }
    }

    public void solve() {
      s = scanner.next();
      letters = s.toCharArray();
      Arrays.sort(letters);

      taken = new boolean[s.length()];
      buffer = new char[s.length()];

      generate(0);

      System.out.println(result.size());
      System.out.print(result.stream().collect(Collectors.joining("\n")));
    }
  }
}
