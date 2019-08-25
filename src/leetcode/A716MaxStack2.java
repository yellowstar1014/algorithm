package leetcode;

import java.util.*;

public class A716MaxStack2 {
  private DoubleLinkedList list = new DoubleLinkedList();
  private TreeMap<Integer, List<Node>> treeMap = new TreeMap<>();

  public A716MaxStack2() {

  }

  public int pop() {
    int val = list.pop();
    List<Node> nodes = treeMap.get(val);
    nodes.remove(nodes.size()-1);
    return val;
  }

  public void push(int x) {
    Node node = list.append(x);
    List<Node> nodes = treeMap.computeIfAbsent(x, k -> new ArrayList<>());
    nodes.add(node);
  }

  public int peekMax() {
    return treeMap.lastKey();
  }

  public int popMax() {
    int max = treeMap.lastKey();
    List<Node> nodes = treeMap.get(max);
    nodes.remove(nodes.size()-1);
    if  (nodes.isEmpty()) treeMap.remove(max);
    return max;
  }

  class DoubleLinkedList {
    Node head;
    Node tail;

    DoubleLinkedList() {
      head = new Node(-1);
      tail = new Node(-1);
      head.next = tail;
      tail.prev = head;
    }

    public void remove(Node node) {
      node.prev.next = node.next;
      node.next.prev = node.prev;
      node.prev = null;
      node.next = null;
    }

    public Node append(int val) {
      Node node = new Node(val);
      node.prev = tail.prev;
      node.next = tail;
      node.prev.next = node;
      tail.prev = node;
      return node;
    }

    public int pop() {
      Node node = tail.prev;
      if (node == head) throw new EmptyStackException();
      node.prev.next = tail;
      tail.prev = node.prev;
      node.prev = null;
      node.next = null;
      return node.val;
    }
  }

  class Node {
    int val;
    Node prev;
    Node next;
    Node (int val) {
      this.val = val;
    }
  }
}


