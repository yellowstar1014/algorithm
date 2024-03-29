package leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class A659SplitArrayConsecutiveSubsequences {
  public boolean isPossible(int[] nums) {
    Map<Integer, Integer> map = new LinkedHashMap<>();
    Map<Integer, Integer> tail = new HashMap<>();
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }
    /*
      82,
      83,
      84,
      85,85,
      86,86,86,
      87,87,87,
      88,88,88});
     */
    for (int num : map.keySet()) {
      int cur = map.get(num);
      if (cur == 0) continue;
      int pre = tail.getOrDefault(num-1, 0);
      if (pre >= cur) {
        tail.put(num, tail.getOrDefault(num, 0) + cur);
        tail.put(num-1, pre-cur);
      } else {
        tail.put(num, pre);
        tail.put(num-1, 0);
        int c1 = map.getOrDefault(num+1, 0) - (cur-pre);
        int c2 = map.getOrDefault(num+2, 0) - (cur-pre);
        if (c1 < 0 || c2 < 0) {
          return false;
        }
        map.put(num+1, c1);
        map.put(num+2, c2);
        tail.put(num+2, cur-pre);
      }
      map.put(num, 0);
    }
    return true;
  }
  /*
  [9,10,11,12,13,14,29,30,31,32,33,34,35,36,37,38,39,40,41,41,42,42,43,44,45,46,47,47,48,48,49,49,50,50,51,51,51,52,52,52,53,53,53,54,54,54,55,55,55,56,56,56,57,57,57,58,58,58,59,59,59,59,60,60,60,60,61,61,61,61,62,62,62,62,63,63,63,63,64,64,64,64,65,65,65,65,66,66,66,66,67,67,67,67,68,68,68,68,69,69,69,69,70,70,70,70,71,71,71,71,72,72,72,72,73,73,73,73,74,74,74,74,75,75,75,75,76,76,76,76,76,77,77,77,77,77,78,78,78,78,78,79,79,79,79,80,80,80,80,81,81,81,81,82,82,82,82,83,83,83,83,84,84,84,84,85,85,85,85,85,86,86,86,86,86,86,87,87,87,87,87,87,88,88,88,88,88,88,89,89,89,89,89,89,90,90,90,90,90,90,91,91,91,91,91,91,92,92,92,92,92,92,93,93,93,93,93,93,94,94,94,94,94,94,95,95,95,95,95,95,96,96,96,96,96,96,96,97,97,97,97,97,97,97,98,98,98,98,98,98,98,99,99,99,99,99,99,99,100,100,100,100,100,100,100,101,101,101,101,101,101,101,102,102,102,102,102,102,102,103,103,103,103,103,103,103,104,104,104,104,104,104,104,105,105,105,105,105,105,106,106,106,106,106,106,107,107,107,107,107,107,108,108,108,108,108,108,109,109,109,109,109,109,110,110,110,110,110,111,111,111,111,111,112,112,112,112,112,113,113,113,113,113,114,114,114,114,114,115,115,115,115,115,116,116,116,116,116,117,117,117,117,118,118,118,118,119,119,119,119,120,120,120,120,121,121,121,122,122,122,123,123,123,124,124,124,125,125,125,126,126,126,127,127,127,128,128,128,129,129,129,130,130,130,131,131,132,132,133,133,134,134,135,135,136,136,137,137,138,138,139,139,140,140,141,141,142,142,143,143,144,144,145,145,146,146,147]
   */
  public static void main(String[] args) {
    A659SplitArrayConsecutiveSubsequences in = new A659SplitArrayConsecutiveSubsequences();
//    in.isPossible(new int[]{
//      82,
//      83,
//      84,
//      85,85,
//      86,86,86,
//      87,87,87,
//      88,88,88});
    in.isPossible(new int[]{1,2,3,3,4,4,5,5});
  }
}
