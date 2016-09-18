import java.util.*;

public class Solution {
  public static class Edge {
    public int node;
    public int distance;

    public Edge(int node, int distance) {
      this.node = node;
      this.distance = distance;
    }
  }

  public static class Waypoint {
    public int node;
    public int mask;
    public int distance;

    public Waypoint(int node, int mask, int distance) {
      this.node = node;
      this.mask = mask;
      this.distance = distance;
    }

    public String toString() {
      return node + " " + Integer.toBinaryString(mask) + " " + distance;
    }
  }

  public static class WaypointComparator implements Comparator<Waypoint> {
    @Override
    public int compare(Waypoint wp1, Waypoint wp2) {
      if (wp1.distance > wp2.distance) return 1;
      else if (wp1.distance < wp2.distance) return -1;
      return 0;
    }
  }

  static int N, M, K;
  static HashMap<Integer, ArrayList<Edge>> edges = new HashMap<>();
  static HashMap<Integer, HashMap<Integer, Integer>> messages = new HashMap<>();

  static PriorityQueue<Waypoint> queue = new PriorityQueue<>(1000, new WaypointComparator());

  static int[] fishTypes;
  static int happyCats;
  static int[][] distances;

  public static void route(int i) {
    boolean[] visited = new boolean[N];
    PriorityQueue<Waypoint> dijkstra = new PriorityQueue<>(1000, new WaypointComparator());
    dijkstra.add(new Waypoint(i, -1, 0));

    while (!dijkstra.isEmpty()) {
      Waypoint wp = dijkstra.poll();
      if (visited[wp.node]) continue;
      visited[wp.node] = true;

      boolean validEnd = fishTypes[wp.node] != 0 || wp.node == 0 || wp.node == N - 1;
      if (wp.node != i && validEnd) {
        edges.get(i).add(new Edge(wp.node, wp.distance));
      } else {
        for (int j = 0; j < N; j++) {
          if (visited[j]) continue;
          if (distances[wp.node][j] == -1) continue;
          dijkstra.add(new Waypoint(j, -1, wp.distance + distances[wp.node][j]));
        }
      }
    }
  }

  public static long fanout(Waypoint wp) {
    messages.get(wp.node).put(wp.mask, wp.distance);

    if (wp.node == N - 1) {
      long minTime = Long.MAX_VALUE;
      for (Map.Entry<Integer, Integer> entry : messages.get(N - 1).entrySet())
        if ((wp.mask | entry.getKey()) == happyCats)
          minTime = Math.min(minTime, Math.max(wp.distance, entry.getValue()));
      if (minTime < Integer.MAX_VALUE) return minTime;
    }

    for (Edge neighborEdge : edges.get(wp.node)) {
      Waypoint nwp =
          new Waypoint(
              neighborEdge.node,
              wp.mask | fishTypes[neighborEdge.node],
              wp.distance + neighborEdge.distance);

      if (messages.get(nwp.node).containsKey(nwp.mask)) {
        if (nwp.distance < messages.get(nwp.node).get(nwp.mask)) {
          queue.add(nwp);
        }
      } else {
        queue.add(nwp);
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    N = scanner.nextInt();
    M = scanner.nextInt();
    K = scanner.nextInt();

    fishTypes = new int[N];
    happyCats = 0;

    distances = new int[N][N];
    for (int n = 0; n < N; n++) {
      distances[n][n] = 0;
      Arrays.fill(distances[n], -1);
    }

    for (int k = 0; k < K; k++) happyCats |= 1 << k;
    int zeroes = 0;
    for (int n = 0; n < N; n++) {
      int T = scanner.nextInt();
      if (T == 0) zeroes++;
      for (int t = 0; t < T; t++) {
        fishTypes[n] |= 1 << (scanner.nextInt() - 1);
      }
      edges.put(n, new ArrayList<>());
      messages.put(n, new HashMap<>());
    }

    // if graph contains a lot of void nodes (no shopping centers)
    // we can squeeze it to a smaller one
    if (zeroes > N * 0.8) {
      for (int m = 0; m < M; m++) {
        int X = scanner.nextInt() - 1, Y = scanner.nextInt() - 1, D = scanner.nextInt();
        distances[X][Y] = D;
        distances[Y][X] = D;
      }

      for (int i = 0; i < N; i++) if (fishTypes[i] != 0 || i == 0 || i == N - 1) route(i);
    } else {
      for (int m = 0; m < M; m++) {
        int X = scanner.nextInt() - 1, Y = scanner.nextInt() - 1, D = scanner.nextInt();
        edges.get(X).add(new Edge(Y, D));
        edges.get(Y).add(new Edge(X, D));
      }
    }

    // brute force solution in optimized graph
    queue.add(new Waypoint(0, fishTypes[0], 0));
    while (!queue.isEmpty()) {
      long result = fanout(queue.poll());
      if (result != -1) {
        System.out.println(result);
        return;
      }
    }
  }
}
