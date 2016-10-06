import java.io.*;
import java.util.*;

public class Solution {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int A = scanner.nextInt();
    HashMap<Integer, Integer> aHash = new HashMap<>();
    for (int a = 0; a < A; a++) {
      int value = scanner.nextInt();
      if (aHash.containsKey(value)) aHash.put(value, aHash.get(value) + 1);
      else aHash.put(value, 0);
    }

    int B = scanner.nextInt();
    HashMap<Integer, Integer> bHash = new HashMap<>();
    for (int b = 0; b < B; b++) {
      int value = scanner.nextInt();
      if (bHash.containsKey(value)) bHash.put(value, bHash.get(value) + 1);
      else bHash.put(value, 0);
    }

    ArrayList<String> result = new ArrayList<>();
    for (Map.Entry<Integer, Integer> entry : bHash.entrySet()) {
      if (!aHash.containsKey(entry.getKey()) || aHash.get(entry.getKey()) < entry.getValue()) {
        result.add(Integer.toString(entry.getKey()));
      }
    }

    System.out.print(String.join(" ", result));
  }
}
