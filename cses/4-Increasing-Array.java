import java.util.Scanner;

class Wrapper {
    public static void main(String[] args) {
        (new Solution()).solve();
    }

    private static class Solution {
        Scanner scanner = new Scanner(System.in);

        public void solve() {
            long N = scanner.nextLong();
            long d = 0L;
            int m = 0;
            for (int i = 0; i < N; i++) {
                int x = scanner.nextInt();
                m = Math.max(x, m);
                d += m - x;
            }

            System.out.println(d);
        }
    }
}