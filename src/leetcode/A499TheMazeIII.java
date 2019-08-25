package leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

public class A499TheMazeIII {
  public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
    int M = maze.length, N = maze[0].length;
    String ret = "";
    Point[][] memo = new Point[M][N];
    memo[ball[0]][ball[1]] = new Point(0, "");

    Queue<int[]> queue = new PriorityQueue<>(
      (p1,p2) -> memo[p1[0]][p1[1]].dist != memo[p2[0]][p2[1]].dist
        ? memo[p1[0]][p1[1]].dist - memo[p2[0]][p2[1]].dist : memo[p1[0]][p1[1]].path.compareTo(memo[p2[0]][p2[1]].path));
    queue.offer(new int[]{ball[0], ball[1]});

    while (!queue.isEmpty()) {
      int[] cur = queue.poll();
      if (memo[cur[0]][cur[1]].visited) continue;
      memo[cur[0]][cur[1]].visited = true;

      if (cur[0] == hole[0] && cur[1] == hole[1]) return memo[cur[0]][cur[1]].path;

      for (int k = 0; k < 4; k++) {
        int[] next = getNext(maze, cur, hole, k);
        int d = getDist(cur, next) + memo[cur[0]][cur[1]].dist;
        String path = memo[cur[0]][cur[1]].path + moves[k];
        if (memo[next[0]][next[1]] == null) {
          memo[next[0]][next[1]] = new Point(d, path);
          queue.offer(next);
        }
        if (d < memo[next[0]][next[1]].dist || d == memo[next[0]][next[1]].dist && path.compareTo(memo[next[0]][next[1]].path) < 0) {
          memo[next[0]][next[1]].dist = d;
          memo[next[0]][next[1]].path = path;
          queue.offer(next);
        }
      }
    }
    return ret.isEmpty() ? "impossible" : ret;
  }

  private int getDist(int[] cur, int[] next) {
    return Math.abs(cur[0]-next[0]) + Math.abs(cur[1]-next[1]);
  }

  private int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
  private char[] moves = {'u', 'r', 'd', 'l'};

  private int[] getNext(int[][] maze, int[] cur, int[] hole, int k) {
    int[] dir = dirs[k];

    int i = cur[0], j = cur[1], M = maze.length, N = maze[0].length, d = 0;
    while (i+dir[0] >= 0 && i+dir[0] < M && j+dir[1] >= 0 && j+dir[1] < N && maze[i+dir[0]][j+dir[1]] != 1) {
      i += dir[0];
      j += dir[1];
      d++;
      if (i == hole[0] && j == hole[1]) {
        break;
      }
    }
    return new int[]{i,j};
  }

  public static void main(String[] args) {
    A499TheMazeIII in = new A499TheMazeIII();
    String path = in.findShortestWay(new int[][]{{0,0,0,0,0},{1,1,0,0,1},{0,0,0,0,0}, {0,1,0,0,1},{0,1,0,0,0}},
                      new int[]{4,3}, new int[]{0,1});

    System.out.println(path);
  }
}

class Point {
  int dist;
  String path;
  boolean visited;

  Point(int dist, String path) {
    this.dist = dist;
    this.path = path;
    this.visited = false;
  }
}
