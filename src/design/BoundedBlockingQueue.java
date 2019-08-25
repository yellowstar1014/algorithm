package design;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBlockingQueue<E> {
  private final Queue<E> queue = new LinkedList<>();
  private final Lock putLock = new ReentrantLock();
  private final Lock takeLock = new ReentrantLock();

  private final Condition notEmpty = takeLock.newCondition();
  private final Condition notFull = putLock.newCondition();
  private int capacity;

  private AtomicInteger count = new AtomicInteger(0);

  public BoundedBlockingQueue(int capacity) {
    this.capacity = capacity;
  }

  public void offer(E e) throws InterruptedException {
    int oldCount = -1;
    putLock.lock();
    try {
      while (count.get() == capacity) {
        notFull.await();
      }
      queue.offer(e);
      oldCount = count.getAndIncrement();

      if (oldCount + 1 < capacity) {
        notFull.signal();
      }

    } finally {
      putLock.unlock();
    }

    if (oldCount == 0) {
      takeLock.lock();
      try {
        notEmpty.signal();
      } finally {
        takeLock.unlock();
      }
    }
  }

  public E poll() throws InterruptedException {
    int oldCount = -1;
    E e;
    takeLock.lock();
    try {
      while (count.get() == 0) {
        notEmpty.await();
      }
      e = queue.poll();
      oldCount = count.getAndDecrement();

      if (oldCount - 1 > 0) {
        notEmpty.signal();
      }

    } finally {
      takeLock.unlock();
    }

    if (oldCount == capacity) {
      putLock.lock();
      try {
        notFull.signal();
      } finally {
        putLock.unlock();
      }
    }
    return e;
  }

  public E peek() throws InterruptedException {
    if (count.get() == 0) return null;
    takeLock.lock();
    try {
      return queue.peek();
    } finally {
      takeLock.unlock();
    }
  }

  public int size() {
    return count.get();
  }
}
