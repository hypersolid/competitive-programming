import java.util.*;

public class Solution {
  static int[] expenditures;
  static int N, D, notifications = 0;
  static PriorityQueue<Integer> minHeap, maxHeap;

  static void shiftLeft() {
    if (!maxHeap.isEmpty() && maxHeap.size() > minHeap.size() + 1) minHeap.offer(maxHeap.poll());
  }

  static void shiftRight() {
    if (!minHeap.isEmpty() && minHeap.size() > maxHeap.size() + 1) maxHeap.offer(minHeap.poll());
  }

  static void insert(int value) {
    if (!maxHeap.isEmpty() && value > minHeap.peek()) {
      maxHeap.offer(value);
      shiftLeft();
    } else {
      minHeap.offer(value);
      shiftRight();
    }
  }

  static int medianx2() {
    if (minHeap.size() == maxHeap.size()) return maxHeap.peek() + minHeap.peek();
    if (minHeap.size() > maxHeap.size()) return minHeap.peek() * 2;
    else return maxHeap.peek() * 2;
  }

  static void remove(int value) {
    if (minHeap.peek() >= value) {
      minHeap.remove(value);
      shiftLeft();
    } else {
      maxHeap.remove(value);
      shiftRight();
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    N = scanner.nextInt();
    D = scanner.nextInt();

    minHeap = new PriorityQueue<>(Collections.reverseOrder());
    maxHeap = new PriorityQueue<>();

    expenditures = new int[N];

    for (int n = 0; n < N; n++) expenditures[n] = scanner.nextInt();
    for (int i = 0; i < D; i++) insert(expenditures[i]);
    for (int i = D; i < N; i++) {
      if (expenditures[i] >= medianx2()) notifications++;
      if (expenditures[i] != expenditures[i - D]) {
        insert(expenditures[i]);
        remove(expenditures[i - D]);
      }
    }

    System.out.println(notifications);
  }
}
