package uber;

import leetcode.NaryTreeNode;

import java.util.*;

public class NaryTreeFromTuple {
  public NaryTreeNode buildTree(int[][] tuples) {
    Map<Integer, Integer> indegree = new HashMap<>();
    Map<Integer, List<Integer>> graph = new HashMap<>();
    Map<Integer, NaryTreeNode> nodes = new HashMap<>();

    for (int[] t : tuples) {
      if (t[0] == t[1]) continue;
      graph.computeIfAbsent(t[0], k -> new ArrayList<>()).add(t[1]);
      if (!indegree.containsKey(t[0])) indegree.put(t[0], 0);
      indegree.put(t[1], indegree.getOrDefault(t[1], 0) + 1);
    }

    Queue<Integer> queue = new LinkedList<>();

    NaryTreeNode root = null;

    for (int key : indegree.keySet()) {
      int v = indegree.get(key);
      if (v == 0) {
        root = new NaryTreeNode(key);
        nodes.put(key, root);
        queue.offer(key);
        break;
      }
    }

    while (!queue.isEmpty()) {
      int cur = queue.poll();
      List<Integer> children = graph.get(cur);
      if (children == null) continue;
      NaryTreeNode p = nodes.get(cur);
      for (int next : children) {
        if (indegree.get(next) > 1) {
          indegree.put(next, indegree.get(next) - 1);
        } else {
          NaryTreeNode node = new NaryTreeNode(next);
          nodes.put(next, node);
          p.children.add(node);
          queue.offer(next);
        }
      }
    }

    return root;
  }

  public static void main(String[] args) {
    NaryTreeFromTuple in = new NaryTreeFromTuple();
    NaryTreeNode root = in.buildTree(new int[][]{{1,1},{1,2},{1,3},{2,2},{3,3},{1,4},{2,4},{4,4}});
    System.out.println(root);
  }
}
