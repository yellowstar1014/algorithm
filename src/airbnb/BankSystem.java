package airbnb;

import java.util.*;

public class BankSystem {
  private Map<Integer, Integer> balance = new HashMap<>();
  private Map<Integer, Map<Long, Integer>> memo = new HashMap<>();

  public void save(int id, int money, long date) {
    balance.put(id, balance.getOrDefault(id, 0) + money);
    memo.computeIfAbsent(id, k -> new HashMap<>()).put(date, balance.get(id));
  }

  public void withDraw(int id, int money, long date) {
    if (!balance.containsKey(id)) return;
    balance.put(id, balance.get(id) - money);
    memo.get(id).put(date, balance.get(id));
  }

  public int[] query(int id, long startDate, long endDate) {
    if (!memo.containsKey(id)) return new int[0];
    int[] ret = new int[2];

    Map<Long, Integer> statement = memo.get(id);
    List<Long> dates = new ArrayList<>(statement.keySet());
    Collections.sort(dates);

    if (statement.containsKey(startDate)) {
      ret[0] = statement.get(startDate);
    } else {
      int j = -(Collections.binarySearch(dates, startDate)+1);
      ret[0] = j == 0 ? 0 : statement.get(dates.get(j-1));
    }

    if (statement.containsKey(endDate)) {
      ret[1] = statement.get(endDate);
    } else {
      int j = -(Collections.binarySearch(dates, endDate)+1);
      ret[1] = j == 0 ? 0 : statement.get(dates.get(j-1));
    }

    return ret;
  }
}
