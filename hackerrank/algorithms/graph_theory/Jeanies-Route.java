import java.io.*;
import java.util.*;

public class Solution {
  public static class Node {
    public boolean deliveryNeeded = false;

    ArrayList<Edge> edges = new ArrayList<>();
  }

  public static class Edge {
    public Node source, target;
    public int weight;
    public boolean visited = false;
    public long deliveryDistance = 0;
    public long maxDistance = 0;

    public long estimate() {
      return deliveryDistance - maxDistance;
    }

    public Edge(Node source, Node target, int weight) {
      this.source = source;
      this.target = target;
      this.weight = weight;
    }
  }

  static int N, K;
  static ArrayList<Node> nodes, deliveries;

  static void startFrom(Edge prevEdge, Edge edge) {
    Node node = edge.target;
    if (!edge.visited) {
      for (Edge nextEdge : node.edges) {
        if (nextEdge.target == edge.source) continue;
        startFrom(edge, nextEdge);
      }
    }
    edge.visited = true;

    if (prevEdge == null) return;

    if (node.deliveryNeeded || edge.deliveryDistance > 0) {
      prevEdge.deliveryDistance += edge.weight * 2 + edge.deliveryDistance;
      prevEdge.maxDistance = Math.max(prevEdge.maxDistance, edge.weight + edge.maxDistance);
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    N = scanner.nextInt();
    K = scanner.nextInt();

    nodes = new ArrayList<>();
    for (int i = 0; i < N; i++) nodes.add(new Node());

    deliveries = new ArrayList<>();
    for (int k = 0; k < K; k++) {
      Node node = nodes.get(scanner.nextInt() - 1);
      node.deliveryNeeded = true;
      deliveries.add(node);
    }

    for (int n = 0; n < N - 1; n++) {
      Node from = nodes.get(scanner.nextInt() - 1);
      Node to = nodes.get(scanner.nextInt() - 1);
      int weight = scanner.nextInt();
      from.edges.add(new Edge(from, to, weight));
      to.edges.add(new Edge(to, from, weight));
    }

    long result = Integer.MAX_VALUE;
    for (Node node : deliveries) {
      Edge edge = new Edge(null, node, 0);
      startFrom(null, edge);
      result = Math.min(result, edge.estimate());
    }
    System.out.println(result);
  }
}
