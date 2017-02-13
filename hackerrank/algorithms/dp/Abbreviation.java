import java.util.Scanner;

public class Solution {

    static String a, b;
    static int[][] dp;

    static boolean compare(int p1, int p2) {

        if (p1 < 0 && p2 < 0) return true;
        if (p2 < 0) return compare(p1 - 1, p2);
        if (p1 < 0) return false;

        char ch1 = a.charAt(p1);
        char ch2 = b.charAt(p2);

        if (dp[p1][p2] != 0) return dp[p1][p2] == 1;

        if (Character.isUpperCase(ch1)) {
            if (ch1 == ch2) dp[p1][p2] = compare(p1 - 1, p2 - 1) ? 1 : 2;
            else dp[p1][p2] = 2;
        } else {
            if (Character.toUpperCase(ch1) == ch2 && compare(p1 - 1, p2 - 1)) dp[p1][p2] = 1;
            else dp[p1][p2] = compare(p1 - 1, p2) ? 1 : 2;
        }

        return dp[p1][p2] == 1;
    }

    public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        scanner.nextLine();
	    for (int n = 0; n < N; n++) {
            a = scanner.nextLine();
            b = scanner.nextLine();
            dp = new int[a.length()][b.length()];
            System.out.println(compare(a.length() - 1, b.length() - 1) ? "YES" : "NO");
        }
    }
}
