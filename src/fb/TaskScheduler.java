package fb;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class TaskScheduler {
  public int schedule(String tasks, int coolDown) {
    Set<Character> set = new HashSet<>();
    Queue<Character> queue = new LinkedList<>();
    int ret = 0, i = 0;
    while (i < tasks.length()) {
      char c = tasks.charAt(i);
      if (set.contains(c)) {
        queue.offer('-');
      } else {
        queue.offer(c);
        set.add(c);
        i++;
      }
      if (queue.size() > coolDown) {
        set.remove(queue.poll());
      }
      ret++;
    }
    return ret;
  }

  public static void main(String[] args) {
    TaskScheduler in = new TaskScheduler();
    int ret = in.schedule("aabcbb", 1); // a_abcb
    System.out.println(ret);
  }
}
