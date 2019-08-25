package airbnb;

import java.util.LinkedList;

public class QueueWithFixedArray {
  private int count;
  private int tail;
  private int head;
  private LinkedList<Object> tailList;
  private LinkedList<Object> headList;
  private int limit;

  public QueueWithFixedArray(int limit) {
    this.limit = limit;
    tailList = new LinkedList<>();
    headList = tailList;
    tail = 0;
    head = 0;
  }

  public void offer(int v) {
    if (tail == limit-1) {
      LinkedList<Object> newList = new LinkedList<>();
      tailList.offer(newList);
      tailList = newList;
      tail = 0;
    }
    tailList.add(v);
    tail++;
    count++;
  }

  public Integer poll() {
    if (count == 0) return null;
    if (head == limit-1) {
      headList = (LinkedList<Object>)headList.poll();
      head = 0;
    }
    count--;
    head++;
    return (int)headList.poll();
  }

  public int size() {
    return count;
  }

  public static void main(String args[])
  {
    QueueWithFixedArray queue = new QueueWithFixedArray(5);
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
