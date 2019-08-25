package leetcode;

import java.util.*;

public class A939MinimumAreaRectangle {
  public int minAreaRect(int[][] points) {
    Set<Long> set = new HashSet<>();
    Map<Integer, List<Integer>> cols = new HashMap<>();
    Map<Integer, List<Integer>> rows = new HashMap<>();
    set.add(getKey(points[0]));
    set.add(getKey(points[1]));
    set.add(getKey(points[2]));

    addToCol(cols, points[0]);
    addToCol(cols, points[1]);
    addToCol(cols, points[2]);

    addToRow(rows, points[0]);
    addToRow(rows, points[1]);
    addToRow(rows, points[2]);

    int ret = Integer.MAX_VALUE;

    for (int i = 3; i < points.length; i++) {
      int x = points[i][0];
      int y = points[i][1];
      List<Integer> cs = rows.get(x);
      List<Integer> rs = cols.get(y);
      if (cs != null && rs != null) {
        for (int c : cs) {
          for (int r : rs) {
            if (set.contains(getKey(r, c))) {
              ret = Math.min(ret, Math.abs((x - r) * (y - c)));
            }
          }
        }
      }
      set.add(getKey(points[i]));
      addToCol(cols, points[i]);
      addToRow(rows, points[i]);
    }
    return ret;
  }

  private long getKey(int x, int y) {
    return ((long)x) << 32 | (long)y;
  }

  private long getKey(int[] point) {
    return getKey(point[0], point[1]);
  }

  private void addToCol(Map<Integer, List<Integer>> cols, int[] point) {
    List<Integer> list = cols.computeIfAbsent(point[1], k -> new ArrayList<>());
    list.add(point[0]);
  }

  private void addToRow(Map<Integer, List<Integer>> rows, int[] point) {
    List<Integer> list = rows.computeIfAbsent(point[0], k -> new ArrayList<>());
    list.add(point[1]);
  }

  public static void main(String[] args) {
    A939MinimumAreaRectangle o = new A939MinimumAreaRectangle();
    int r = o.minAreaRect(new int[][]{{1,1},{1,3},{3,1},{3,3},{4,1},{4,3}});
    System.out.println(r);
  }
}
