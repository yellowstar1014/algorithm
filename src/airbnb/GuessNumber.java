package airbnb;

import java.util.*;

public class GuessNumber {
  String target = "6543";
  int N = 4;
  public int[] guessServer(String guess) {
    int bulls = 0, cows = 0;
    int[] d = new int[7];
    for (int i = 0; i < guess.length(); i++) {
      if (guess.charAt(i) == target.charAt(i)) bulls++;
      d[guess.charAt(i)-'0']--;
      d[target.charAt(i)-'0']++;
    }

    int remain = 0;
    for (int v : d) {
      if (v > 0) remain += v;
    }

    cows = guess.length() - remain;

    return new int[]{bulls, cows-bulls};
  }

  private int[] digit = {1,2,3,4,5,6};

  // 1234
  // 1111 -> 10  -> 1
  // 2222 -> 10  -> 2
  // 3333 -> 10  -> 3
  // 4444 -> 21  -> 4

  private String[] cands = new String[]{"1111", "2222", "3333", "4444", "5555", "6666"};

  private boolean better(int bulls, int guessed, int N) {
    return N * 5 < (1 << (N-1)) + guessed;
  }

  public int guess() {
    int N = 4;
    int[] guessed = new int[N];
    int i = 0;

    int count = 0;

    for (int j = 0; j < cands.length; j++) {
      int[] ret = guessServer(cands[j]);
      count++;
      if (ret[0] == N) return count;
      int total = ret[0] + ret[1];
      while (i < N && total-- > 0) {
        guessed[i++] = (j+1);
      }
      if (i == N) break;
    }

    while (i < N) {
      guessed[i++] = 6;
    }

    List<String> pers = new ArrayList<>();
    permutation(guessed, new HashSet<>(), new ArrayList<>(), pers);

    for (String per : pers) {
      System.out.println(per);
      int[] ret = guessServer(per);
      count++;
      if (ret[0] == N) break;
    }

    return count;
  }

  private void permutation(int[] digit, Set<Integer> used, List<Integer> sol, List<String> pers) {
    if (sol.size() == digit.length) {
      StringBuilder sb = new StringBuilder();
      for (int d : sol) {
        sb.append(d);
      }
      pers.add(sb.toString());
      return;
    }

    Set<Integer> seen = new HashSet<>();
    for (int i = 0; i < digit.length; i++) {
      if (seen.contains(digit[i]) || used.contains(i)) continue;
      seen.add(digit[i]);
      used.add(i);
      sol.add(digit[i]);
      permutation(digit, used, sol, pers);
      sol.remove(sol.size()-1);
      used.remove(i);
    }
  }

  public static void main(String[] args) {
    GuessNumber in = new GuessNumber();
    int ret = in.guess();
    System.out.println("count=" + ret);
  }
}
