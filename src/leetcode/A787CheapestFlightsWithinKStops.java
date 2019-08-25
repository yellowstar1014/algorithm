package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class A787CheapestFlightsWithinKStops {
  class Point {
    int cost;
    int city;
    int stops;
    Point prev;

    Point (int cost, int city, int stops, Point prev) {
      this.cost = cost;
      this.city = city;
      this.stops = stops;
      this.prev = prev;
    }
  }

  public String findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
    boolean[] visited = new boolean[n];
    for (int[] f : flights) {
      prices.computeIfAbsent(f[0], key->new HashMap<>()).put(f[1], f[2]);
    }
    Queue<Point> q = new PriorityQueue<>((a,b) -> a.cost-b.cost);
    q.offer(new Point(0, src, k+1, null));
    while (!q.isEmpty()) {
      Point cur = q.poll();
      visited[cur.city] = true;
      if (cur.city == dst) {
        return createResult(cur);
      }
      if (cur.stops > 0) {
        Map<Integer, Integer> map = prices.getOrDefault(cur.city, new HashMap<>());
        for (Integer key : map.keySet()) {
          if (visited[key]) continue;
          q.offer(new Point(cur.cost + map.get(key), key, cur.stops-1, cur));
        }
      }
    }
    return "";
  }

  private String createResult(Point cur) {
    StringBuilder sb = new StringBuilder();
    while (cur != null) {
      sb.append(cur.city);
      sb.append(" ");
      cur = cur.prev;
    }
    return sb.reverse().toString();
  }

  public static void main(String[] args) {
    A787CheapestFlightsWithinKStops in = new A787CheapestFlightsWithinKStops();
    String ret = in.findCheapestPrice(3, new int[][]{{0,1,100},{1,2,100},{0,2,500}}, 0, 2 ,1);
    System.out.println(ret);
  }
}
