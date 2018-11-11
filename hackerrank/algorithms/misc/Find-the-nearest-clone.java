import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.*;

public class Solution {
  static class Node {
    ArrayList<Node> edges = new ArrayList<>();
    long color;
    int id;

    boolean visited = false;
    Node source = null;
    int distance = 0;

    public Node(int id, long color) {
      this.id = id;
      this.color = color;
    }
  }

  static Node[] buildGraph(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids) {
    int graphEdges = graphFrom.length;
    Node[] nodes = new Node[graphNodes];
    // create nodes
    for (int i = 0; i < graphNodes; i++) {
      nodes[i] = new Node(i + 1, ids[i]);
    }
    // link nodes
    for (int i = 0; i < graphEdges; i++) {
      Node from = nodes[graphFrom[i] - 1];
      Node to = nodes[graphTo[i] - 1];
      from.edges.add(to);
      to.edges.add(from);
    }
    return nodes;
  }

  static int doBFS(Node[] nodes, long color) {
    // init BFS
    LinkedList<Node> queue = new LinkedList<>();

    for (Node node : nodes) {
      if (node.color != color) {
        continue;
      }
      node.visited = true;
      node.source = node;
      queue.add(node);
    }

    // optimization for one node
    if (queue.size() == 1) {
      return -1;
    }

    // do the search
    while (!queue.isEmpty()) {
      Node current = queue.remove();
      for (Node neighbour : current.edges) {
        if (neighbour.source != null && neighbour.source != current.source) {
          return neighbour.distance + current.distance + 1;
        }
      }

      // mark as visited
      for (Node neighbour : current.edges) {
        if (neighbour.visited) {
          continue;
        }
        neighbour.visited = true;
        neighbour.source = current.source;
        neighbour.distance = current.distance + 1;
        queue.add(neighbour);
      }
    }
    // found nothing
    return -1;
  }
  // Complete the findShortest function below.

  /*
   * For the unweighted graph, <name>:
   *
   * 1. The number of nodes is <name>Nodes.
   * 2. The number of edges is <name>Edges.
   * 3. An edge exists between <name>From[i] to <name>To[i].
   *
   */
  static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
    Node[] nodes = buildGraph(graphNodes, graphFrom, graphTo, ids);

    return doBFS(nodes, val);
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] graphNodesEdges = scanner.nextLine().split(" ");
    int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
    int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());

    int[] graphFrom = new int[graphEdges];
    int[] graphTo = new int[graphEdges];

    for (int i = 0; i < graphEdges; i++) {
      String[] graphFromTo = scanner.nextLine().split(" ");
      graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
      graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
    }

    long[] ids = new long[graphNodes];

    String[] idsItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < graphNodes; i++) {
      long idsItem = Long.parseLong(idsItems[i]);
      ids[i] = idsItem;
    }

    int val = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    int ans = findShortest(graphNodes, graphFrom, graphTo, ids, val);

    bufferedWriter.write(String.valueOf(ans));
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
