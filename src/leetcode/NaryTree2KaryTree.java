package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class NaryTree2KaryTree {
  public NaryTreeNode convert(NaryTreeNode root, int k) {
    Queue<NaryTreeNode> old = new LinkedList<>();
    Queue<NaryTreeNode> cur = new LinkedList<>();
    old.offer(root);
    cur.offer(root);
    while (!old.isEmpty()) {
      NaryTreeNode node = cur.poll();
      node.children.clear();
      for (int i = 0; i < k; i++) {
        if (old.isEmpty()) break;
        NaryTreeNode child = old.poll();
        for (NaryTreeNode cc : child.children) {
          old.offer(cc);
        }
        node.children.add(child);
        cur.offer(child);
      }
    }
    return root;
  }
}
