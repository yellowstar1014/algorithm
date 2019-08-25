package fb;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class TowArrayProduct {
  public List<int[]> sortByProduct(int[] A, int[] B) {
    List<int[]> ret = new ArrayList<>();
    PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> A[a[0]]*B[a[1]] - A[b[0]]*B[b[1]]);
    for (int i = 0; i < A.length; i++) {
      queue.offer(new int[]{i, 0});
    }

    while(!queue.isEmpty()) {
      int[] cur = queue.poll();
      ret.add(cur);
      if (cur[1]+1 < B.length) {
        queue.offer(new int[]{cur[0], cur[1]+1});
      }
    }

    return ret;
  }

  public static void main(String[] args) {
    TowArrayProduct in = new TowArrayProduct();
    int[] A = new int[]{1,3,3};
    int[] B = new int[]{2,3,4};
    List<int[]> ret = in.sortByProduct(A,B);
    for (int[] e : ret) {
      System.out.println("" + A[e[0]] * B[e[1]] + "[" + e[0] + "," +e[1] + "]");
    }
  }
}
