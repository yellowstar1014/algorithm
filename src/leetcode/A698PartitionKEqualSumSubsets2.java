package leetcode;

import java.util.Arrays;

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

public class A698PartitionKEqualSumSubsets2 {

  public boolean canPartitionKSubsets(int[] nums, int k) {
    int sum = Arrays.stream(nums).sum();
    if (sum % k != 0) return false;
    Boolean[] memo = new Boolean[1 << nums.length];
    //memo[memo.length-1] = true;
    boolean ret = search(0, 0, nums, sum/k, k, 0, memo);
    return ret;
  }

  private boolean search(int pos, int sum, int[] nums, int target, int k, int used, Boolean[] memo) {
    if (memo[used] != null) return memo[used];

    if (k == 1) return true;
    if (sum == target) {
      return search(0, 0, nums, target, k-1, used, memo);
    }

    if (sum > target || pos >= nums.length) return false;

    if ((used >> pos & 1) == 0) {
      if (search(pos+1, sum+nums[pos], nums, target, k, used | 1 << pos, memo)) {
        memo[used] = true;
        return true;
      }
      if (sum == 0) {
        memo[used] = false;
        return false;
      }
    }

    boolean ret = search(pos+1, sum, nums, target, k, used, memo);
    memo[used] = ret;
    return ret;
  }

  public static void main(String[] args) {
    A698PartitionKEqualSumSubsets2 in = new A698PartitionKEqualSumSubsets2();
    boolean ret = in.canPartitionKSubsets(new int[]{4}, 2);
    System.out.println(ret);
  }

}
