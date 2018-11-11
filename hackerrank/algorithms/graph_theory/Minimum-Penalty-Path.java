import java.io.*;
import java.util.*;

public class Solution {
  public static class Node {
    public boolean visited = false;
    public int penalty;
    ArrayList<Edge> edges = new ArrayList<>();
  }

  public static class Edge {
    public Node source, target;
    public int weight;

    public Edge(Node source, Node target, int weight) {
      this.source = source;
      this.target = target;
      this.weight = weight;
    }
  }

  static int N, M, mask;
  static ArrayList<Node> nodes;
  static Node start, finish;

  static boolean bfs(int number) {
    for (Node node : nodes) {
      node.visited = false;
      node.penalty = 0;
    }
    LinkedList<Node> queue = new LinkedList<>();
    start.visited = true;
    queue.addFirst(start);

    while (!queue.isEmpty()) {
      Node node = queue.pollFirst();
      for (Edge edge : node.edges) {
        if ((edge.weight & ~number) != 0) continue;
        if (edge.target.visited) continue;
        edge.target.visited = true;

        edge.target.penalty = edge.source.penalty | edge.weight;
        if (edge.target == finish) return true;

        queue.addLast(edge.target);
      }
    }

    return false;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    N = scanner.nextInt();
    M = scanner.nextInt();

    nodes = new ArrayList<>();
    for (int i = 0; i < N; i++) nodes.add(new Node());

    Edge edge;
    for (int i = 0; i < M; i++) {
      Node source = nodes.get(scanner.nextInt() - 1);
      Node destination = nodes.get(scanner.nextInt() - 1);
      int weight = scanner.nextInt();
      edge = new Edge(source, destination, weight);
      source.edges.add(edge);
      edge = new Edge(destination, source, weight);
      destination.edges.add(edge);
      mask |= weight;
    }
    start = nodes.get(scanner.nextInt() - 1);
    finish = nodes.get(scanner.nextInt() - 1);

    for (int i = 1; i <= mask; i++) {
      if (bfs(i)) {
        System.out.println(finish.penalty);
        return;
      }
    }
    System.out.println(-1);
  }
}
