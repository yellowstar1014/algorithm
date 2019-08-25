package leetcode.dp;

class A1012NumbersWithRepeatedDigits {
  // F => N - uniques

  // 0,1,2,...,9
  // 10 12 13 14...  20 21 23..     10 20 30...    01 21 31...
  // 210   213 214...120 123
  //
  // f(i) = f(i-1)*(10-(i-1))
  //
  // N = abcdefg (a-1)000000
  // f = (<a)f(6) + (a)(<b)f(5) + ... + ...(<f)f(1)

  // f(i,j,1) = (j-1)*f(i-1,j-1,0) = f(i,j,0) - f(i-1,j-1,0)
  // f(i,j,0) = (j-1)*f(i-1,j-1,0) + f(i-1,j,0)

  // f(i,j) = 0 (i > j)
  // f(1,j) = j

  // N = abcdefg
  // f = (a-1)f(n-1, 10-1, 0) + (b-2)f(n-2,10-2, 0) + ... + (f-6)f(n-6, 10-6, 0)

  // 9*9*8 = 81*8 = 648
  // 648 + 90 = 738
  // f(3,10) = 9*f(2,9) + f(2,10)
  //         = 9*(9*8) + (10*9)

  // f(i,j,0) = (j-1)(f(i-1,j-1,0) + f(i-2,j-2,1))
  // f(i,j,1) = j*f(i-1,j-1,1)

  // g(i,j) = j*g(i-1,j-1)
  // f(i,j) = (j-1)*g(i-1,j-1)
  // F(i,j) = f(i,j) + f(i-1, j) + f(1,j)


  public static int numDupDigitsAtMostN(int N) {
    char[] cs = String.valueOf(N).toCharArray();
    int len = cs.length;
    // 1 1 1 1 1 1 1 1 1 1 1
    //   1 2 3 4 5 6 7 8 9 10
    //int[][] f = new int[len+1][11];
    int[][] g = new int[len+1][11];

    for (int j = 0; j <= 10; j++) {
      //f[0][j] = 1;
      g[0][j] = 1;
    }

    for (int i = 1; i < len; i++) {
      for (int j = i; j <= 10; j++) {
        g[i][j] = j*g[i-1][j-1];
        //f[i][j] = (j-1)*g[i-1][j-1];
      }
    }

    boolean[] used = new boolean[10];

    int r = 0;
    for (int i = 0; i < cs.length; i++) { // i=0
      int v = cs[i] - '0'; //v=2

      int num = getAvailable(v, used, i == cs.length-1); // num=2

      if (num > 0) {
        if (i == 0) r += (num-1) * g[len-(i+1)][10-(i+1)];
        else r += num * g[len-(i+1)][10-(i+1)];
      }
      if (used[v]) break;
      else used[v]=true;
    }

    for(int i = 1; i < len; i++) {
      //r += f[i][10];
      r += 9*g[i-1][9];
    }

    return N-r;
  }

  private static int getAvailable(int v, boolean[] used, boolean isLast) {
    int ret = 0;
    if (isLast) v++;
    for (int i = 0; i < v; i++) {
      if (!used[i]) {
        ret++;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    int r= A1012NumbersWithRepeatedDigits.numDupDigitsAtMostN(1000);
    System.out.println(r);
  }
}
