package fb;

import java.util.HashMap;
import java.util.Map;

public class KSum {

  // f(i,t,k) = f(i-1,t,k) + f(i-1, t-num[i-1], k-1)
  // f(i,0,0) = 1
  // f(0,t,k) = 0 k!=0 || t !=0
  // f(i,t,0) = 0 t!=0

  public int ksum2(int[] num, int K ,int target) {
    int[][][] f = new int[num.length+1][K+1][target+1];

    for (int i = 0; i < num.length; i++) {
      for (int k = i; k <= K; k++) {
        for (int t = 0; t <= target; t++) {
          if (k == 0 && t == 0) f[i][k][t] = 1;
          else if (i == 0 || k == 0) {
            f[i][k][t] = 0;
          } else {
            f[i][k][t] = f[i - 1][k][t];
            if (t - num[i - 1] >= 0) {
              f[i][k][t] += f[i - 1][k - 1][t - num[i - 1]];
            }
          }
        }
      }
    }

    return f[num.length][K][target];
  }

//  public int ksum(int[] num, int K, int target) {
//    return helper()
//  }

  private int helper(int pos, int target, int k, int[] num, int[][][] memo) {
    if (memo[pos][k][target] != -1) return memo[pos][k][target];

    if (target == 0 && k == 0) return 1;
    if (target == 0 || k == 0 || pos == num.length) return 0;

    int count = 0;
    for (int i = pos; i < num.length-k+1; i++) {
      if (target-num[i] >= 0) {
        count += helper(i+1, target-num[i], k-1, num, memo);
      }
    }
    memo[pos][k][target] = count;
    return count;
  }
}
