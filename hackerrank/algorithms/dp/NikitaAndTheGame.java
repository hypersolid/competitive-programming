import java.io.*;
import java.util.*;

public class Solution {
    static long[] reverseSums, directSums, array;

    public static int split(int start, int end) {
        for (int i = start; i < end; i++) {
            if (directSums[i] - directSums[start - 1]  == reverseSums[i + 1] - reverseSums[end + 1]) {
                return 1 + Math.max(split(start, i), split(i + 1, end));
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int G = scanner.nextInt();
        for (int g = 0; g < G; g++) {
            int N = scanner.nextInt();
            array = new long[N];
            directSums = new long[N + 2];
            reverseSums = new long[N + 2];

            long directSum = 0, reverseSum = 0;

            for (int i = 0; i < N; i++) array[i] = scanner.nextInt();

            for (int i = 1; i <= N; i++) {
                directSum += array[i - 1];
                reverseSum += array[N - i];
                directSums[i] = directSum;
                reverseSums[N - i + 1] = reverseSum;
            }

            System.out.println(split(1, N));
        }
    }
}
