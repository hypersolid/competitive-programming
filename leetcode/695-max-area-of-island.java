class Solution {
  int N;
  int M;
  int[][] grid;

  int dfs(int j, int i) {
    if (j < 0 || j >= N) {
      return 0;
    }
    if (i < 0 || i >= M) {
      return 0;
    }
    if (grid[j][i] == 0) {
      return 0;
    }
    grid[j][i] = 0;
    return 1 + dfs(j + 1, i) + dfs(j - 1, i) + dfs(j, i + 1) + dfs(j, i - 1);
  }

  public int maxAreaOfIsland(int[][] grid) {
    N = grid.length;
    if (N == 0) {
      return 0;
    }
    M = grid[0].length;
    this.grid = grid;

    int result = 0;
    for (int j = 0; j < N; j++) {
      for (int i = 0; i < M; i++) {
        if (grid[j][i] == 1) {
          result = Math.max(result, dfs(j, i));
        }
      }
    }
    return result;
  }
}
