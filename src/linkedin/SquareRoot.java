package linkedin;

public class SquareRoot {
  double squareRoot(double v, double precision) {
    int sign = 1;
    if (v < 0) {
      sign = -1;
      v = Math.abs(v);
    }
    double l = 0, r = v, mid = v;
    while (l < r) {
      mid = l + (r-l) / 2;
      if (mid*mid > v) {
        r = mid;
      } else if ((mid*mid <= v && (mid+precision)*(mid+precision) > v) ) {
        break;
      } else {
        l = mid-precision;
      }
    }
    return mid*sign;
  }

  public static void main(String[] args) {
    SquareRoot in = new SquareRoot();
    double r = in.squareRoot(100, 0.001);
    System.out.println(r);
  }
}
