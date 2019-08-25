package google;

import java.util.Random;

public class MazeGeneration2 {
  private Random rd = new Random();

  public void generateMaze(int[][] board, int[] start, int[] end) {
    int M = board.length, N = board[0].length;
    dfs(start[0], start[1], board, new boolean[M][N], end);
  }

  private int[][] dirs = new int[][]{{0,-1},{-1,0},{0,1},{1,0}};

  private void dfs(int i, int j, int[][] board, boolean[][] visited, int[] end) {
    board[i][j] = 1;
    if (i == end[0] && j == end[1]) return;
    if (i == end[0]-1 && j == end[1]) {
      board[end[0]][end[1]] = 1;
      return;
    }

    int r = rd.nextInt(4);

    for (int d = 0; d < 4; d++) {
      int k = (d+r)%4;
      int ii = i+dirs[k][0];
      int jj = j+dirs[k][1];
      if (ii >= 0 && ii < board.length && jj >= 0 && jj < board[0].length && isValid(ii, jj, k, board, visited)) {
        dfs(ii, jj, board, visited, end);
      }
    }
  }


  private boolean isValid(int i, int j, int d, int[][] board, boolean[][] visited) {
    if (visited[i][j]) return false;
    for (int k = 0; k < 4; k++) {
      if (dirs[k][0] + dirs[d][0] == 0 && dirs[k][1] + dirs[d][1] == 0) continue;
      int ii = i+dirs[k][0];
      int jj = j+dirs[k][1];
      if (ii >= 0 && ii < board.length && jj >= 0 && jj < board[0].length && board[ii][jj] == 1) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    int[][] board = new int[7][7];
    MazeGeneration2 in = new MazeGeneration2();
    in.generateMaze(board, new int[]{0,0}, new int[]{6,6});
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }
}
