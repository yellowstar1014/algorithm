package leetcode.dp;

// f(i,l,k) = min(max( f(i,h-i,k-1), f(h, i+l-h, k))

// f(n,k) = min(max(f(i,k-1), f(n-i,k))+1)  i = [1,n-1]

// f(0,k) = 0
// f(h,1) = h

// f(N,K)

public class A887SuperEggDrop {
  public static int superEggDrop(int K, int N) {
    int[][] f = new int[N+1][K+1];
    for (int i = 0; i < f.length; i++) {
      f[i][1] = i;
    }

    for (int n = 1; n < N+1; n++) {
      for (int k = 2; k < K+1; k++) {
        f[n][k] = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
          f[n][k] = Math.min(f[n][k], Math.max(f[i][k-1], f[n-i][k])+1);
        }
      }
    }
    return f[N][K];
  }
}
