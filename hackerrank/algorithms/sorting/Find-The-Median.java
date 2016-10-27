import java.io.*;
import java.util.*;

public class Solution {
  static int N, K;
  static int[] array;

  static void partition(int start, int finish) {
    if (start >= finish) return;

    int pivot = start;
    int left = pivot + 1;
    int right = finish;
    while (left < right) {
      while (left < right && array[left] <= array[pivot]) left++;
      while (left < right && array[right] >= array[pivot]) right--;
      if (left >= right) break;
      int tmp = array[left];
      array[left] = array[right];
      array[right] = tmp;
    }
    int ins = array[left] > array[pivot] ? left - 1 : left;
    int tmp = array[start];
    array[start] = array[ins];
    array[ins] = tmp;

    if (ins == K) return;

    if (ins < K) partition(ins + 1, finish);
    else partition(start, ins - 1);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    N = scanner.nextInt();
    K = N / 2;
    array = new int[N];
    for (int n = 0; n < N; n++) array[n] = scanner.nextInt();
    partition(0, N - 1);
    System.out.println(array[K]);
  }
}
