package leetcode;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class A366FindLeavesOfBinaryTree {

  public List<List<Integer>> findLeaves(TreeNode root) {
    List<List<Integer>> ret = new ArrayList<>();
    traversal(root, ret);
    return ret;
  }

  private int traversal(TreeNode root, List<List<Integer>> ret) {
    if (root == null) return -1;

    int l = traversal(root.left, ret);
    int r = traversal(root.right, ret);

    int index = Math.max(l, r) + 1;

    List<Integer> list = null;
    if (ret.size() <= index) {
      list = new ArrayList<>();
      ret.add(list);
    } else {
      list = ret.get(index);
    }
    list.add(root.val);

    return index;
  }
}
