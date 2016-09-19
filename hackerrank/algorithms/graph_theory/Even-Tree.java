import java.io.*;
import java.util.*;

public class Solution {
  public static class Node {
    public ArrayList<Node> edges;

    public Node() {
      edges = new ArrayList<>();
    }
  }

  public static class Edge {
    public Node node1, node2;

    public Edge(Node node1, Node node2) {
      this.node1 = node1;
      this.node2 = node2;
    }
  }

  public static int count(Node previous, Node current) {
    int nodesNumber = 1;
    for (Node next : current.edges) {
      if (next == previous) continue;
      nodesNumber += count(current, next);
    }
    return nodesNumber;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt(), M = scanner.nextInt();
    Node[] nodes = new Node[N];
    Edge[] edges = new Edge[M];

    for (int n = 0; n < N; n++) nodes[n] = new Node();

    for (int m = 0; m < M; m++) {
      Node node1 = nodes[scanner.nextInt() - 1];
      Node node2 = nodes[scanner.nextInt() - 1];
      edges[m] = new Edge(node1, node2);
      node1.edges.add(node2);
      node2.edges.add(node1);
    }

    int counter = 0;
    for (int i = 0; i < M; i++) {
      boolean evenLeft = count(edges[i].node2, edges[i].node1) % 2 == 0;
      boolean evenRight = count(edges[i].node1, edges[i].node2) % 2 == 0;
      if (evenLeft && evenRight) {
        edges[i].node1.edges.remove(edges[i].node2);
        edges[i].node2.edges.remove(edges[i].node1);
        counter++;
      }
    }
    System.out.println(counter);
  }
}
