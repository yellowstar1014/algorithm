package google;

import java.util.Random;

public class MazeGeneration3 {
  private Random rd = new Random();

  public boolean[][][] generateMaze(int M, int N, int[] start, int[] end) {
    boolean[][][] walls = new boolean[M][N][2];
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        for (int k = 0; k < 2; k++) {
          walls[i][j][k] = true;
        }
      }
    }
    boolean[][] visited = new boolean[M][N];
    dfs(start[0], start[1], end, walls, visited);
    return walls;
  }

  private void dfs(int i, int j, int[] end, boolean[][][] walls, boolean[][] visited) {
    visited[i][j] = true;
    System.out.println(i + " " + j);
    if (i == end[0] && j == end[1]) return;
    int r = rd.nextInt(4);
    for (int d = 0; d < 4; d++) {
      int k = (d+r) % 4;
      int ii = i + dirs[k][0];
      int jj = j + dirs[k][1];
      if (isValid(ii, jj, walls, visited)) {
        switch(k) {
          case 0:
            walls[ii][jj][0] = false; break;
          case 1:
            walls[ii][jj][1] = false; break;
          case 2:
            walls[i][j][0] = false; break;
          case 3:
            walls[i][j][1] = false;break;
        }
        dfs(ii, jj, end, walls, visited);
      }
    }
  }

  private boolean isValid(int i, int j, boolean[][][] walls, boolean[][] visited) {
    return i >= 0 && i < walls.length && j >= 0 && j < walls[0].length && !visited[i][j];
  }

  private int[][] dirs = new int[][]{{0,-1},{-1,0},{0,1},{1,0}};


  public static void main(String[] args) {
    MazeGeneration3 in = new MazeGeneration3();
    boolean[][][] walls = in.generateMaze(6, 6, new int[]{0,0}, new int[]{5,5});
    for (int i = 0; i < walls.length; i++) {
      for (int k = 0; k < 2; k++) {
        for (int j = 0; j < walls[i].length; j++) {
          if (k==0 && j == 0) System.out.print("|");
          if (k==1 && j == 0) System.out.print(" ");
          if (walls[i][j][k]) {
            String c = k == 0 ? " |" : "--";
            System.out.print(c);
          } else {
            System.out.print("  ");
          }
        }
        System.out.println();
      }
    }
  }
}
