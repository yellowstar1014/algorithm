package leetcode;

import java.util.ArrayList;
import java.util.List;

public class A886PossibleBipartition {
  /**
   * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.

   Each person may dislike some other people, and they should not go into the same group.

   Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.

   Return true if and only if it is possible to split everyone into two groups in this way.
   */
  // a -> b -> c -> d -> a
  //
  //
  // a  b   c   d
  // 1  -1  -1  1

  // a -> b -> c -> a
  // a
  // b
  public boolean possibleBipartition(int N, int[][] dislikes) {
    List<Integer>[] graph = new List[N+1];
    for (int i = 1; i <= N; i++) {
      graph[i] = new ArrayList<>();
    }
    for (int[] dislike:dislikes) {
      graph[dislike[0]].add(dislike[1]);
      graph[dislike[1]].add(dislike[0]);
    }

    int[] color = new int[N+1];
    for (int i = 1; i <= N; i++) {
      if (color[i] == 0) {
        color[i] = 1;
        if (!dfs(i, graph, color)) return false;
      }
    }
    return true;
  }

  private boolean dfs(int cur, List<Integer>[] graph, int[] color) {
    for (int next : graph[cur]) {
      if (color[next] != 0) {
        if (color[next] == color[cur]) return false;
        continue;
      }
      color[next] = -color[cur];
      dfs(next, graph, color);
    }
    return true;
  }
}
