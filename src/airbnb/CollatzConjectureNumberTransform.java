package airbnb;

import java.util.HashMap;
import java.util.Map;

public class CollatzConjectureNumberTransform {
  // 8 -> 4 -> 2 -> 1
  // 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
  public int getLongestStep(int n) {
    Map<Integer, Integer> memo = new HashMap<>();
    int ret = 0;
    for (int i = 1; i <=n; i++) {
      ret = Math.max(ret, helper(i, memo));
    }
    return ret;
  }

  private int helper(int n, Map<Integer, Integer> memo) {
    if (n == 1) return 0;
    if (memo.containsKey(n)) {
      return memo.get(n);
    }
    int ret = 1;
    if (n%2 == 0) {
      ret += helper(n/2, memo);
    } else {
      ret += helper(n*3+1, memo);
    }
    memo.put(n, ret);
    return ret;
  }
}
