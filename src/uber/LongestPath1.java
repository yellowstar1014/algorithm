package uber;

import java.util.*;

public class LongestPath1 {

  public List<Integer> longestPath(List<List<Integer>> paths, int N) {
    int[] indegree = new int[N];
    Map<Integer, Set<Integer>> graph = createGraph(paths, indegree);
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < N; i++) {
      if (indegree[i] == 0) {
        queue.offer(i);
      }
    }

    int[] dist = new int[N];
    int[] prev = new int[N];
    Arrays.fill(prev, -1);

    int r = 0;

    while (!queue.isEmpty()) {
        int cur = queue.poll();
        if (graph.get(cur) == null) continue;
        for (int next : graph.get(cur)) {
          if (dist[cur] + 1 > dist[next]) {
            dist[next] = dist[cur] + 1;
            prev[next] = cur;
          }
          if (--indegree[next] == 0) {
            queue.offer(next);

            if (graph.get(next) != null) continue;

            if (dist[next] > dist[r]) {
              r = next;
            }
          }
        }
    }

    LinkedList<Integer> path = new LinkedList<>();
      while (r != -1) {
        path.offerFirst(r);
        r = prev[r];
      }

    return path;
  }

  private Map<Integer, Set<Integer>> createGraph(List<List<Integer>> paths, int[] indegree) {
    Map<Integer, Set<Integer>> graph = new HashMap<>();

    for (List<Integer> p : paths) {
      for (int i = 0; i < p.size()-1; i++) {
        indegree[p.get(i+1)]++;
        graph.computeIfAbsent(p.get(i), k -> new HashSet<>()).add(p.get(i+1));
      }
    }

    return graph;
  }

  public static void main(String[] args) {
    LongestPath1 in = new LongestPath1();
    List<Integer> l1 = new ArrayList<>(Arrays.asList(1,3,5,7,9));
    List<Integer> l2 = new ArrayList<>(Arrays.asList(0,2,4,5));
    List<Integer> l3 = new ArrayList<>(Arrays.asList(8,6,10,11,12,13,14));
    List<Integer> l4= new ArrayList<>(Arrays.asList(7,8));

    List<List<Integer>> l = new ArrayList<>(Arrays.asList(l1,l2,l3));

    List<Integer> ret = in.longestPath(l, 15);

    for (int r : ret) {
      System.out.print(r + " ");
    }

//    for (List<Integer> r : ret) {
//      for (int d : r) {
//        System.out.print(d + " ");
//      }
//      System.out.println();
//    }
  }
}
