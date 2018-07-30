import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
 static class Bar {
  int width, height;
  Bar(int width, int height) {
   this.width = width;
   this.height = height;
  }
 }

 static long unwindAndCalculateArea(ArrayDeque < Bar > deque, int currentHeight) {
  long area = 0;
  int width = 0;

  while (!deque.isEmpty() && deque.peekLast().height > currentHeight) {
   Bar popped = deque.removeLast();
   width += popped.width;
   long currentArea = (long) popped.height * (long) width;
   area = Math.max(area, currentArea);c
  }

  deque.addLast(new Bar(width + 1, currentHeight));

  return area;
 }

 // Complete the largestRectangle function below.
 static long largestRectangle(int[] h) {
  long result = 0;
  ArrayDeque < Bar > deque = new ArrayDeque < > ();
  for (int i = 0; i < h.length; i++) {
   int currentHeight = h[i];
   result = Math.max(result, unwindAndCalculateArea(deque, currentHeight));
  }
  result = Math.max(result, unwindAndCalculateArea(deque, 0));

  return result;
 }

 private static final Scanner scanner = new Scanner(System.in);

 public static void main(String[] args) throws IOException {
  BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

  int n = scanner.nextInt();
  scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

  int[] h = new int[n];

  String[] hItems = scanner.nextLine().split(" ");
  scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

  for (int i = 0; i < n; i++) {
   int hItem = Integer.parseInt(hItems[i]);
   h[i] = hItem;
  }

  long result = largestRectangle(h);

  bufferedWriter.write(String.valueOf(result));
  bufferedWriter.newLine();

  bufferedWriter.close();

  scanner.close();
 }
}
