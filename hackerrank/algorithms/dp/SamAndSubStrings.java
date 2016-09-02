import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String src = scanner.nextLine().trim();
        int N = src.length();
        int[] digits = new int[N];
        for (int i = 0; i < N; i++) digits[i] = src.charAt(i) - '0';
        long sum = 0;
        long modulo = (long) Math.pow(10, 9) + 7;
        long f = 1;
        for(int i = N-1; i >= 0; i--) {
            sum = (sum + digits[i] * f * (i + 1)) % modulo;
            f = (f * 10 + 1) % modulo;
        }
        System.out.println(sum);
    }
}
