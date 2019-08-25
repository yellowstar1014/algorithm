package leetcode.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A982TriplesBitwiseZero {
  public static int countTriplets(int[] A) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < A.length; i++) {
      for (int j = 0; j < A.length; j++) {
        map.put(A[i]&A[j], map.getOrDefault(A[i]&A[j], 0) + 1);
      }
    }

    int ret = 0;
    for (int a : A) {
      List<Integer> list = new ArrayList<>();
      list.add(0);
      for (int i = 0; i <= 16; i++) {
        int d = (a >>> i) & 1;
        if (d == 1) continue;
        int s = list.size();
        for (int j = 0; j < s; j++) {
          list.add(list.get(j) + (1 << i));
        }
      }
      for (int v : list) {
        if (map.containsKey(v)) {
          ret += map.get(v);
        }
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    int ret = A982TriplesBitwiseZero.countTriplets(new int[]{2,1,3});
    System.out.println(ret);
  }
}
