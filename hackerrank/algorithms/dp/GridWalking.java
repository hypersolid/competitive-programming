import java.io.*;
import java.util.*;

public class Solution {
    static     int T, M, N = 0;
    static     int[] coordinates;
    static     int[] dimensions;
    static     long[][] walks;
    static     long[][] combinations;
    static     long[][] routes;
    static final long modulo = (int) Math.pow(10, 9) + 7;

    public static void walk(int n) {
        long[] buffer1 =  new long[dimensions[n]];
        long[] buffer2 =  new long[dimensions[n]];
        buffer1[coordinates[n] - 1] = 1;

        for (int m = 0; m < M; m++) {
            for (int i = 0; i < dimensions[n]; i++) {
                if (i > 0) {
                    buffer2[i - 1] += buffer1[i];
                    buffer2[i - 1] %= modulo;
                }
                if (i < dimensions[n] - 1) {
                    buffer2[i + 1] += buffer1[i];
                    buffer2[i + 1] %= modulo;
                }
            }
            for (int i = 0; i < dimensions[n]; i++) {
                buffer1[i] = buffer2[i];
                walks[n][m] += buffer2[i];
                walks[n][m] %= modulo;
                buffer2[i] = 0;
            }
        }
    }

    public static void combine(int N, int K) {
        combinations = new long[N + 1][K + 1];
        for (int n = 0; n <= N; n++) {
            combinations[n][0] = 1;
            combinations[n][n] = 1;
            combinations[n][1] = n;
        }

        for (int n = 1; n <= N; n++) {
            for (int k = 1; k < n; k++) {
                combinations[n][k] = combinations[n-1][k-1] + combinations[n-1][k];
                combinations[n][k] %= modulo;
            }
        }
    }

    public static void route() {
        for (int m = 1; m <= M; m++) routes[1][m] = walks[0][m-1];

        for (int n = 2; n <= N; n++) {
            for (int m = 1; m <= M; m++) {
                for (int i = 0; i <= m; i++) {
                    if (i == 0) routes[n][m] += walks[n - 1][m - 1];
                    else if (i == m) routes[n][m] += routes[n - 1][m];
                    else {
                        routes[n][m] += (
                                combinations[m][i] * (
                                        (
                                                routes[n - 1][m - i]  * walks[n - 1][i - 1]
                                        ) % modulo
                                )
                        ) % modulo;
                    }
                    routes[n][m] %= modulo;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        T = scanner.nextInt();

        // 1) For each dimension d: use a dynamic programming algorithm to find the number of possible walks for each number of steps. This algorithm focuses on a single dimension. Results saved in, say: result_per_dimension(d, s), d=dimension, s=number_of_steps.
        // 2) Calculate combinations c(n,k) for n = 0 to M, for k = 0 to n. Results saved in say: c(n,k).
        // 3) Use another dynamic programing algorithm to calculate the final results, per dimension and per number of steps. results[d,s] = sum{i>=0,i<=s}(c(s,i)*results(d-1,i)*result_per_dimension(d,s-i))
        // 4) return results(numDimensions, M)

        combine(300, 300);

        for (int t = 0; t < T; t++) {
            N = scanner.nextInt();
            M = scanner.nextInt();
            coordinates = new int[N];
            dimensions = new int[N];
            walks = new long[N][M];
            routes = new long[N + 1][M + 1];

            for (int n = 0; n < N; n++) coordinates[n] = scanner.nextInt();
            for (int n = 0; n < N; n++) dimensions[n] = scanner.nextInt();
            for (int n = 0; n < N; n++) walk(n);

            route();

            System.out.println(routes[N][M]);
        }

    }
}
