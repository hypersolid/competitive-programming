import java.io.*;
import java.util.*;

public class Solution {
    public static long mod=1000000007;
    public static long[] T, N, E, L, a;

    public static long acrossTheShortBridge(int k) {
        return a[k] * 2;
    }

    public static long acrossTheLongBridge(int k) {
        return a[k] * 3;
    }

    public static long interGraph(int k) {
        return 2 * EN(k);
    }

    public static long N2(int k) {
        return N[k-1] * N[k-1] % mod;
    }

    public static long EN(int k) {
        return E[k-1] * N[k-1] % mod;
    }

    public static long headsToAllNodes(int k) {
        return 4 * N[k-1] * a[k] + 4 * N[k-1] * 2 * a[k] + 8 * E[k-1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int K = scanner.nextInt();
        a = new long[K + 1];
        N = new long[K + 1];
        E = new long[K + 1];
        T = new long[K + 1];
        L = new long[K + 1];

        N[0] = 1;
        E[0] = 0;
        T[0] = 0;
        L[0] = 0;

        scanner.nextLine();
        for (int k = 1; k <= K; k++) {
            a[k] = scanner.nextInt();

            N[k] = 4 * N[k - 1] + 2;


            L[k] = L[k-1] * 2 + acrossTheLongBridge(k);


            E[k] = 3 * E[k-1] + 3 * N[k-1] * L[k-1] // to other graphs
                    + N[k-1] * acrossTheShortBridge(k)
                    + 2 * N[k-1] * acrossTheLongBridge(k)
                    + 3 * a[k] + 2 * L[k-1] // to heads
                    + E[k-1]; // this graph path sum



            T[k] = 4 * T[k-1] // graph internal sums
                    + a[k] // inter heads sum
                    + 4 * N2(k) * acrossTheLongBridge(k) // times across the long bidge
                    + 2 * N2(k) * acrossTheShortBridge(k) // times across the short bidge
                    + 6 * interGraph(k) // inter-graph combinations
                    + headsToAllNodes(k); // from heads to all nodes

            N[k] %= mod;
            T[k] %= mod;
            E[k] %= mod;
            L[k] %= mod;
        }

        System.out.println(T[K] % mod);
    }
}
