package uber;

import java.util.*;

public class LongestPath {

    static class Node {
      int value;
      int dist;
      Node prev;
      Node(int value, int dist, Node prev) {
        this.value = value;
        this.dist = dist;
        this.prev = prev;
      }
    }

  public List<List<Integer>> longestPath(List<List<Integer>> paths, int N) {
    int[] indegree = new int[N];
    Map<Integer, Set<Integer>> graph = createGraph(paths, indegree);
    Queue<Node> queue = new LinkedList<>();
    for (int i = 0; i < N; i++) {
      if (indegree[i] == 0) {
        queue.offer(new Node(i, 0, null));
      }
    }

    List<Node> tail = new ArrayList<>();

    while (!queue.isEmpty()) {
      int size = queue.size();
      tail = new ArrayList<>(queue);
      while(size-- > 0) {
        Node cur = queue.poll();
        if (graph.get(cur.value) == null) continue;
        for (int next : graph.get(cur.value)) {
          if (--indegree[next] == 0) {
            queue.offer(new Node(next, cur.dist+1, cur));
          }
        }
      }
    }

    List<List<Integer>> ret = new ArrayList<>();
    for (Node t : tail) {
      LinkedList<Integer> path = new LinkedList<>();
      while (t != null) {
        path.offerFirst(t.value);
        t = t.prev;
      }
      ret.add(path);
    }

    return ret;
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
    LongestPath in = new LongestPath();
    List<Integer> l1 = new ArrayList<>(Arrays.asList(1,3,5,7,9));
    List<Integer> l2 = new ArrayList<>(Arrays.asList(0,2,4,5));
    List<Integer> l3 = new ArrayList<>(Arrays.asList(8,10,11,12,13,14));
    List<Integer> l4= new ArrayList<>(Arrays.asList(7,8));

    List<List<Integer>> l = new ArrayList<>(Arrays.asList(l1,l2,l3,l4));

    List<List<Integer>> ret = in.longestPath(l, 15);

    for (List<Integer> r : ret) {
      for (int d : r) {
        System.out.print(d + " ");
      }
      System.out.println();
    }
  }
}
