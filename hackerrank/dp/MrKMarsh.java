import java.io.*;
import java.util.*;

public class Solution {
    static final char swamp = 'x';
    static int N, M;
    static char[][] field;
    static int[][][] lookup;
    static int u = 0, l = 1, d = 2, r = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        scanner.nextLine();
        field = new char[N][M];
        lookup = new int[N+2][M+2][4];

        for (int n = 0; n < N; n++) {
            String buffer = scanner.nextLine();
            for (int m = 0; m < M; m++) field[n][m] = buffer.charAt(m);
        }

        for (int n = 1; n <= N; n++) {
            for (int m = 1; m <= M; m++) {
                if (field[n - 1][m - 1] == swamp) continue;
                Arrays.fill(lookup[n][m], 1);

                lookup[n][m][u] += lookup[n-1][m][u];
                lookup[n][m][l] += lookup[n][m-1][l];
            }
        }

        for (int n = N; n > 0; n--) {
            for (int m = M; m > 0; m--) {
                if (field[n - 1][m - 1] == swamp) continue;
                lookup[n][m][d] += lookup[n+1][m][d];
                lookup[n][m][r] += lookup[n][m+1][r];
            }
        }

        int perimeter = 0;
        for (int n1 = 1; n1 <= N; n1++)
            for (int m1 = 1; m1 <= M; m1++) {
                if (field[n1 - 1][m1 - 1] == swamp) continue;
                int lN = n1 + lookup[n1][m1][d] - 1;
                int lM = m1 + lookup[n1][m1][r] - 1;
                if (2 * (lookup[n1][m1][d] - 1 + lookup[n1][m1][r] - 1) <= perimeter) continue;
                for (int n2 = lN; n2 >= n1 + 1; n2--)
                    for (int m2 = lM; m2 >= m1 + 1; m2--) {
                        if (field[n2 - 1][m2 - 1] == swamp) continue;
                        int dn = n2 - n1 + 1;
                        int dm = m2 - m1 + 1;
                        if (lookup[n2][m2][u] < dn || lookup[n2][m2][l]  < dm) continue;
                        perimeter = Math.max(perimeter, 2 * (dm + dn - 2));
                    }

            }


        System.out.println(perimeter > 0 ? perimeter : "impossible");
    }
}
