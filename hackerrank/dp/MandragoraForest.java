import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int t = 0; t < T; t++) {
            int N = scanner.nextInt();
            int S = 1;
            long sum = 0;
            long max = 0;
            long[] buffer = new long[N];
            ArrayList<Integer> array = new ArrayList<>();
            for (int i = 0; i < N; i++) array.add(scanner.nextInt());

            if (N == 1) {
                System.out.println(array.get(0));
                continue;
            }

            Collections.sort(array);
            for (int i = N - 1; i >= 0; i--) {
                sum += array.get(i);
                buffer[i] = sum;
            }

            for (int i = 0; i < N - 1; i++) {
                if (buffer[i] * S > buffer[i + 1] * (S + 1)) {
                    max = buffer[i] * S;
                    break;
                } else {
                    max = buffer[i + 1] * (S + 1);
                    S++;
                }
            }

            System.out.println(max);
        }
    }
}
