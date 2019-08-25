package airbnb;

import java.util.LinkedList;

public class QueueWithFixedArray2 {
  private int limit;
  private Object[] headList;
  private Object[] tailList;
  private int tail;
  private int head;
  private int count;

  public QueueWithFixedArray2(int limit) {
    this.limit = limit;
    this.headList = new Object[limit];
    this.tailList = headList;
    this.count = 0;
    this.tail = 0;
    this.head = 0;
  }

  public void offer(int v) {
    if (tail == limit-1) {
      Object[] next = new Object[limit];
      tailList[tail] = next;
      tailList = next;
      tail = 0;
    }
    tailList[tail++] = v;
    count++;
  }

  public Integer poll() {
    if (count == 0) return null;
    if (head == limit-1) {
      headList = (Object[])headList[head];
      head = 0;
    }
    count--;
    return (int)headList[head++];
  }

  public int size() {
    return count;
  }

  public static void main(String args[])
  {
    QueueWithFixedArray2 queue = new QueueWithFixedArray2(5);
    System.out.println(queue.poll());//null
    queue.offer(1);
    queue.offer(1);
    queue.offer(2);
    queue.offer(3);

    queue.offer(4);
    queue.offer(5);
    queue.offer(6);
    System.out.println(queue.poll());//1
    System.out.println(queue.poll());//1
    System.out.println(queue.poll());//2
    System.out.println(queue.poll());//3
    System.out.println(queue.poll());//4
    queue.offer(7);
    System.out.println("size: " + queue.size());//size:3
    System.out.println(queue.poll());//5
    System.out.println(queue.poll());//6
    System.out.println(queue.poll());//7
    System.out.println(queue.poll());//null
  }
}
