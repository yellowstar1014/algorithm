package google;

import java.util.Random;

public class MazeGeneration {
  private Random rd = new Random();

  public void generateMaze(int[][] board, int[] start, int[] end) {
    int M = board.length, N = board[0].length;
    board[start[0]][start[1]] = 1;
    dfs(start[0], start[1], board, new boolean[M][N], end);
  }

  private int[][] dirs = new int[][]{{0,-1},{-1,0},{0,1},{1,0}};

  private void dfs(int i, int j, int[][] board, boolean[][] visited, int[] end) {

    if (i == end[0] && j == end[1]) return;

    int r = rd.nextInt(4);
    for (int d = 0; d < 4; d++) {
      int k = (d+r)%4;
      int ii = i+dirs[k][0]*2;
      int jj = j+dirs[k][1]*2;
      if (ii >= 0 && ii < board.length && jj >= 0 && jj < board[0].length && board[ii][jj] == 0) {
        board[ii][jj] = 1;
        board[i+dirs[k][0]][j+dirs[k][1]] = 1;
        dfs(ii, jj, board, visited, end);
      }
    }
  }

  private int moveX(int i, int k, int time) {
    return i + dirs[k][0] * time;
  }

  private int moveY(int j, int k, int time) {
    return j + dirs[k][1] * time;
  }

  public static void main(String[] args) {
    int[][] board = new int[20][20];
    MazeGeneration in = new MazeGeneration();
    in.generateMaze(board, new int[]{0,0}, new int[]{18,18});
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }
}
