package google;

import java.util.Arrays;

public class A803BricksFallingWhenHit {
  public static void main(String[] args) {
    A803BricksFallingWhenHit in = new A803BricksFallingWhenHit();
    int[] ret = in.hitBricks(new int[][]{{1},{1},{1},{1},{1}}, new int[][]{{3,0},{4,0},{1,0},{2,0},{0,0}});
    for (int r : ret) {
      System.out.println(r);
    }
  }

  public int[] hitBricks(int[][] grid, int[][] hits) {
    int M = grid.length, N = grid[0].length;
    int[] beforeHits = new int[hits.length];

    for (int i = 0; i < hits.length; i++) {
      int[] hit = hits[i];
      beforeHits[i] = grid[hit[0]][hit[1]];
      grid[hit[0]][hit[1]] = 0;
    }

    DSU dsu = new DSU(M*N+1);

    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        if (grid[i][j] == 1) {
          if (i == 0) dsu.union(i*N+j, M*N);
          if (j-1 >= 0 && grid[i][j-1] == 1) dsu.union(i*N+j, i*N+j-1);
          if (i-1 >= 0 && grid[i-1][j] == 1) dsu.union(i*N+j, (i-1)*N+j);
        }
      }
    }

    int[] ret = new int[hits.length];
    for (int t = hits.length-1; t >= 0; t--) {
      if (beforeHits[t] == 0) continue;
      int i = hits[t][0], j = hits[t][1];
      int preTop = dsu.top();
      if (i == 0) dsu.union(i*N+j, M*N);
      for (int k = 0; k < 4; k++) {
        int ii = i + dirs[k][0];
        int jj = j + dirs[k][1];
        if (ii >= 0 && ii < M && jj >= 0 && jj < N && grid[ii][jj] == 1) {
          dsu.union(i * N + j, ii * N + jj);
        }
      }
      grid[i][j] = beforeHits[t];
      ret[t] = Math.max(0, dsu.top() - preTop - 1);
    }
    return ret;
  }

  private int[][] dirs = new int[][]{{0,-1},{-1,0},{0,1},{1,0}};
}

class DSU {
  int[] parent;
  int[] size;

  DSU(int N) {
    parent = new int[N];
    for (int i = 0; i < N; i++) {
      parent[i] = i;
    }
    size = new int[N];
    Arrays.fill(size, 1);
  }

  private int root(int i) {
    if (parent[i] == i) return i;
    parent[i] = root(parent[i]);
    return parent[i];
  }

  public void union(int i, int j) {
    int p = root(i);
    int q = root(j);
    if (p != q) {
      parent[p] = q;
      size[q] += size[p];
    }
  }

  public int size(int i) {
    return size[root(i)];
  }

  public int top() {
    return size(parent.length - 1) - 1;
  }
}
