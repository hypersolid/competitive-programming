import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    static int[] parents;
    static boolean[] machines;

    static int find(int source) {
      if (source == parents[source]) { return source; }
      int root = find(parents[source]);
      parents[source] = root;
      machines[source] = machines[root];
      return root;
    }

    static void union(int source, int target) {
      boolean infected = machines[find(source)] || machines[find(target)];
      parents[find(source)] = find(target);
      machines[find(source)] = infected;
    }

    static boolean joinUnlessHasTwoMachines(int source, int target) {
      if (machines[find(source)] && machines[find(target)]) {
        return false;
      }
      
      union(source, target);
      
      return true;
    }

    static void sortRoads(int[][] roads) {
      Comparator<int[]> descCostComp = (int[] row1, int[] row2) -> (row2[2] - row1[2]);
      Arrays.sort(roads, descCostComp);
    }

    // Complete the minTime function below.
    static int minTime(int N, int[][] roads, int[] machinesArray) {
      sortRoads(roads);

      parents = new int[N];
      machines = new boolean[N];
      for (int i = 0; i < N; i++) { parents[i] = i; }
      for (int machineCity: machinesArray) { 
        machines[machineCity] = true;
      }

      int result = 0;
      for (int[] edge: roads) {
        if (!joinUnlessHasTwoMachines(edge[0], edge[1])) {
          result += edge[2];
        }
      }
      return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] roads = new int[n - 1][3];

        for (int i = 0; i < n - 1; i++) {
            String[] roadsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int roadsItem = Integer.parseInt(roadsRowItems[j]);
                roads[i][j] = roadsItem;
            }
        }

        int[] machines = new int[k];

        for (int i = 0; i < k; i++) {
            int machinesItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            machines[i] = machinesItem;
        }

        int result = minTime(n, roads, machines);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

