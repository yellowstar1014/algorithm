package common;

import java.util.TreeMap;

public class Intervals {

  /**
   * Adds an interval [from, to) into an internal structure.
   */
  TreeMap<Integer, Integer> treeMap = new TreeMap<>();
  int covered = 0;

  void addInterval(int from, int to) {
    Integer end = treeMap.ceilingKey(from);
    int original = 0;
    while (end != null) {
      int start = treeMap.get(end);

      if (to < start) break;

      treeMap.remove(end);
      from = Math.min(from, start);
      to = Math.max(to, end);
      original += end - start;

      end = treeMap.ceilingKey(from);
    }
    treeMap.put(to, from);
    covered += to - from - original;
  }

  int getTotalCoveredLength() {
    return covered;
  }

/**
 * Returns a total length covered by the added intervals.
 * If several intervals intersect, the intersection should be counted only once.
 * Example:
 *
 * addInterval(3, 6)
 * addInterval(8, 9)
 * addInterval(1, 5)
 *
 * getTotalCoveredLength() -> 6
 *
 * i.e. [1,5) and [3,6) intersect and give a total covered interval [1,6) with a length of 5.
 * [1,6) and [8,9) don't intersect, so the total covered length is a sum of both intervals, that is 5+1=6.
 *
 * |__|__|__| (3,6)
 * |__| (8,9)
 * |__|__|__|__| (1,5)
 *
 * 0 1 2 3 4 5 6 7 8 9 10
 *
 */
}