import java.io.*;
import java.util.*;

public class Solution {
  static class Edge {
    public Node node;
    public Edge reverse;
    public int capacity;

    public Edge(Node node, int capacity) {
      this.node = node;
      this.capacity = capacity;
    }
  }

  static class Node {
    public Node previousNode;
    public Edge previousEdge;
    public boolean visited;
    public ArrayList<Edge> edges = new ArrayList<>();
  }

  static int N, T, M;
  static Node source, sink;
  static ArrayList<Node> heads;
  static ArrayList<Node> legs;

  static boolean bfs() {
    LinkedList<Node> queue = new LinkedList<>();
    for (Node node : heads) node.visited = false;
    for (Node node : legs) node.visited = false;
    sink.visited = false;
    source.visited = true;

    queue.add(source);

    while (!queue.isEmpty()) {
      Node current = queue.pollFirst();
      for (Edge edge : current.edges) {
        if (edge.node.visited) continue;
        if (edge.capacity == 0) continue;

        edge.node.visited = true;
        edge.node.previousEdge = edge;
        edge.node.previousNode = current;
        if (edge.node == sink) return true;

        queue.addLast(edge.node);
      }
    }
    return false;
  }

  static int fordFulkerson() {
    int maxFlow = 0;
    Node current;
    while (bfs()) {
      int pathFlow = Integer.MAX_VALUE;

      current = sink;
      while (current != source) {
        pathFlow = Math.min(pathFlow, current.previousEdge.capacity);
        current = current.previousNode;
      }

      current = sink;
      while (current != source) {
        current.previousEdge.capacity -= pathFlow;
        if (current.previousEdge.reverse != null) current.previousEdge.reverse.capacity += pathFlow;
        current = current.previousNode;
      }

      maxFlow += pathFlow;
    }
    return maxFlow;
  }

  static void doubleLink(Node from, Node to, int capacity) {
    Edge direct = new Edge(to, capacity);
    Edge reverse = new Edge(from, 0);
    direct.reverse = reverse;
    reverse.reverse = direct;
    from.edges.add(direct);
    to.edges.add(reverse);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int C = scanner.nextInt();

    for (int c = 0; c < C; c++) {
      N = scanner.nextInt();
      T = scanner.nextInt();
      M = scanner.nextInt();

      source = new Node();
      sink = new Node();

      heads = new ArrayList<>();
      legs = new ArrayList<>();

      for (int n = 0; n < N; n++) {
        Node head = new Node();
        doubleLink(source, head, T);
        heads.add(head);

        Node leg = new Node();
        doubleLink(leg, sink, 1);
        legs.add(leg);
      }

      for (int m = 0; m < M; m++) {
        int n1 = scanner.nextInt() - 1;
        int n2 = scanner.nextInt() - 1;
        doubleLink(heads.get(n1), legs.get(n2), 1);
        doubleLink(heads.get(n2), legs.get(n1), 1);
      }

      System.out.println(fordFulkerson());
    }
  }
}
