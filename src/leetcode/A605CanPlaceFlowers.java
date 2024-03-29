package leetcode;

public class A605CanPlaceFlowers {
  /**
   * Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot
   * be planted in adjacent plots - they would compete for water and both would die.
   * <p>
   * Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and
   * a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.
   * <p>
   * Example 1:
   * Input: flowerbed = [1,0,0,0,1], n = 1
   * Output: True
   * Example 2:
   * Input: flowerbed = [1,0,0,0,1], n = 2
   * Output: False
   * Note:
   * The input array won't violate no-adjacent-flowers rule.
   * The input array size is in the range of [1, 20000].
   * n is a non-negative integer which won't exceed the input array size.
   */
  public boolean canPlaceFlowers(int[] flowerbed, int n) {
    for (int i = 0; i < flowerbed.length; i++) {
      int prev = i == 0 ? 0 : flowerbed[i - 1];
      int next = i == flowerbed.length - 1 ? 0 : flowerbed[i + 1];
      if (flowerbed[i] == 0 && prev == 0 && next == 0) {
        n--;
        flowerbed[i] = 1;
      }
    }
    return n <= 0;
  }

  public boolean canPlaceFlowers2(int[] flowerbed, int n) {
    int prev = 0;
    for (int i = 0; i < flowerbed.length; i++) {
      int next = i == flowerbed.length - 1 ? 0 : flowerbed[i + 1];
      if (flowerbed[i] == 0) {
        if (prev == 0 && next == 0) {
          n--;
        } else {
          prev = 0;
        }
      } else {
        prev = 1;
      }
    }
    return n <= 0;
  }
}
