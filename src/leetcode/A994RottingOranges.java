package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class A994RottingOranges {
  private int[] di = {0, -1, 0, 1};
  private int[] dj = {-1, 0, 1, 0};

  // [[2,1,1],[1,1,0],[0,1,1]]
  public int orangesRotting(int[][] grid) {
    int M = grid.length, N = grid[0].length;
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        if (grid[i][j] == 2) {
          queue.offer(i*N+j);
        }
      }
    }
    int dist = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      while(size-- > 0) {
        int code = queue.poll();
        for (int k = 0; k < 4; k++) {
          int ii = code/N + di[k];
          int jj = code%N + dj[k];
          if (ii >= 0 && ii < M && jj >= 0 && jj < N && grid[ii][jj] == 1) {
            grid[code/N][code%N] = 2;
            queue.offer(ii*N+jj);
          }
        }
      }
      if (!queue.isEmpty()) dist++;
    }
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        if (grid[i][j] == 1) return -1;
      }
    }
    return dist;
  }
  public static void main(String[] args) {
    A994RottingOranges in  = new A994RottingOranges();
    in.orangesRotting(new int[][]{
      {2,1,1},
      {1,1,0},
      {0,1,1}});
  }
}
