import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
static class Interval {
int start, end;
long value;
public Interval(int start, int end, long value) {
        this.start = start;
        this.end = end;
        this.value = value;
}
}

// Complete the arrayManipulation function below.
static long arrayManipulation(int n, int[][] queries) {
        long K = queries.length;
        ArrayList<Interval> startIntervals = new ArrayList<>();
        ArrayList<Interval> endIntervals = new ArrayList<>();

        for (int i = 0; i < K; i++) {
                Interval interval = new Interval(queries[i][0]-1, queries[i][1], queries[i][2]);
                startIntervals.add(interval);
                endIntervals.add(interval);
        }

        Collections.sort(startIntervals, new Comparator<Interval>(){
                        @Override
                        public int compare(Interval o1, Interval o2) {
                                return o1.start - o2.start;
                        }
                });
        Collections.sort(endIntervals, new Comparator<Interval>(){
                        @Override
                        public int compare(Interval o1, Interval o2) {
                                return o1.end - o2.end;
                        }
                });

        int i = 0, j = 0;
        long maxValue = Long.MIN_VALUE;
        long value = 0;
        Interval startInterval, endInterval;
        while (i < K) {
                startInterval = startIntervals.get(i);
                endInterval = endIntervals.get(j);
                if (endInterval.end <= startInterval.start) {
                        value -= endInterval.value;
                        j++;
                } else {
                        value += startInterval.value;
                        i++;
                        maxValue = Math.max(maxValue, value);
                }
        }
        return maxValue;
}

private static final Scanner scanner = new Scanner(System.in);

public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
                String[] queriesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 3; j++) {
                        int queriesItem = Integer.parseInt(queriesRowItems[j]);
                        queries[i][j] = queriesItem;
                }
        }

        long result = arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
}
}
