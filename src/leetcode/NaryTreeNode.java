package leetcode;

import java.util.ArrayList;
import java.util.List;

public class NaryTreeNode {
  public int val;
  public List<NaryTreeNode> children;
  public NaryTreeNode(int val) {
    this.val = val;
    this.children = new ArrayList<>();
  }
}
