package leetcode;

import common.TreeNode;

public class A222CountCompleteTreeNodes {
  public int countNodes(TreeNode root) {
    if (root == null) return 0;
    int h = -1;
    TreeNode node = root;
    while (node != null) {
      h++;
      node = node.left;
    }
    // h = 3
    int v = (int)Math.pow(2, h); // 8

    int l = v, r = v+v, start = l, end = r; // l = start = 8 r = end = 16

    int mid = r;
    TreeNode cur = null;
    while (l < r) {  // l=8,8,8,8,9 r=16,12,10,9,9
      mid = l + (r-l) / 2; // mid=12,10,9,8
      cur = getNode(root, mid, start, end, h); // cur=null
      if (cur == null) {
        r = mid;
      } else {
        l = mid + 1;
      }
    }

    return v - 1 + l - start;
  }

  // root=1, v=12  l=8 r=16 h=3
  private TreeNode getNode(TreeNode root, int v, int l, int r, int h) {
    while(h-- > 0) {
      if (v*4 < (l+r)) {
        root = root.left;
        r = (l+r) / 2;
      } else {
        root = root.right;
        l = (l+r) / 2;
      }
    }
    return root;
  }

  public static void main(String[] args) {
    A222CountCompleteTreeNodes o = new A222CountCompleteTreeNodes();
    TreeNode n1 = new TreeNode(1);
    TreeNode n2 = new TreeNode(2);
    TreeNode n3 = new TreeNode(3);
    TreeNode n4 = new TreeNode(4);
    TreeNode n5 = new TreeNode(5);
    TreeNode n6 = new TreeNode(6);
    TreeNode n7 = new TreeNode(7);
    TreeNode n8 = new TreeNode(8);
    n1.left = n2; n1.right = n3;
    n2.left = n4; n2.right = n5;
    n3.left = n6; n3.right = n7;
    n4.left = n8;
    o.countNodes(n1);
  }
}
