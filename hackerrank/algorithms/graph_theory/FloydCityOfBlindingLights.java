import java.io.*;
import java.util.*;

public class Solution {

public static void route(int N, int[][] distances) {
        for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                        if (distances[i][k] == Integer.MAX_VALUE) continue;
                        for (int j = 0; j < N; j++) {
                                if (distances[k][j] == Integer.MAX_VALUE) continue;
                                distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                        }
                }
        }
}

public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int[][] distances = new int[N][N];
        for (int n = 0; n < N; n++) {
                Arrays.fill(distances[n], Integer.MAX_VALUE);
                distances[n][n] = 0;
        }

        for (int m = 0; m < M; m++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int r = scanner.nextInt();
                distances[x - 1][y - 1] = Math.min(distances[x - 1][y - 1], r);
        }

        route(N, distances);

        int Q = scanner.nextInt();
        for (int q = 0; q < Q; q++) {
                int src = scanner.nextInt();
                int dst = scanner.nextInt();
                int result = distances[src - 1][dst - 1];
                System.out.println(result == Integer.MAX_VALUE ? -1 : result);
        }
}
}
