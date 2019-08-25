package leetcode.dp;

import java.util.Arrays;
import java.util.Stack;

public class A975OddEvenJump {
  public static int oddEvenJumps(int[] A) {
    int[] f = new int[A.length];
    int[] g = new int[A.length];
    // {2,3,1,1,4}
    int len = A.length;
    f[len-1] = 1;
    g[len-1] = 1;
    // f
    // 0 1 0 1 1
    // g
    // 0 0 1 0 1

    int ret = 1;

    Integer[] B = new Integer[len];
    for (int i = 0; i < len; i++) {
      B[i] = i;
    }

    Arrays.sort(B, (a,b)->A[a]-B[b]);

    for (int i = len-2; i >= 0; i--) { // i=3
      int pre = f1(A, i); // pre=
      if (pre >= 0) {
        f[i] = g[pre];
      }
      pre = f2(A,i);
      if (pre >= 0) {
        g[i] = f[pre];
      }
      ret += f[i];
    }
    return ret;
  }

  // i=3
  private static int f1(int[] A, int i) {
    int j=i, min=Integer.MAX_VALUE, ret=-1;
    while (++j < A.length) {
      if (A[j] >= A[i] && A[j] < min) {
        min = A[j];
        ret = j;
      }
    }
    return ret;
  }

  private static int f2(int[] A, int i) {
    int j=i, max=Integer.MIN_VALUE, ret=-1;
    while (++j < A.length) {
      if (A[j] <= A[i] && A[j] > max) {
        max = A[j];
        ret = j;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    int ret = A975OddEvenJump.oddEvenJumps(new int[]{2,3,1,1,4});
    int ret1 = A975OddEvenJump.oddEvenJumps2(new int[]{2,3,1,1,4});
    System.out.println(ret);
    System.out.println(ret1);
  }

  // 2 3 1 1 5 4
  // 0 1 2 3 4 5
  // 2 3 0 1 5 4
  //
  //
  //
  // 0 1 2 3 4 5
  // 1 5 3 5
  // 1 5 3 5



  public static int oddEvenJumps2(int[] A) {
    int[] f = new int[A.length];
    int[] g = new int[A.length];

    int len = A.length;
    f[len-1] = 1;
    g[len-1] = 1;

    int ret = 1;

    Integer[] B = new Integer[A.length];
    for (int i = 0; i < B.length; i++) {
      B[i] = i;
    }
    Arrays.sort(B, (x,y)->{
      if (A[y] == A[x]) return y-x;
      else return A[y]-A[x];
    });
    int[] n1 = getNext(B);
    Arrays.sort(B, (x,y)->(A[x]-A[y]));
    int[] n2 = getNext(B);

    for (int i = len-2; i >= 0; i--) { // i=3
      if (n1[i] != 0) {
        f[i] = g[n1[i]];
      }
      if (n2[i] != 0) {
        g[i] = f[n2[i]];
      }
      ret += f[i];
    }
    return ret;
  }

  private static int[] getNext(Integer[] B) {
    int[] ret = new int[B.length];
    Stack<Integer> stack = new Stack<>();
    for(int i : B) {
      while (!stack.isEmpty() && i > stack.peek()) {
        stack.pop();
      }
      if (!stack.isEmpty()) ret[i] = stack.peek();
      stack.push(i);
    }
    return ret;
  }
}
