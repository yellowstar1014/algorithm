package airbnb;

import leetcode.NaryTreeNode;

/**
 * coding，给一颗树，每个节点是0或1，有个目标值也是0或1，每次翻转一个节点，同时隔行的子节点也会被翻转，求最少翻转几次
 */
public class FlipTreeGetTarget {
  public int flip(NaryTreeNode root, NaryTreeNode target) {
    return helper(root, target, false);
  }

  private int helper(NaryTreeNode root, NaryTreeNode target, boolean flip) {
    if (root == null) return 0;
    if (flip) {
      root.val ^= 1;
    }
    int count = 0;
    if (root.val != target.val) {
      count++;
      flip = !flip;
    }

    for (int i = 0; i < root.children.size(); i++) {
      count += helper(root.children.get(i), target.children.get(i), flip);
    }

    return count;
  }
}
