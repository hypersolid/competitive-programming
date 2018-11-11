import java.util.*;

public class Solution {
  static HashMap<String, Integer> substrings = new HashMap<>(10000);
  static String source;

  static long solve() {
    for (int i = 0; i < source.length(); i++) {
      for (int j = i; j < source.length(); j++) {
        String substring = source.substring(i, j + 1);

        char[] chars = substring.toCharArray();
        Arrays.sort(chars);
        String sortedSubstring = new String(chars);

        if (!substrings.containsKey(sortedSubstring)) substrings.put(sortedSubstring, 0);
        substrings.put(sortedSubstring, substrings.get(sortedSubstring) + 1);
      }
    }

    long result = 0;
    for (Map.Entry<String, Integer> entry : substrings.entrySet()) {
      // binomial coefficient n!/(n-m)!m! when m == 2 equals n * (n - 1) / 2
      result += entry.getValue() * (entry.getValue() - 1) / 2;
    }

    return result;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int T = scanner.nextInt();
    scanner.nextLine();

    for (int i = 0; i < T; i++) {
      source = scanner.nextLine().trim();
      substrings.clear();
      System.out.println(solve());
    }
  }
}
