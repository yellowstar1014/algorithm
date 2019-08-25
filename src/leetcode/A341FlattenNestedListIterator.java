package leetcode;

import java.util.*;

public class A341FlattenNestedListIterator {
  public interface NestedInteger {
    public boolean isInteger();
    public Integer getInteger();
    public List<NestedInteger> getList();
  }

  public class NestedIterator implements Iterator<Integer> {
    private Stack<ListIterator<NestedInteger>> stack = new Stack<>();

    public NestedIterator(List<NestedInteger> nestedList) {
      stack.push(nestedList.listIterator());
    }

    @Override
    public Integer next() {
      hasNext();
      return stack.peek().next().getInteger();
    }

    @Override
    public boolean hasNext() {
      while (!stack.isEmpty()) {
        if (!stack.peek().hasNext()) {
          stack.pop();
        } else {
          NestedInteger ni = stack.peek().next();
          if (ni.isInteger()) {
            stack.peek().previous();
            return true;
          } else {
            stack.push(ni.getList().listIterator());
          }
        }
      }
      return false;
    }
  }

}


/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
