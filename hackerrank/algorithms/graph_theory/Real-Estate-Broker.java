import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
  static int M, N, V, s, t;

  static boolean dfs(int[][] G, boolean[] visited, int i, int[] path) {
    int currentVertex = path[i];
    if (visited[currentVertex]) return false;
    if (currentVertex == t) return true;
    visited[currentVertex] = true;

    for (int k = 0; k < V; k++) {
      if (G[currentVertex][k] > 0) {
        path[i + 1] = k;
        if (dfs(G, visited, i + 1, path)) return true;
      }
    }

    return false;
  }

  static int[][] buildResidualGraph(int[][] clients, int[][] houses) {
    int[][] G = new int[V][V];
    for (int i = 0; i < M; i++) G[s][i] = 1; // source to clients
    for (int i = M; i < M + N; i++) G[i][t] = 1; // houses to sink

    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        if (houses[j][0] > clients[i][0] && houses[j][1] <= clients[i][1]) {
          G[i][M + j] = 1; // clients to houses
        }
      }
    }

    return G;
  }

  /*
   * Complete the realEstateBroker function below.
   */
  static int realEstateBroker(int[][] clients, int[][] houses) {
    M = clients.length;
    N = houses.length;
    V = M + N + 2; // +2 for source and sink
    s = M + N;
    t = M + N + 1;

    int maxFlow = 0;
    int flow;
    boolean[] visited = new boolean[V];
    int[] path = new int[V];
    path[0] = s; // always start with source
    int[][] G = buildResidualGraph(clients, houses);

    while (dfs(G, visited, 0, path)) {
      flow = Integer.MAX_VALUE;

      // find max flow
      for (int i = 1; path[i - 1] != t; i++) {
        flow = Math.min(G[path[i - 1]][path[i]], flow);
      }

      // reverse flow
      for (int i = 1; path[i - 1] != t; i++) {
        G[path[i - 1]][path[i]] -= flow;
        G[path[i]][path[i - 1]] += flow;
      }

      // clear visited marks
      Arrays.fill(visited, false);

      maxFlow += flow;
    }

    return maxFlow;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] nm = scanner.nextLine().split(" ");

    int n = Integer.parseInt(nm[0].trim());

    int m = Integer.parseInt(nm[1].trim());

    int[][] clients = new int[n][2];

    for (int clientsRowItr = 0; clientsRowItr < n; clientsRowItr++) {
      String[] clientsRowItems = scanner.nextLine().split(" ");

      for (int clientsColumnItr = 0; clientsColumnItr < 2; clientsColumnItr++) {
        int clientsItem = Integer.parseInt(clientsRowItems[clientsColumnItr].trim());
        clients[clientsRowItr][clientsColumnItr] = clientsItem;
      }
    }

    int[][] houses = new int[m][2];

    for (int housesRowItr = 0; housesRowItr < m; housesRowItr++) {
      String[] housesRowItems = scanner.nextLine().split(" ");

      for (int housesColumnItr = 0; housesColumnItr < 2; housesColumnItr++) {
        int housesItem = Integer.parseInt(housesRowItems[housesColumnItr].trim());
        houses[housesRowItr][housesColumnItr] = housesItem;
      }
    }

    int result = realEstateBroker(clients, houses);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedWriter.close();
  }
}
