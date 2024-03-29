package leetcode.dp;

/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of
 * painting each house with a certain color is different. You have to paint all the houses such that no two adjacent
 * houses have the same color.

 The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0]
 is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on...
 Find the minimum cost to paint all houses.

 Note:
 All costs are positive integers.

 Example:

 Input: [[17,2,17],[16,16,5],[14,3,19]]
 Output: 10
 Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
 Minimum cost: 2 + 5 + 3 = 10.
 */
public class A256PaintHouse {
  // g(i,0) = g(i-1,1) + min(costs[i][0],costs[i][1]) and g(i-1,2)
  //
  // g(0,j) = costs[0][j]
  public int minCost(int[][] costs) {
    int red = costs[0][0], blue = costs[0][1], green = costs[0][2], preRed = red, preBlue = blue;
    for (int i = 1; i < costs.length; i++) {
      red = Math.min(blue, green) + costs[i][0];
      blue = Math.min(preRed, green) + costs[i][1];
      green = Math.min(preRed, preBlue) + costs[i][2];
      preRed = red;
      preBlue = blue;
    }
    return Math.max(red, Math.max(blue, green));
  }
}
