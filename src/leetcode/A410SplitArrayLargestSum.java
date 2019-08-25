package leetcode;

public class A410SplitArrayLargestSum {
  public static int splitArray(int[] nums, int m) {
    long r = 0;
    for (int num : nums) {
      r += num;
    }
    long l = r / m;
    while (l < r) {
      long mid = l + (r-l)/2;
      if (test(mid, m, nums)) {
        r = mid;
      } else {
        l = mid+1;
      }
    }
    return (int)l;
  }

  private static boolean test(long target, int m, int[]nums) {
    long sum = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > target) return false;
      if (sum + nums[i] <= target) {
        sum += nums[i];
      } else {
        sum = nums[i];
        m--;
      }
    }
    m--;
    return m >= 0;
  }

  public static void main(String[] args) {
    A410SplitArrayLargestSum.splitArray(new int[]{1, Integer.MAX_VALUE}, 2);
  }
}
