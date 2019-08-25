package design;

import java.util.PriorityQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyDelayQueue<E extends Delayed> {

  private final transient ReentrantLock lock = new ReentrantLock();
  private final PriorityQueue<E> queue = new PriorityQueue<>();
  private final Condition available = lock.newCondition();
  private Thread leader = null;


  public void offer(E e) {
    lock.lock();
    try {
      queue.offer(e);
      if (queue.peek() == e) {
        leader = null;
        available.signal();
      }
    } finally {
      lock.unlock();
    }
  }

  public E poll() throws InterruptedException {
    lock.lockInterruptibly();
    try {
      while (true) {
        E first = queue.peek();
        if (first == null) {
          available.await();
        } else {
          long delay = first.getDelay(TimeUnit.NANOSECONDS);
          if (delay <= 0)
            return queue.poll();

          first = null;

          if (leader != null)
            available.await();
          else {
            Thread curThread = Thread.currentThread();
            leader = curThread;
            try {
              available.awaitNanos(delay);
            } finally {
              if (leader == curThread)
                leader = null;
            }
          }
        }
      }
    } finally {
      if (leader == null && queue.peek() != null)
        available.signal();
      lock.unlock();
    }
  }
}
