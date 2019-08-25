package leetcode.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class A834SumofDistancesInTree {
  int[] ret;
  Set<Integer>[] graph;
  int[] count;
  int N;

  public int[] sumOfDistancesInTree(int N, int[][] edges) {
    this.ret = new int[N];
    this.count = new int[N];
    this.graph = buildGraph(edges, N);
    this.N = N;

    postOrder(0, -1);
    preOrder(0,-1);
    return ret;
  }

  private void postOrder(int cur, int parent) {
    for (int next: graph[cur]) {
      if (next == parent) continue;
      postOrder(next, cur);
      count[cur] += count[next];
      ret[cur] += ret[next] + count[next];
    }
    count[cur]++;
  }

  private void preOrder(int cur, int parent) {
    for (int next: graph[cur]) {
      if (next == parent) continue;
      ret[next] = ret[cur] - count[next] + (N - count[next]);
      preOrder(next, cur);
    }
  }

  private Set<Integer>[] buildGraph(int[][] edges, int N) {
    Set<Integer>[] graph = new Set[N];
    Arrays.fill(graph, new HashSet<>());
    for (int[] e : edges) {
      graph[e[0]].add(e[1]);
      graph[e[1]].add(e[0]);
    }
    return graph;
  }

  public static void main(String[] args) {
    A834SumofDistancesInTree o = new A834SumofDistancesInTree();
    int[][] es = new int[][]{{0,1},{0,2},{2,3},{2,4},{2,5}};
    o.sumOfDistancesInTree(6, es);
  }
}
