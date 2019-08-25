package airbnb;

import java.util.*;

public class GuessNumber2 {
  String target = "6543";
  int N = 4;
  public int[] guessServer(String guess) {
    int bulls = 0, cows = guess.length();
    int[] d = new int[7];
    for (int i = 0; i < guess.length(); i++) {
      if (guess.charAt(i) == target.charAt(i)) bulls++;
      d[guess.charAt(i)-'0']--;
      d[target.charAt(i)-'0']++;
    }

    int remain = 0;
    for (int v : d) {
      if (v > 0) cows -= v;
    }

    return new int[]{bulls, cows-bulls};
  }

  // 1234
  // 1111 -> 10  -> 1
  // 2222 -> 10  -> 2
  // 3333 -> 10  -> 3
  // 4444 -> 21  -> 4

  private String[] strs = new String[]{"1111", "2222", "3333", "4444", "5555", "6666"};
  private List<Integer> digit = Arrays.asList(1,2,3,4,5,6);

  public int guess() {

    int count = 0;
    Set<Integer> cands = new HashSet<>(digit);

    for (int i = 0; i < strs.length; i++) {
      int[] ret = guessServer(strs[i]);
      count++;
      if (ret[0] == N) return count;
      if (ret[0]+ret[1] == 0) {
        cands.remove(i+1);
      }
    }

    StringBuilder sb = new StringBuilder();

    while (sb.length() < N) {
      //Set<Integer> tmp = new HashSet<>(cands);
      for (int c : cands) {
        String g = getNumber(c, sb.toString());
        int[] ret = guessServer(g);
        count++;
        if (ret[0] == sb.length()+1) {
          sb.append(c);
          break;
        }
      }
    }

    return count;
  }

  private String getNumber(int c, String pre) {
    String ret = pre + c;
    int i = ret.length();
    while (i++ < N) {
      ret += "0";
    }
    return ret;
  }

  public static void main(String[] args) {
    GuessNumber2 in = new GuessNumber2();
    int ret = in.guess();
    System.out.println("count=" + ret);
  }
}
