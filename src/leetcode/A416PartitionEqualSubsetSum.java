package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class A416PartitionEqualSubsetSum {
  public boolean canPartition(int[] nums) {
    int sum = Arrays.stream(nums).sum();
    if (sum%2 == 1) return false;
    sum = sum/2;
    return helper(nums, 0, sum, new HashMap<Long, Boolean>());
  }

  private boolean helper(int[] nums, int pos, int sum, Map<Long, Boolean> memo) {
    if (sum == 0) return true;
    if (sum < 0) return false;
    if (pos >= nums.length) return false;
    long key = getKey(pos, sum);
    if (memo.containsKey(key)) return memo.get(key);
    if (helper(nums, pos+1, sum-nums[pos], memo)) {
      memo.put(key, true);
      return true;
    }
    if (helper(nums, pos+1, sum, memo)) {
      memo.put(key, true);
      return true;
    }
    memo.put(key, false);
    return false;
  }

  private long getKey(int i, int v) {
    return (long)i << 32 | (long)v;
  }
}
