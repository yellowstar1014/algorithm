package uber;

import java.util.ArrayList;
import java.util.List;

public class IntervalIntersectUnion {
  public List<int[]> union(List<int[]> in1, List<int[]> in2) {
    int i = 0, j = 0;
    if (in1.size() == 0) return in2;
    if (in2.size() == 0) return in1;

    List<int[]> ret = new ArrayList<>();
    int[] cur = in1.get(0)[0] < in2.get(0)[0] ? in1.get(i++) : in2.get(j++);

    while (i < in1.size() || j < in2.size()) {
      int[] interval = null;
      if (i == in1.size()) interval = in2.get(j++);
      else if (j == in2.size()) interval = in1.get(i++);
      else if (in1.get(i)[0] < in2.get(j)[0]) {
        interval = in1.get(i++);
      } else {
        interval = in2.get(j++);
      }

      if (interval[0] <= cur[1]) {
        cur[1] = Math.max(cur[1], interval[1]);
      } else {
        ret.add(cur);
        cur = interval;
      }
    }
    ret.add(cur);
    return ret;
  }

  public List<int[]> intersect(List<int[]> in1, List<int[]> in2) {
    int i = 0, j = 0;

    List<int[]> ret = new ArrayList<>();

    while (i < in1.size() && j < in2.size()) {
      int lo = Math.max(in1.get(i)[0], in2.get(j)[0]);
      int hi = Math.min(in1.get(i)[1], in2.get(j)[1]);

      if(lo <= hi) {
        ret.add(new int[]{lo, hi});
      }

      if (in1.get(i)[1] < in2.get(j)[1]) {
        i++;
      } else {
        j++;
      }
    }

    return ret;
  }
}
