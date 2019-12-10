import java.util.Scanner;

class Wrapper {
    public static void main(String[] args) {
        (new Solution()).solve();
    }

    private static class Solution {
        Scanner scanner = new Scanner(System.in);


        public void solve() {
            int N = scanner.nextInt();
            StringBuilder sb = new StringBuilder(N);
            if (N == 2 || N == 3) {
                System.out.println("NO SOLUTION");
                return;
            }


            // odd
            for (int i = N - (N % 2 == 0 ? 1 : 0); i >= 1; i -= 2) sb.append(i).append(" ");
            // even
            for (int i = N - N % 2; i >= 2; i -= 2) sb.append(i).append(" ");

            System.out.println(sb);
        }
    }
}