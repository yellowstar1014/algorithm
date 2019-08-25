package leetcode;

public class A774MinimizeMaxDistance2GasStation {
  public double minmaxGasDist(int[] stations, int K) {
    double lo = 0, hi = 1e8;
    while (hi-lo > 1e-6) {
      double mi = (lo+hi) / 2.0;
      if (possible(mi, stations, K)) {
        hi = mi;
      } else {
        lo = mi;
      }
    }
    return lo;
  }

  private boolean possible(double d, int[] stations, int K) {
    int c = 0;
    for (int i = 1; i < stations.length; i++) {
      c += (int)((stations[i] - stations[i-1]) / d);
    }
    return c <= K;
  }

  public static void main(String[] args) {
    A774MinimizeMaxDistance2GasStation in = new A774MinimizeMaxDistance2GasStation();
    in.minmaxGasDist(new int[]{1,2,3,4,5,6,7,8,9,10},9);
  }
}
