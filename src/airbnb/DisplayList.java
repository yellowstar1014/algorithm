package airbnb;

import java.util.*;

public class DisplayList {
  public List<String> display(List<String> input, int pageSize) {
    Set<String> seen = new HashSet<>();
    Deque<String> duplicates = new LinkedList<>();
    Deque<String> deque = new LinkedList<>(input);
    List<String> ret = new ArrayList<>();
    int count = 0;

    while (!deque.isEmpty()) {
      String cur = deque.poll();
      String hostId = getHostId(cur);
      if (seen.contains(hostId)) {
        duplicates.offer(cur);
      } else {
        ret.add(cur);
        seen.add(hostId);
        count++;
        if (count == pageSize) {
          count = 0;
          ret.add("-------------");
          seen.clear();
          while(!duplicates.isEmpty()) {
            deque.offerFirst(duplicates.pollLast());
          }
        }
      }
    }

    while (!duplicates.isEmpty()) {
      ret.add(duplicates.poll());
      if (++count == pageSize) {
        count = 0;
        ret.add("-------------");
      }
    }

    return ret;
  }

  private String getHostId(String cur) {
    return cur.substring(0, cur.indexOf(','));
  }
}
