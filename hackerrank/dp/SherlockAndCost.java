import java.io.*;
import java.util.*;

public class Solution {
    static int T, N;
    static int[] limits;
    static int[][] dp;

    static void getMax() {
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] + Math.abs(limits[i] - limits[i - 1]), dp[i - 1][1] + Math.abs(limits[i] - 1));
            dp[i][1] = Math.max(dp[i - 1][0] + Math.abs(limits[i - 1] - 1), dp[i - 1][1] + 0);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        T = scanner.nextInt();
        for (int t = 0; t < T; t++) {
            N = scanner.nextInt();
            limits = new int[N];
            for (int n = 0; n < N; n++) limits[n] = scanner.nextInt();

            dp = new int[N][2];
            // 0 - limit
            // 1 - 1

            getMax();

            System.out.println(Math.max(dp[N-1][0], dp[N-1][1]));
        }
    }
}
