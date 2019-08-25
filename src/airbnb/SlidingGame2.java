package airbnb;

import java.util.*;

class SlidingGame2 {
  class Point {
    char[] state;
    int pos;
    Point(char[] state, int pos) {
      this.state = Arrays.copyOf(state, state.length);
      this.pos = pos;
    }

    Point(int[][] board) {
      int M = board.length, N = board[0].length;
      this.state = new char[M*N];
      for (int i = 0; i < state.length; i++) {
        state[i] = (char)('0' + board[i/N][i%N]);
        if (state[i] == '0') {
          pos = i;
        }
      }
    }

    String getKey() {
      return new String(state);
    }

    void swap(int i, int j) {
      char tmp = state[i];
      state[i] = state[j];
      state[j] = tmp;
      pos = j;
    }
  }

  private int[][] dirs = new int[][]{{0,-1},{-1,0},{0,1},{1,0}};
  
  public int slidingPuzzle(int[][] board) {
    int M = board.length, N = board[0].length;
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < M*N; i++) {
      sb.append(i);
    }
    sb.append(0);
    String target = sb.toString();

    Queue<Point> q = new LinkedList<>();
    q.offer(new Point(board));
    int count = 0;
    Set<String> visited = new HashSet<>();
    while (!q.isEmpty()) {
      int size = q.size();
      while (size-- > 0) {
        Point cur = q.poll();
        String key = cur.getKey();
        if (key.equals(target)) return count;
        visited.add(key);
        for (int k = 0; k < 4; k++) {
          int ii = cur.pos / N + dirs[k][0];
          int jj = cur.pos % N + dirs[k][1];
          if (ii >= 0 && ii < M && jj >= 0 && jj < N) {
            Point next = new Point(cur.state, cur.pos);
            next.swap(cur.pos, ii*N+jj);
            if (visited.contains(next.getKey())) continue;
            q.offer(next);
          }
        }
      }
      count++;
    }
    return -1;
  }

  public static void main(String[] args) {
    int[][] board = new int[][]{
      {1,5,2},
      {4,8,3},
      {7,0,6}};

    SlidingGame2 in = new SlidingGame2();
    int ret = in.slidingPuzzle(board);
    System.out.println(ret);
  }
}
