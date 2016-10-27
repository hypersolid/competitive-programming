class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt();
    int M = scanner.nextInt();
    int A = scanner.nextInt();

    System.out.println(((double) N * M) / ((double) A * A));
  }
}
