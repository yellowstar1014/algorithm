package leetcode.dp;

/**
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with
 * a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

 The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0]
 is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on...
 Find the minimum cost to paint all houses.

 Note:
 All costs are positive integers.

 Example:

 Input: [[1,5,3],[2,9,4]]
 Output: 5
 Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
 Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
 Follow up:
 Could you solve it in O(nk) runtime?
 */
public class A265PaintHouseII {
  // f(i,j) -> min cost paint houses 0 to i end with color j
  // f(0,j) = costs[0][j]
  // f(i,j) = min(f(i-1,k) k!= j) + costs[i][j]
  //
  // min(f(i-1, k)) or secondMin(f(i-1,k)) end-color
  //
  // F = min(f(N-1, k))
  public int minCostII(int[][] costs) {
    int N = costs.length;
    if (N == 0) return 0;
    int K = costs[0].length, preMin = 0, preSecondMin = 0, min = -1, secondMin = -1, preColor = -1, curColor = -1;

    for (int i = 0; i < N; i++) {
      min = -1;
      secondMin = -1;
      for (int k = 0; k < K; k++) {
        int cur = 0;
        if (k != preColor) {
          cur = preMin + costs[i][k];
        } else {
          cur = preSecondMin + costs[i][k];
        }
        if (min == -1 || cur < min) {
          secondMin = min;
          min = cur;
          curColor = k;
        } else if (secondMin == -1 || cur < secondMin) {
          secondMin = cur;
        }
      }
      preMin = min;
      preSecondMin = secondMin;
      preColor = curColor;
    }
    return min;
  }
}
