package leetcode.topological;

import java.util.ArrayList;
import java.util.List;

public class A210CourseScheduleII {
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    List<Integer> path = new ArrayList<>();
    boolean[][] graph = new boolean[numCourses][numCourses];
    for (int[] p : prerequisites) {
      graph[p[0]][p[1]] = true;
    }
    int[] stats = new int[numCourses];
    for (int i = 0; i < numCourses; i++) {
      if (!dfs(i, graph, stats, path)) return new int[0];
    }
    int[] ret = new int[numCourses];
    for (int i = 0; i < ret.length; i++) {
      ret[i] = path.get(i);
    }
    return ret;
  }

  private boolean dfs(int pos, boolean[][] graph, int[] stats, List<Integer> path) {
    if (stats[pos] == 2) return true;
    if (stats[pos] == 1) return false;
    stats[pos] = 1;

    for (int i = 0; i < graph.length; i++) {
      if (graph[pos][i] && !dfs(i, graph, stats, path)) return false;
    }

    stats[pos] = 2;
    path.add(pos);
    return true;
  }

  public static void main(String[] args) {
    A210CourseScheduleII in = new A210CourseScheduleII();
    int[] ret = in.findOrder(4, new int[][]{{1,0},{2,1},{3,2}});
    for (int r : ret) {
      System.out.print(r + " ");
    }
  }
}
