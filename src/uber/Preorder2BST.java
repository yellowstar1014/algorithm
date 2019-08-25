package uber;


import linkedin.TreeNode;

import java.util.*;

public class Preorder2BST {
  public TreeNode create(List<Integer> preorder) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode root = new TreeNode(preorder.get(0));
    stack.push(root);
    for (int i = 1; i < preorder.size(); i++) {
      TreeNode cur = new TreeNode(preorder.get(i));
      TreeNode p = null;
      while (!stack.isEmpty() && cur.val > stack.peek().val) {
        p = stack.pop();
      }
      if (p == null) {
        stack.peek().left = cur;
      } else {
        p.right = cur;
      }
      stack.push(cur);
    }
    return root;
  }

  public TreeNode levelOrder(List<Integer> levelOrder) {
    Queue<TreeNode> q = new LinkedList<>();
    TreeNode root = new TreeNode(levelOrder.get(0));
    q.offer(root);

    Queue<int[]> r = new LinkedList<>();
    r.offer(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE});

    int i = 1;

    while(i < levelOrder.size()) {
      TreeNode cur = new TreeNode(levelOrder.get(i++));
      while (!q.isEmpty() && (cur.val > r.peek()[1] || cur.val < r.peek()[0])) {
        q.poll();
        r.poll();
      }

      TreeNode p = q.peek();
      int[] range = r.peek();

      if (cur.val < p.val) {
        p.left = cur;
        r.offer(new int[]{range[0], p.val});
      } else {
        p.right = cur;
        r.offer(new int[]{p.val, range[1]});
        r.poll();
        q.poll();
      }
      q.offer(cur);
    }

    return root;
  }

  public static void main(String[] args) {
    Preorder2BST in = new Preorder2BST();
    TreeNode r1 = in.create(new ArrayList<>(Arrays.asList(10, 5, 3, 2, 8, 7, 14)));
    TreeNode r2 = in.levelOrder(new ArrayList<>(Arrays.asList(10,5,14,3,8,2,7)));
    System.out.println();
  }
}