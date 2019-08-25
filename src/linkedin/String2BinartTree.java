package linkedin;

import java.util.Stack;

public class String2BinartTree {
  // 0－1－－2－3－－4－－5
  public TreeNode deserialize(String str) {
    Stack<TreeNode> stack = new Stack<>();

    int i = 0;
    while (i < str.length()) {
      int j = i;
      while (i < str.length() && str.charAt(i) == '-') i++;
      int h = i - j;
      j = i;
      while (i < str.length() && str.charAt(i) != '-') {
        i++;
      }
      int v = Integer.valueOf(str.substring(j, i));
      while (stack.size() > h) {
        stack.pop();
      }

      TreeNode cur = new TreeNode(v);
      if (!stack.isEmpty()) {
        if (stack.peek().left == null) {
          stack.peek().left = cur;
        } else {
          stack.peek().right = cur;
        }
      }
      stack.push(cur);
    }

    TreeNode root = null;
    while (!stack.isEmpty()) {
      root = stack.pop();
    }
    return root;
  }

  public static void main(String[] args) {
    String2BinartTree in = new String2BinartTree();
    TreeNode root = in.deserialize("0-1--2---3--4-5");
    System.out.println();
  }
}
