import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
  static class CrosswordSolved extends RuntimeException {}

  static class StartingPoint {
    int j;
    int i;
    int length;
    boolean down;
    String word;

    public StartingPoint(int j, int i, int length, boolean down) {
      this.j = j;
      this.i = i;
      this.length = length;
      this.down = down;
    }

    public String toString() {
      return String.format("%d:%d / l%d / down=%b / %s", j, i, length, down, word);
    }
  }

  static final int N = 10;
  static final char EMPTY = '-';
  static final char BLOCKED = '+';
  static ArrayList<StartingPoint> points = new ArrayList<>();
  static List<String> words;
  static char[][] crossword = new char[N + 2][N + 2];

  static void addPoint(int j, int i, int count, boolean down) {
    if (count > 1) {
      if (down) {
        points.add(new StartingPoint(j - count, i, count, down));
      } else {
        points.add(new StartingPoint(j, i - count, count, down));
      }
    }
  }

  static void unwrap(String[] crossword) {
    int count = 0;
    for (int j = 0; j < N; j++) {
      for (int i = 0; i < N; i++) {
        if (crossword[j].charAt(i) == EMPTY) {
          count++;
        } else {
          addPoint(j, i, count, false);
          count = 0;
        }
      }
      addPoint(j, N, count, false);
      count = 0;
    }
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (crossword[j].charAt(i) == EMPTY) {
          count++;
        } else {
          addPoint(j, i, count, true);
          count = 0;
        }
      }
      addPoint(N, i, count, true);
      count = 0;
    }
  }

  static String[] wrap() {
    for (StartingPoint point : points) {
      fillPoint(point, point.word);
    }

    String[] result = new String[N];
    for (int j = 0; j < N; j++) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < N; i++) {
        sb.append(crossword[j + 1][i + 1]);
      }
      result[j] = sb.toString();
    }
    return result;
  }

  static void clearPoint(StartingPoint point, int steps) {
    for (int k = 0; k < steps; k++) {
      if (point.down) {
        crossword[point.j + k + 1][point.i + 1] = EMPTY;
      } else {
        crossword[point.j + 1][point.i + k + 1] = EMPTY;
      }
    }
  }

  static void clearCrossword() {
    for (int j = 0; j < N + 2; j++) {
      Arrays.fill(crossword[j], BLOCKED);
    }
    for (StartingPoint point : points) {
      clearPoint(point, point.length);
    }
  }

  static int fillPoint(StartingPoint point, String word) {
    int j;
    int i;
    for (int k = 0; k < word.length(); k++) {
      if (point.down) {
        j = point.j + k + 1;
        i = point.i + 1;
      } else {
        j = point.j + 1;
        i = point.i + k + 1;
      }
      char ch = word.charAt(k);
      if (crossword[j][i] != EMPTY && crossword[j][i] != ch) {
        return k;
      }
      crossword[j][i] = ch;
    }
    return word.length();
  }

  static void solve(int index) {
    if (index == points.size()) {
      throw new CrosswordSolved();
    }
    StartingPoint point = points.get(index);
    for (int i = 0; i < words.size(); i++) {
      String word = words.get(i);
      if (word.length() != point.length) {
        continue;
      }
      int steps = fillPoint(point, word);
      if (steps == point.length) {
        words.set(i, "");
        point.word = word;
        System.out.println(point);
        solve(index + 1);
        words.set(i, word);
        point.word = null;
      }
      clearPoint(point, steps);
    }
  }

  // Complete the crosswordPuzzle function below.
  static String[] crosswordPuzzle(String[] crosswordSource, String wordsString) {
    unwrap(crosswordSource);
    words = Arrays.asList(wordsString.split(";"));
    clearCrossword();
    // System.out.println(points);
    try {
      solve(0);
    } catch (CrosswordSolved e) {
      clearCrossword();
      return wrap();
    }

    return new String[0];
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] crossword = new String[10];

    for (int i = 0; i < 10; i++) {
      String crosswordItem = scanner.nextLine();
      crossword[i] = crosswordItem;
    }

    String words = scanner.nextLine();

    String[] result = crosswordPuzzle(crossword, words);

    for (int i = 0; i < result.length; i++) {
      bufferedWriter.write(result[i]);

      if (i != result.length - 1) {
        bufferedWriter.write("\n");
      }
    }

    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
