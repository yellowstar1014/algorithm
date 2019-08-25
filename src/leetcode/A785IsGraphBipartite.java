package leetcode;

/**
 * [[2,3,5,6,7,8,9],[2,3,4,5,6,7,8,9],[0,1,3,4,5,6,7,8,9],[0,1,2,4,5,6,7,8,9],[1,2,3,6,9],[0,1,2,3,7,8,9],[0,1,2,3,4,7,8,9],[0,1,2,3,5,6,8,9],[0,1,2,3,5,6,7],[0,1,2,3,4,5,6,7]]
 */
public class A785IsGraphBipartite {
  public boolean isBipartite(int[][] graph) {
    int N = graph.length;
    int[] color = new int[N];
    for (int i = 0; i < N; i++) {
      if (color[i] == 0) {
        color[i] = 1;
        if (!dfs(i, graph, color)) return false;
      }
    }
    return true;
  }

  private boolean dfs(int cur, int[][] graph, int[] color) {
    for (int next : graph[cur]) {
      if (color[next] != 0) {
        if (color[next] == color[cur]) return false;
      } else {
        color[next] = -color[cur];
        if (!dfs(next, graph, color)) return false;
      }
    }
    return true;
  }
}

// [3,9,8,4,0,1,7,null,null,null,2,5]
