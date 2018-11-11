class Solution {
  ArrayList<Integer> sets = new ArrayList<>();
  int a;
  int N;
  int S = 0;
  int[] nums;

  void reverse(int[] input) {
    // handling null, empty and one element array
    if (input == null || input.length <= 1) {
      return;
    }

    for (int i = 0; i < input.length / 2; i++) {
      int temp = input[i]; // swap numbers
      input[i] = input[input.length - 1 - i];
      input[input.length - 1 - i] = temp;
    }
  }

  boolean crosscheck() {
    int M = sets.size();
    int[] combinataions = new int[M];
    for (int i = 0; i < M; i++) {
      combinataions[i] = sets.get(i);
    }

    for (int a = 0; a < M; a++) {
      for (int b = a + 1; b < M; b++) {
        if ((combinataions[a] & combinataions[b]) != 0) continue;
        for (int c = b + 1; c < M; c++) {
          if ((combinataions[a] & combinataions[c]) != 0) continue;
          if ((combinataions[b] & combinataions[c]) != 0) continue;
          for (int d = c + 1; d < M; d++) {
            if ((combinataions[a] & combinataions[d]) != 0) continue;
            if ((combinataions[b] & combinataions[d]) != 0) continue;
            if ((combinataions[c] & combinataions[d]) != 0) continue;
            return true;
          }
        }
      }
    }
    return false;
  }

  void knapsack(int set, int sum, int index) {
    if (index == N || sum > a) {
      return;
    }
    // move forward without current element
    if (sum == a) {
      sets.add(set);
      // System.out.println(Integer.toString(set, 2));
      return;
    } else {
      knapsack(set, sum, index + 1);
    }
    // move forward with current element
    int newSet = set | 1 << index;
    int newSum = sum + nums[index];
    if (newSum == a) {
      sets.add(newSet);
      // System.out.println(Integer.toString(newSet, 2));
    } else {
      knapsack(newSet, newSum, index + 1);
    }
  }

  public boolean makesquare(int[] nums) {
    Arrays.sort(nums);
    reverse(nums);

    N = nums.length;
    if (N < 4) {
      return false;
    }
    for (int num : nums) {
      S += num;
    }
    if (S % 4 != 0L) {
      return false;
    }
    this.nums = nums;
    this.a = S / 4;

    knapsack(0, 0, 0);

    return crosscheck();
  }
}
