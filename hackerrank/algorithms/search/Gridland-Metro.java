import java.util.*;

public class Solution {
  static class Interval {
    public int start, end;

    public Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }

    public boolean merge(Interval prev) {
      boolean mergable = prev.end >= this.start;
      if (mergable) {
        this.start = Math.min(this.start, prev.start);
        this.end = Math.max(this.end, prev.end);
      }
      return mergable;
    }
  }

  static int N, M, K;
  static HashMap<Integer, ArrayList<Interval>> intervals = new HashMap<>();

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    M = scanner.nextInt();
    N = scanner.nextInt();
    K = scanner.nextInt();

    for (int i = 0; i < K; i++) {
      int row = scanner.nextInt();
      int start = scanner.nextInt();
      int finish = scanner.nextInt();

      if (!intervals.containsKey(row)) intervals.put(row, new ArrayList<>());
      intervals.get(row).add(new Interval(start, finish));
    }

    long occupied = 0;
    for (ArrayList<Interval> array : intervals.values()) {
      Collections.sort(
          array,
          new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
              if (i1.start > i2.start) return 1;
              if (i1.start < i2.start) return -1;
              return 0;
            }
          });

      for (int i = 0; i < array.size(); i++) {
        if (i < array.size() - 1 && array.get(i + 1).merge(array.get(i))) continue;
        occupied += array.get(i).end + 1 - array.get(i).start;
      }
    }

    System.out.println((long) M * N - occupied);
  }
}
