import java.util.Scanner;

class Wrapper {
    public static void main(String[] args) {
        (new Solution()).solve();
    }

    private static class Solution {
        Scanner scanner = new Scanner(System.in);

        public void solve() {
            String dna = scanner.next();
            long maxSequence = 1;
            long currentSequence = 1;
            for (int i = 1; i < dna.length(); i++) {
                if (dna.charAt(i - 1) == dna.charAt(i)) {
                    currentSequence++;
                    maxSequence = Math.max(maxSequence, currentSequence);
                } else {
                    currentSequence = 1;
                }
            }

            System.out.println(maxSequence);
        }
    }
}