package leetcode;

class ListNode {
  int val;
  ListNode next;
  ListNode(int x) { val = x; }
}

public class A25ReverseNodesInK {
  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode dummy = new ListNode(0);
    //dummy.next = head;
    ListNode cur = head;
    ListNode tail = dummy;
    while (cur != null) { // cur = 1
      ListNode start = cur; // start = 1
      ListNode nextTail = cur; // nextTail = 1
      int c = k;
      while (c-- > 0 && cur != null) {
        cur = cur.next;
      }
      // cur = null
      if (c != 0) break;
      while(start != cur) {
        ListNode next = cur.next; // next=null
        start.next = tail.next;
        tail.next = start;
        start = next; // start=null
      }
      tail = nextTail; // 1
    }
    return dummy.next;
  }

  public static void main(String[] args) {
    A25ReverseNodesInK in = new A25ReverseNodesInK();
    ListNode n1 = new ListNode(1);
    ListNode n2 = new ListNode(2);
    n1.next = n2;

    in.reverseKGroup(n1, 2);
  }
}
