import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(), M = scanner.nextInt();

        if (N == 0) {
            System.out.println(1);
            return;
        }

        int[] coins = new int[M];
        long[] combinations = new long[N + 1];
        combinations[0] = 1;
        for (int m = 0; m < M; m++) coins[m] = scanner.nextInt();

        for (int m = 0; m < M; m++) {
            for (int n = 0; n < N; n++) {
                if (combinations[n] == 0) continue;
                int sum = n + coins[m];
                if (sum <= N) combinations[sum] += combinations[n];
            }
        }

        System.out.println(combinations[N]);
    }
}
