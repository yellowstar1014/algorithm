package uber;

import java.util.*;

public class LongestPath2 {
  static class Node {
    int value;
    LinkedHashSet<Integer> path;
    Node(int value, LinkedHashSet<Integer> p) {
      this.value = value;
      this.path = new LinkedHashSet<>(p);
      this.path.add(value);
    }

    Node(int value) {
      this.value = value;
      this.path = new LinkedHashSet<>();
      this.path.add(value);
    }

    public void add(int v) {
      path.add(v);
    }
  }

  public List<List<Integer>> longestPath(List<List<Integer>> paths, int N) {
    Set<Integer> start = new HashSet<>();
    Map<Integer, Set<Integer>> graph = createGraph(paths, start);
    Queue<Node> queue = new LinkedList<>();
    for (int v : start) {
        queue.offer(new Node(v));
    }

    List<Node> tail = new ArrayList<>();

    while (!queue.isEmpty()) {
      int size = queue.size();
      tail = new ArrayList<>(queue);
      while(size-- > 0) {
        Node cur = queue.poll();
        for (int next : graph.get(cur.value)) {
          if (cur.path.contains(next)) continue;
          Node nextNode = new Node(next, cur.path);
          queue.offer(nextNode);
        }
      }
    }

    List<List<Integer>> ret = new ArrayList<>();
    for (Node t : tail) {
      ret.add(new ArrayList<>(t.path));
    }

    return ret;
  }

  private Map<Integer, Set<Integer>> createGraph(List<List<Integer>> paths, Set<Integer> set) {
    Map<Integer, Set<Integer>> graph = new HashMap<>();

    for (List<Integer> p : paths) {
      set.add(p.get(0));
      for (int i = 0; i < p.size()-1; i++) {
        graph.computeIfAbsent(p.get(i), k -> new HashSet<>()).add(p.get(i+1));
      }
    }

    return graph;
  }
}
