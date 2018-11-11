import static java.util.stream.Collectors.joining;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

public class Solution {
  // Complete the freqQuery function below.
  static List<Integer> freqQuery(List<int[]> queries) {
    HashMap<Integer, Integer> frequencies = new HashMap<>();
    int[] quantities = new int[queries.size() + 1];
    List<Integer> result = new ArrayList<>();
    int frequency, value;

    for (int[] query : queries) {
      value = query[1];
      switch (query[0]) {
        case 1:
          // process fq
          frequency = frequencies.getOrDefault(value, 0);
          frequencies.put(value, frequency + 1);
          // process qt
          quantities[frequency]--;
          quantities[frequency + 1]++;
          break;
        case 2:
          // process fq
          frequency = frequencies.getOrDefault(value, 0);
          if (frequency == 0) break;
          if (frequency == 1) frequencies.remove(value);
          else frequencies.put(value, frequency - 1);
          // process qt
          quantities[frequency]--;
          quantities[frequency - 1]++;
          break;
        case 3:
          if (value >= quantities.length) result.add(0);
          else result.add(quantities[value] > 0 ? 1 : 0);
          break;
      }
    }

    return result;
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
      int q = Integer.parseInt(bufferedReader.readLine().trim());
      List<int[]> queries = new ArrayList<>(q);
      Pattern p = Pattern.compile("^(\\d+)\\s+(\\d+)\\s*$");
      for (int i = 0; i < q; i++) {
        int[] query = new int[2];
        Matcher m = p.matcher(bufferedReader.readLine());
        if (m.matches()) {
          query[0] = Integer.parseInt(m.group(1));
          query[1] = Integer.parseInt(m.group(2));
          queries.add(query);
        }
      }
      List<Integer> ans = freqQuery(queries);
      try (BufferedWriter bufferedWriter =
          new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")))) {
        bufferedWriter.write(ans.stream().map(Object::toString).collect(joining("\n")) + "\n");
      }
    }
  }
}
