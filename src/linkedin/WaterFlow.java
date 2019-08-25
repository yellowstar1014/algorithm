package linkedin;

import java.util.Arrays;

public class WaterFlow {
  // f[i][j] = min(terrain[i][j],max(f[i][j-1],f[i-1][j-1], f[i+1][j-1])
  // f[i][j] =

  public int waterFlow(int[][] terrain) {
    int M = terrain.length, N = terrain[0].length;
    int[] f = new int[M];
    int ret = 0;
    for (int j = 0; j < N; j++) {
      for (int i = 0; i < M; i++) {
        int cur = terrain[i][j];
        if (j > 0) {
          int pre = f[i];
          if (i > 0) pre = Math.max(pre, f[i-1]);
          if (i < M-1) pre = Math.max(pre, f[i+1]);
          cur = Math.min(cur, pre);
        }
        f[i] = cur;
        if (j == N-1) ret = Math.max(ret, f[i]);
      }
    }
    return ret;
  }

  public int waterFlow1(int[][] terrain) {
    int M = terrain.length, N = terrain[0].length;
    int ret = 0;
    for (int j = 0; j < N; j++) {
      for (int i = 0; i < M; i++) {
        if (j > 0) {
          terrain[i][j] = Math.min(terrain[i][j], terrain[i][j-1]);
          if (i > 0) terrain[i][j] = Math.max(terrain[i][j], terrain[i-1][j]);
          if (i < M-1) terrain[i][j] = Math.max(terrain[i][j], terrain[i+1][j]);
        }
        if (j == N-1) ret = Math.max(ret, terrain[i][j]);
      }
    }
    return ret;
  }

  public int waterFlow2(int[][] terrain) {
    int M = terrain.length, N = terrain[0].length;
    int max = 0;
    int[][] memo = new int[M][N];
    for (int i = 0; i < M; i++) {
      Arrays.fill(memo[i], -1);
    }

    for (int i = 0; i < M; i++) {
      max = Math.max(max, helper(i, 0, terrain, memo));
    }

    return max;
  }

  private int helper(int i, int j, int[][] terrain, int[][]memo) {
    if (memo[i][j] != -1) return memo[i][j];
    if (j == terrain[0].length - 1) {
      memo[i][j] = terrain[i][j];
    } else {
      memo[i][j] = terrain[i][j];
      if (j-1 >= 0) {
        memo[i][j] = Math.min(memo[i][j], helper(i, j-1, terrain, memo));
      }
      if (j > 0) {
        if (i-1 >= 0) {
          memo[i][j] = Math.min(memo[i][j], helper(i-1, j, terrain, memo));
        }
        if (i+1 < terrain.length) {
          memo[i][j] = Math.min(memo[i][j], helper(i+1, j, terrain, memo));
        }
      }
    }
    return memo[i][j];
  }
}
