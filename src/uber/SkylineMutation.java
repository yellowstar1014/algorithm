package uber;

import java.util.*;

public class SkylineMutation {
  static class Interval {
    int start;
    int end;
    int weight;

    Interval(int start, int end, int weight) {
      this.start = start;
      this.end = end;
      this.weight = weight;
    }
  }

  public List<Interval> getInterval(List<Interval> intervals) {
    List<int[]> es = new ArrayList<>();
    for (Interval interval : intervals) {
      es.add(new int[]{interval.start, interval.weight, 0});
      es.add(new int[]{interval.end, interval.weight, 1});
    }

    Collections.sort(es, (a,b)->a[0]!=b[0] ? a[0]-b[0] : a[2] !=b[2]?a[2]-b[2] : a[2] == 0 ? b[1]-a[1] : a[1] - b[1]);

    List<Interval> ret = new ArrayList<>();

    TreeMap<Integer, Integer> map = new TreeMap<>((a,b) -> b-a);
    map.put(-1, 1);
    int preH = -1;
    int pre = -1;

    for (int[] e : es) {
      if (e[2] == 0) {
        map.put(e[1], map.getOrDefault(e[1],0) + 1);
      } else {
        int v = map.get(e[1]) - 1;
        if (v == 0) {
          map.remove(e[1]);
        } else {
          map.put(e[1], v);
        }
      }

      int h = map.firstKey();
      if (h != preH) {
        if (preH != -1) {
          ret.add(new Interval(pre, e[0], preH));
        }
        preH = h;
        pre = e[0];
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    SkylineMutation in = new SkylineMutation();
    Interval i1 = new Interval(2,9,10);
    Interval i2 = new Interval(3,7,15);
    Interval i3 = new Interval(5,12,12);
    Interval i4 = new Interval(15,20,10);
    Interval i5 = new Interval(19,24,8);

    List<Interval> ret = in.getInterval(new ArrayList<>(Arrays.asList(i1,i2,i3,i4,i5)));
    for (Interval i : ret) {
      System.out.println(i.start + " " + i.end + " " + i.weight);
    }
  }
}
