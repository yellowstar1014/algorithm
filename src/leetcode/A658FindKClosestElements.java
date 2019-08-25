package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class A658FindKClosestElements {
  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    int l = -1, r = -1;
    int j = Arrays.binarySearch(arr, x);
    if (j >= 0) {
      l = j;
      r = j+1;
    } else {
      j = -(j+1);
      l = j-1;
      r = j;
    }
    LinkedList<Integer> ret = new LinkedList<>();
    while ((l >= 0 || r < arr.length) && k-- > 0) {
      if (r == arr.length) {
        ret.addFirst(arr[l--]);
      } else if (l < 0) {
        ret.addLast(arr[r++]);
      } else if (x - arr[l] <= arr[r] - x) {
        ret.addFirst(arr[l--]);
      } else {
        ret.addLast(arr[r++]);
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    A658FindKClosestElements in = new A658FindKClosestElements();
    in.findClosestElements(new int[]{1}, 1, 1);
  }
}
