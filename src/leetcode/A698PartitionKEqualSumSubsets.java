package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k
 * non-empty subsets whose sums are all equal.

 Example 1:

 Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 Output: True
 Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.

 Note:

 1 <= k <= len(nums) <= 16.
 0 < nums[i] < 10000.
 */

public class A698PartitionKEqualSumSubsets {

  public boolean canPartitionKSubset(int[] nums, int k) {
    int sum = Arrays.stream(nums).sum();
    if (sum % k > 0) return false;
    int[] memo = new int[1 << nums.length];
    memo[memo.length-1] = 1;
    return search(nums, 0, sum, sum / k, memo);
  }

  private boolean search(int[] nums, int used, int sum, int target, int[] memo) {
    if (memo[used] == 0) {
      memo[used] = 2;
      int t = (sum-1)%target+1;
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] > t || (used >> i & 1) == 1) continue;
        if (search(nums, used | 1 << i, sum-nums[i], target, memo)) {
          memo[used] = 1;
          break;
        }
      }
    }
    return memo[used] == 1;
  }

  public boolean canPartitionKSubsets(int[] nums, int k) {
    int sum = 0, max = nums[0];
    for (int num : nums) {
      sum += num;
      max = Math.max(max, num);
    }
    if (sum%k != 0 || max > sum/k) return false;
    sum = sum/k;
    return helper(nums, 0, 0, sum, k, new boolean[nums.length]);
  }

  private boolean helper(int[] nums, int pos, int sum, int target, int k, boolean[] used) {
    if (k == 1) return true;
    if (sum == target) {
      return helper(nums, 0, 0, target, k-1, used);
    }
    if (sum > target) return false;
    for (int i = pos; i < nums.length; i++) {
      if (!used[i]) {
        used[i] = true;
        if (helper(nums, i+1, sum+nums[i], target, k, used)) return true;
        used[i] = false;
      }
      if(!used[i] && sum == 0) break;
    }
    return false;
  }

  public List<Integer>[] partitionKSubsets(int[] nums, int k) {
    int sum = 0, max = nums[0];
    for (int num : nums) {
      sum += num;
      max = Math.max(max, num);
    }

    if (sum%k != 0 || max > sum/k) return null;
    sum = sum/k;

    List[] rets = new ArrayList[k];
    return helper(nums, 0, 0, sum, k, new boolean[nums.length], rets, new ArrayList<>()) ? rets : null;
  }

  private boolean helper(int[] nums, int pos, int sum, int target, int k, boolean[] used, List[] rets, List<Integer> ret) {
    if (k == 1) {
      for (int i = 0; i < used.length; i++) {
        if (!used[i]) {
          ret.add(nums[i]);
        }
      }
      rets[0] = ret;
      return true;
    }
    if (sum == target) {
      rets[k-1] = ret;
      return helper(nums, 0, 0, target, k-1, used, rets, new ArrayList<>());
    }
    if (sum > target) return false;
    for (int i = pos; i < nums.length; i++) {
      if (!used[i]) {
        used[i] = true;
        ret.add(nums[i]);
        if (helper(nums, i+1, sum+nums[i], target, k, used, rets, ret)) return true;
        ret.remove(ret.size()-1);
        used[i] = false;
      }
      if(!used[i] && sum == 0) break;
    }
    return false;
  }

  public static void main(String[] args) {
    A698PartitionKEqualSumSubsets in = new A698PartitionKEqualSumSubsets();
    List<Integer>[] rets = in.partitionKSubsets(new int[]{4, 3, 2, 3, 1, 1, 5, 4, 2, }, 5);
    if (rets == null) {
      System.out.println("no result");
      return;
    }
    for (List<Integer> l : rets) {
      for (Integer v : l) {
        System.out.print(v + " ");
      }
      System.out.println();
    }
  }
}
