package leetcode;

import java.util.Stack;

public class A716MaxStack {
  private Stack<Integer> stack = new Stack<>();
  private Stack<Integer> maxStack = new Stack<>();


  public A716MaxStack() {

  }

  public int pop() {
    maxStack.pop();
    return stack.pop();
  }

  public void push(int x) {
    stack.push(x);
    if (stack.isEmpty()) maxStack.push(x);
    else {
      int max = Math.max(stack.peek(), x);
      maxStack.push(x);
    }
  }

  public int peekMax() {
    return maxStack.peek();
  }

  public int popMax() {
    int max = maxStack.peek();
    Stack<Integer> tmp = new Stack<>();
    while (!maxStack.isEmpty() && maxStack.peek() == max) {
      maxStack.pop();
      tmp.push(stack.pop());
    }
    tmp.pop();
    while (!tmp.isEmpty()) {
      int cur = tmp.pop();
      stack.push(cur);
      maxStack.push(Math.max(maxStack.peek(), cur));
    }
    return max;
  }
}
