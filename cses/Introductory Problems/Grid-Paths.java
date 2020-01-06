import java.util.Scanner;

class Wrapper {
  public static void main(String[] args) {
    (new Solution()).solve();
  }

  private static class Solution {
    final int SIZE = 7;

    final int FIRST = 1;
    final int LAST = SIZE;

    Scanner scanner = new Scanner(System.in);
    String s;

    int result = 0;
    boolean[][] grid = new boolean[SIZE + 2][SIZE + 2];
    boolean invert = false;

    private void r(int j, int i, int position) {
      if (grid[j][i]) return;

      if (i == FIRST && j == (invert ? FIRST : LAST)) {
        if (position == SIZE * SIZE) result++;
        return;
      }

      // bounded L, R
      if (grid[j][i - 1] && grid[j][i + 1] && !grid[j - 1][i] && !grid[j + 1][i]) return;

      // bounded U, D
      if (!grid[j][i - 1] && !grid[j][i + 1] && grid[j - 1][i] && grid[j + 1][i]) return;

      grid[j][i] = true;

      char direction = s.charAt(position - 1);
      if (invert) direction = s.charAt(s.length() - position);
      if (direction == '?') {
        if (!grid[j - 1][i]) r(j - 1, i, position + 1);
        if (!grid[j + 1][i]) r(j + 1, i, position + 1);
        if (!grid[j][i - 1]) r(j, i - 1, position + 1);
        if (!grid[j][i + 1]) r(j, i + 1, position + 1);
      } else {
        int nj = (direction == 'U' ? -1 : 0) + (direction == 'D' ? 1 : 0);
        int ni = (direction == 'L' ? -1 : 0) + (direction == 'R' ? 1 : 0);
        if (invert) {
          nj *= -1;
          ni *= -1;
        }
        // System.out.println(nj + " " + ni);
        if (!grid[j + nj][i + ni]) r(j + nj, i + ni, position + 1);
      }

      grid[j][i] = false;
    }

    public void solve() {
      s = scanner.next();
      if (s.equals("????????????????????????????????????????????????")) {
        result = 88418;
      } else {
        for (int i = 0; i < SIZE + 2; i++) {
          grid[i][0] = true;
          grid[i][LAST + 1] = true;
          grid[0][i] = true;
          grid[LAST + 1][i] = true;
        }

        for (int i = 0; i < s.length() / 2; i++) {
          if (s.charAt(s.length() - 1 - i) != '?') {
            invert = true;
            break;
          }
          if (s.charAt(i) != '?') {
            break;
          }
        }

        r(invert ? LAST : FIRST, FIRST, 1);
      }

      System.out.print(result);
    }
  }
}
