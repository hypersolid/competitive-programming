import java.io.*;
import java.util.*;

public class Solution {
  static int N, I;
  static int[] astronauts;

  static int trace(int ptr) {
    if (astronauts[ptr] == -1) return ptr;
    astronauts[ptr] = trace(astronauts[ptr]);
    return astronauts[ptr];
  }

  static void join(int p1, int p2) {
    int leftRoot = trace(p1);
    int rightRoot = trace(p2);
    if (leftRoot != rightRoot) astronauts[rightRoot] = leftRoot;
  }

  static long countSetsProduct() {
    HashMap<Integer, Integer> hash = new HashMap<>();

    for (int n = 0; n < N; n++) {
      int ptr = astronauts[n] == -1 ? n : trace(n);
      if (!hash.containsKey(ptr)) hash.put(ptr, 1);
      else hash.put(ptr, hash.get(ptr) + 1);
    }

    long result = 0;
    long candidatesLeft = N;
    for (Integer country : hash.values()) {
      candidatesLeft -= country;
      result += country * candidatesLeft;
    }

    return result;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    N = scanner.nextInt();
    I = scanner.nextInt();

    astronauts = new int[N];
    Arrays.fill(astronauts, -1);

    for (int i = 0; i < I; i++) join(scanner.nextInt(), scanner.nextInt());

    System.out.println(countSetsProduct());
  }
}
