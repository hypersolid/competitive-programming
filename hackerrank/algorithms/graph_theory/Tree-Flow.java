import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
  static class Edge {
    public int from, to;
    public long weight, acc = 0;

    public Edge(int from, int to, long weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }
  }

  static HashMap<Integer, ArrayList<Edge>> connections = new HashMap<>(500000);
  static int N;
  static long acc;
  static LinkedList<Edge> queue = new LinkedList<>();

  public static void dfs() {
    while (!queue.isEmpty()) {
      Edge current = queue.removeFirst();
      acc += current.acc;
      for (Edge edge : connections.get(current.to)) {
        if (current.from == edge.to) continue;
        queue.addLast(edge);
        edge.acc = edge.weight + current.acc;
      }
    }
  }

  public static long traverseFrom(int node) {
    acc = 0;
    for (Edge edge : connections.get(node)) {
      edge.acc = edge.weight;
      queue.addLast(edge);
    }
    dfs();
    return acc;
  }

  public static void main(String[] args) {
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 2 * 1024 * 1024);
      N = Integer.parseInt(reader.readLine());

      for (int i = 0; i < N - 1; i++) {
        String[] tokens = reader.readLine().split(" ");
        int from = Integer.parseInt(tokens[0]);
        int to = Integer.parseInt(tokens[1]);
        int weight = Integer.parseInt(tokens[2]);

        if (!connections.containsKey(from)) connections.put(from, new ArrayList<>());
        if (!connections.containsKey(to)) connections.put(to, new ArrayList<>());

        connections.get(from).add(new Edge(from, to, weight));
        connections.get(to).add(new Edge(to, from, weight));
      }

      System.out.println(Math.min(traverseFrom(1), traverseFrom(N)));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
