package airbnb;


import java.util.ArrayList;
import java.util.List;

class Node {
  int val;
}

public class MST {
  public int prim(int N, int[][] graph) {
    int[] dist = new int[N];
    boolean[] visited = new boolean[N];
    int cur = 0;
    visited[cur] = true;
    for (int i = 1; i < N; i++) {
      dist[i] = graph[cur][i];
    }
    int sum = 0;
    for (int i = 1; i < N; i++) {
      int min = Integer.MAX_VALUE, index = -1;
      for (int j = 0; j < N; j++) {
        if (!visited[j] && dist[j] < min) {
            min = dist[j];
            index = j;
        }
      }
      visited[index] = true;
      sum += min;
      for (int j = 0; j < N; j++) {
        if (!visited[j] && graph[index][j] < dist[j]) {
          dist[j] = graph[index][j];
        }
      }
    }
    return sum;
  }

  public int prim2(int N, int[][] graph) {
    int[] dist = new int[N];
    int[] edge = new int[N];
    boolean[] visited = new boolean[N];
    int[][] ret = new int[N][];
    int k = 0;
    int cur = 0;
    visited[cur] = true;
    for (int i = 1; i < N; i++) {
      dist[i] = graph[cur][i];
      edge[i] = cur;
    }
    int sum = 0;
    for (int i = 1; i < N; i++) {
      int min = Integer.MAX_VALUE;
      int index = -1;
      int nb = -1;
      for (int j = 0; j < N; j++) {
        if (!visited[j] && dist[j] < min) {
          min = dist[j];
          index = j;
          nb = edge[j];
        }
      }

      ret[k++] = new int[]{nb, index};

      visited[index] = true;
      sum += min;

      for (int j = 0; j < N; j++) {
        if (!visited[j] && graph[index][j] < dist[j]) {
          dist[j] = graph[index][j];
          edge[j] = index;
        }
      }
    }
    return sum;
  }
}
