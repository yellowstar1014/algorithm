package leetcode;

import java.util.*;

public class A218SkyLine {
  public List<List<Integer>> getSkyline(int[][] buildings) {
    int N = buildings.length;
    List<int[]> es = new ArrayList<>();

    List<List<Integer>> ret = new ArrayList<>();

    for (int[] b : buildings) {
      es.add(new int[]{b[0],b[2],0});
      es.add(new int[]{b[1],b[2],1});
    }

    Collections.sort(es, (a, b) -> a[0]!= b[0]?a[0]-b[0]:
      a[2]!= b[2]?a[2]-b[2]:a[2] == 0?b[1]-a[1]:a[1]-b[1]);

    TreeMap<Integer, Integer> map = new TreeMap<>((a, b) -> b-a);
    map.put(0, 1);
    int pre = 0;

    for (int[] e : es) {
      if (e[2] == 0) {
        map.put(e[1], map.getOrDefault(e[1], 0) + 1);
      } else {
        int v = map.get(e[1]) - 1;
        if (v == 0) {
          map.remove(e[1]);
        } else {
          map.put(e[1], v);
        }
      }
      int h = map.firstKey();
      if (h != pre) {
        ret.add(new ArrayList<>(Arrays.asList(e[0], h)));
        pre = h;
      }
    }

    return ret;
  }

  public static void main(String[] args) {
    A218SkyLine in = new A218SkyLine();
    List<List<Integer>> ret = in.getSkyline(new int[][]{{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}});
  }
}
