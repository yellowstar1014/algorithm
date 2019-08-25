package google;

//  e.g. [1，2，1，2 ]->  (1+2)*(1+2)=9
//  f(i,j) = f(i,k) +/* f(k+1,j)
//  i <= k < j
//  f(i,i) = num[i]

//  1 -2 2 -1 -3
//

/*
国人。 一个只有正整数的list， 其中插入+， * 或者（），求得到式子最大的值。 e.g. [1，2，1，2 ]->  (1+2)*(1+2)=9.  dp解，
follow up， 如果有负数该怎么办， 如果想要拿到最大的式子该怎么办。
思路：类似burst balloon leetcode.dp[i][j] = max of for (k : i ~ j  max(leetcode.dp[i][k - 1] * leetcode.dp[k][j], leetcode.dp[i][k - 1] + leetcode.dp[k][j]))
 */
public class AddOperatorGetMax {


  public int addOperator(int[] num) {
    int N = num.length;
    int[][] f = new int[N][N];

    for (int i = 0; i < N; i++) {
      f[i][i] = num[i];
    }

    for (int i = N-1; i >= 0; i--) {
      for (int j = i+1; j < N; j++) {
        f[i][j] = Integer.MIN_VALUE;
        for (int k = i; k < j; k++) {
          f[i][j] = Math.max(f[i][j], f[i][k] + f[k+1][j]);
          f[i][j] = Math.max(f[i][j], f[i][k] * f[k+1][j]);
        }
      }
    }
    return f[0][N-1];
  }

  public static void main(String[] args) {
    AddOperatorGetMax in = new AddOperatorGetMax();
    int ret = in.addOperator(new int[]{2,1,2,1});
    System.out.println(ret);
  }
}
