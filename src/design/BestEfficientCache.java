package design;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 题目就是说教你设计一个cache system, 主要是实现一个getItem方法，如果这个key在内存里，直接返回； 如果这个key不在内存里，
 * 从数据源读出，放入内存； 如果内存已经满了，evict rank最小的项目。要求设计的时候是最优的。
 */
public class BestEfficientCache<K, T> {
  private Map<K, T> map = new HashMap<>();
  private PriorityQueue<K> queue = new PriorityQueue<>();
  int capacity;
  private DataSource<K, T> ds;

  public BestEfficientCache(DataSource<K, T> ds, int capacity) {
    this.ds = ds;
    this.capacity = capacity;
  }

  public T getItem(K key) {
    if (!map.containsKey(key)) {
      tryClean();
      T item = ds.getItem(key);
      map.put(key, item);
      queue.offer(key);
    }
    return map.get(key);
  }

  private void tryClean() {
    while (map.size() >= capacity) {
      K key = queue.poll();
      map.remove(key);
    }
  }

  public interface DataSource<K, T> {
    public T getItem(K key);
    public long getRank(K key);
  }

}
