import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long G = scanner.nextInt();
        for (int g = 0; g < G; g++) {
            int N = scanner.nextInt();
            ArrayList<Long> buffer = new ArrayList<>();

            long maxSparseSum = -1;
            long maxContiniousSum = -1;
            long maxContiniousSumEndingHere = -1;

            for (int n = 0; n < N; n++) {
                long number = scanner.nextInt();
                buffer.add(number);
                if (n == 0) {
                    maxSparseSum = number;
                    maxContiniousSum = number;
                    maxContiniousSumEndingHere = number;
                } else {
                    maxSparseSum = Math.max(number, Math.max(maxSparseSum, maxSparseSum + number));
                }
            }

            for (int i = 1; i < N; i++) {
                long number = buffer.get(i);
                maxContiniousSumEndingHere = Math.max(maxContiniousSumEndingHere + number, number);
                maxContiniousSum = Math.max(maxContiniousSum, maxContiniousSumEndingHere);
            }

            System.out.println(maxContiniousSum + " " + maxSparseSum);
        }
    }
}
