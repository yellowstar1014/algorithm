package uber;

public class HouseRob {
  // f(i) = max(f(i-1), houses[i]+f(i-k-1)) i >= k+1 i > 0
  // f(0) = houses[0];
  public int rob(int[] houses, int k) {
    int N = houses.length;
    int[] f = new int[N];
    f[0] = houses[0];
    for (int i = 1; i < N; i++) {
      f[i] = Math.max(f[i-1], i >= k+1 ? houses[i] + f[i-(k+1)] : houses[i]);
    }
    return f[N-1];
  }
}
