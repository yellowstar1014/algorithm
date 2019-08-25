package airbnb;

import java.util.*;

public class Wizard {
  class Node {
    int wizard;
    Node prev;
    int cost;
    Node (int wizard, int cost, Node prev) {
      this.wizard = wizard;
      this.cost = cost;
      this.prev = prev;
    }
  }

  public List<Integer> getShortestPath(List<List<Integer>> wizards, int source, int target) {
    LinkedList<Integer> path = new LinkedList<>();
    Queue<Node> q = new PriorityQueue<>((a,b) -> a.cost-b.cost);
    boolean[] visited = new boolean[wizards.size()];

    q.offer(new Node(source, 0, null));

    Node cur = null;
    while (!q.isEmpty()) {
      cur = q.poll();
      if (cur.wizard == target) break;
      visited[cur.wizard] = true;
      for (int next : wizards.get(cur.wizard)) {
        if (visited[next]) continue;
        int diff = cur.wizard - next;
        q.offer(new Node(next, diff*diff, cur));
      }
    }

    while(cur != null) {
      path.offerFirst(cur.wizard);
      cur = cur.prev;
    }

    return path;
  }
}
