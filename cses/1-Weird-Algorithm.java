import java.util.*;

class Wrapper {
    private static class Solution {
        Scanner scanner = new Scanner(System.in);

        public void solve() {
            long N = scanner.nextInt();
            System.out.print(N + " ");
            while (N > 1) {
                if (N % 2 == 1) {
                    N *= 3;
                    N++;
                } else {
                    N /= 2;
                }
                System.out.print(N + " ");
            }
        }
    }

    public static void main(String[] args) {
        (new Solution()).solve();
    }
}