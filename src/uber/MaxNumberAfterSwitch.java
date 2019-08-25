package uber;

import java.util.ArrayList;
import java.util.List;

public class MaxNumberAfterSwitch {
  public int maxNumber(int num) {
    int n = num;
    List<Integer> ds = new ArrayList<>();
    while (n > 0) {
      ds.add(n%10);
      n /= 10;
    }

    if (ds.size() == 1) return num;

    int[] ret = new int[ds.size()];
    int max = 0;

    for (int i = 1; i < ds.size(); i++) {
      ret[i] = max;
      if (ds.get(i) > ds.get(max)) {
        max = i;
      }
    }

    boolean hasSame = false;

    for (int i = ds.size() - 1; i > 0; i--) {
      if (ds.get(ret[i]) > ds.get(i)) {
        swap(ds, ret[i], i);
        return getNumber(ds);
      } else if (ds.get(ret[i]).equals(ds.get(i))) {
        hasSame = true;
      }
    }

    if (hasSame) return num;
    else {
      swap(ds, ret[1], 1);
      return getNumber(ds);
    }
  }

  private void swap(List<Integer> ds, int i, int j) {
    int tmp = ds.get(i);
    ds.set(i, ds.get(j));
    ds.set(j, tmp);
  }

  private int getNumber(List<Integer> ds) {
    int num = 0;
    for (int i = ds.size()-1; i >= 0; i--) {
      num = num*10 + ds.get(i);
    }
    return num;
  }

  public static void main(String[] args) {
    MaxNumberAfterSwitch in = new MaxNumberAfterSwitch();
    int ret = in.maxNumber(988768);
    System.out.println(ret);
  }
}
