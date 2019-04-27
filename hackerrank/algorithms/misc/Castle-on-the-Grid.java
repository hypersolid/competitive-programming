import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    static enum Cell {
      FREE, MARKED, BLOCKED
    }
    static final char BLOCKED = 'X';
    static int N;
    static class Move {
      int x, y;
      public Move(int x, int y) {
        this.x = x;
        this.y = y;
      }
    }
    static final int[][] MOVES = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static LinkedList<Move> queue;
    static Cell[][] grid;

    static boolean isBlocked(int x, int y) {
      return grid[y+1][x+1] == Cell.BLOCKED;
    }

    static void addElementToQueue(int x, int y) {
      if (grid[y+1][x+1] == Cell.MARKED) {
        return;
      }
      queue.add(new Move(x, y));
      grid[y+1][x+1] = Cell.MARKED;
    }

    static void addSpacerToQueue() {
      for (int j = 0; j < N; j++) {
        for (int i = 0; i < N; i++) {
          if (grid[j+1][i+1] == Cell.MARKED) {
            grid[j+1][i+1] = Cell.BLOCKED;
          }
        }
      }
      queue.add(null);
    }

    static void initGrid(String[] stringGrid) {
      N = stringGrid.length;
      grid = new Cell[N+2][N+2];

      for (int j = 0; j < N+2; j++) {
        grid[j][0] = Cell.BLOCKED;
        grid[j][N+1] = Cell.BLOCKED;
        grid[0][j] = Cell.BLOCKED;
        grid[N+1][j] = Cell.BLOCKED;
      }

      for (int j = 0; j < N; j++) {
        for (int i = 0; i < N; i++) {
          grid[j+1][i+1] = stringGrid[i].charAt(j) == BLOCKED ? Cell.BLOCKED : Cell.FREE;
        }
      }
    }

    // Complete the minimumMoves function below.
    static int minimumMoves(String[] stringGrid, int startX, int startY, int goalX, int goalY)     {
      queue = new LinkedList<Move>();
      initGrid(stringGrid);
      int distance = 0;
      addElementToQueue(startX, startY);
      addSpacerToQueue();

      while (!queue.isEmpty()) {
        Move element = queue.removeFirst();
        if (element == null) {
          if (!queue.isEmpty()) {
            distance++;
            addSpacerToQueue();
          }
          continue;
        }
        if (element.x == goalX && element.y == goalY) {
          return distance;
        }
        for (int[] delta: MOVES) {
          int x = element.x + delta[0];
          int y = element.y + delta[1];
          while (!isBlocked(x,y)) {
            addElementToQueue(x,y);
            x += delta[0];
            y += delta[1];
          }
        }
      }
      return -1;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        String[] startXStartY = scanner.nextLine().split(" ");

        int startX = Integer.parseInt(startXStartY[0]);
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    static enum Cell {
      FREE, MARKED, BLOCKED
    }
    static final char BLOCKED = 'X';
    static int N;
    static class Move {
      int x, y;
      public Move(int x, int y) {
        this.x = x;
        this.y = y;
      }
    }
    static final int[][] MOVES = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static LinkedList<Move> queue;
    static Cell[][] grid;

    static boolean isBlocked(int x, int y) {
      return grid[y+1][x+1] == Cell.BLOCKED;
    }

    static void addElementToQueue(int x, int y) {
      if (grid[y+1][x+1] == Cell.MARKED) {
        return;
      }
      queue.add(new Move(x, y));
      grid[y+1][x+1] = Cell.MARKED;
    }

    static void addSpacerToQueue() {
      for (int j = 0; j < N; j++) {
        for (int i = 0; i < N; i++) {
          if (grid[j+1][i+1] == Cell.MARKED) {
            grid[j+1][i+1] = Cell.BLOCKED;
          }
        }
      }
      queue.add(null);
    }

    static void initGrid(String[] stringGrid) {
      N = stringGrid.length;
      grid = new Cell[N+2][N+2];

      for (int j = 0; j < N+2; j++) {
        grid[j][0] = Cell.BLOCKED;
        grid[j][N+1] = Cell.BLOCKED;
        grid[0][j] = Cell.BLOCKED;
        grid[N+1][j] = Cell.BLOCKED;
      }

      for (int j = 0; j < N; j++) {
        for (int i = 0; i < N; i++) {
          grid[j+1][i+1] = stringGrid[i].charAt(j) == BLOCKED ? Cell.BLOCKED : Cell.FREE;
        }
      }
    }

    // Complete the minimumMoves function below.
    static int minimumMoves(String[] stringGrid, int startX, int startY, int goalX, int goalY)     {
      queue = new LinkedList<Move>();
      initGrid(stringGrid);
      int distance = 0;
      addElementToQueue(startX, startY);
      addSpacerToQueue();

      while (!queue.isEmpty()) {
        Move element = queue.removeFirst();
        if (element == null) {
          if (!queue.isEmpty()) {
            distance++;
            addSpacerToQueue();
          }
          continue;
        }
        if (element.x == goalX && element.y == goalY) {
          return distance;
        }
        for (int[] delta: MOVES) {
          int x = element.x + delta[0];
          int y = element.y + delta[1];
          while (!isBlocked(x,y)) {
            addElementToQueue(x,y);
            x += delta[0];
            y += delta[1];
          }
        }
      }
      return -1;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        String[] startXStartY = scanner.nextLine().split(" ");

        int startX = Integer.parseInt(startXStartY[0]);

        int startY = Integer.parseInt(startXStartY[1]);

        int goalX = Integer.parseInt(startXStartY[2]);

        int goalY = Integer.parseInt(startXStartY[3]);

        int result = minimumMoves(grid, startX, startY, goalX, goalY);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

        int startY = Integer.parseInt(startXStartY[1]);

        int goalX = Integer.parseInt(startXStartY[2]);

        int goalY = Integer.parseInt(startXStartY[3]);

        int result = minimumMoves(grid, startX, startY, goalX, goalY);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

