package leetcode.dp;

import java.util.*;

/**
 * Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.

 Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and is invalid, but 0 is valid.

 You may return the answer in any order.

 Example 1:

 Input: N = 3, K = 7
 Output: [181,292,707,818,929]
 Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 Example 2:

 Input: N = 2, K = 1
 Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]

 Note:

 1 <= N <= 9
 0 <= K <= 9
 */
public class A967NumbersWithSameConsecutiveDifferences {

  // f(i,x) = f(i-1,x+K) U f(i-1, x-K) x+K <= 9  x-K > 0 || x-K==0 and i-1 > 0


  public int[] numsSameConsecDiff(int N, int K) {
    List<Integer> tmp = new ArrayList<>();
    Map<Long, List<Integer>> memo = new HashMap<>();
    for (int v = 1; v <= 9; v++) {
      tmp.addAll(bfs(v, 0,1, N, K, memo));
    }
    int[] ret = new int[tmp.size()];
    for (int i = 0; i < ret.length; i++) {
      ret[i] = tmp.get(i);
    }
    return ret;
  }

  private List<Integer> bfs(int cur, int d, int l, int N, int K, Map<Long, List<Integer>> memo) {
    if (cur > 9 || cur < 0) return new ArrayList<>();
    if (l > N) new ArrayList<>();
    long key = ((long)cur<<32)|(long)l;
    if (memo.containsKey(key)) {
      return memo.get(key);
    }
    List<Integer> r1 = bfs(cur+K, d*10+cur,l+1, N, K, memo);
    List<Integer> r2 = bfs(cur-K, d*10+cur, l+1, N, K, memo);
    List<Integer> r = new ArrayList<>(r1);
    r.addAll(r2);
    memo.put(key, r);
    return r;
  }
}
