import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

// Complete the substrCount function below.
static long substrCount(int n, String s) {
        long result = 0;
        ArrayList<Character> symbols = new ArrayList<>();
        ArrayList<Long> counts = new ArrayList<>();

        // RLE
        symbols.add(s.charAt(0));
        counts.add(1L);

        for (int i = 1; i < s.length(); i++) {
                if (symbols.get(symbols.size()-1).equals(s.charAt(i))) {
                        counts.set(counts.size()-1, counts.get(counts.size()-1)+1);
                } else {
                        symbols.add(s.charAt(i));
                        counts.add(1L);
                }
        }

        // count special palindromes again
        for (int i = 1; i < symbols.size() - 1; i++) {
                if (counts.get(i) != 1) continue;
                if (symbols.get(i-1).equals(symbols.get(i+1))) {
                        result += Math.min(counts.get(i-1),counts.get(i+1));
                }
        }

        for (int i = 0; i < symbols.size(); i++) {
                long N = counts.get(i);
                result += (1+N)*N/2;
        }

        return result;
}

private static final Scanner scanner = new Scanner(System.in);

public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
}
}
