import java.io.*;
import java.util.*;

public class Solution {
    static int upperBound = 217286;
    static boolean[] primes = new boolean[upperBound + 1];
    static int[] primeReference = new int[upperBound + 1];

    public static void sieve() {
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        for (int i = 2; i <= Math.sqrt(upperBound); i++)
            for (int j = i * i; j <= upperBound; j+=i) primes[j] = false;

        int count = 0;
        for (int i = 0; i <= upperBound; i++) {
            if (primes[i]) ++count;
            primeReference[i] = count;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        sieve();

        for (int t = 0; t < T; t++) {
            int N = scanner.nextInt();
            int[] combinations = new int[N + 1];
            combinations[0] = 1;
            for (int i = 0; i < N; i++) {
                combinations[i + 1] += combinations[i];
                if (i + 4 <= N) combinations[i + 4] += combinations[i];
            }

            int total = combinations[N];
            System.out.println(primeReference[total]);
        }
    }
}
