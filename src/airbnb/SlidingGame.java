package airbnb;

import java.util.*;

class SlidingGame {
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

  private int[][] dirs = new int[][]{{1,3},{0,2,4},{1,5},{0,4},{1,3,5},{2,4}};

  public int slidingPuzzle(int[][] board) {
    Queue<Point> q = new LinkedList<>();
    q.offer(new Point(board));
    int count = 0;
    Set<String> visited = new HashSet<>();
    while (!q.isEmpty()) {
      int size = q.size();
      while (size-- > 0) {
        Point cur = q.poll();
        String key = cur.getKey();
        if (key.equals("123450")) return count;
        visited.add(key);
        for (int j : dirs[cur.pos]) {
          Point next = new Point(cur.state, cur.pos);
          next.swap(cur.pos, j);
          if (visited.contains(next.getKey())) continue;
          q.offer(next);
        }
      }
      count++;
    }
    return -1;
  }
}
