package leetcode;

import java.util.ArrayList;
import java.util.List;

public class A301RemoveInvalidParentheses {
  public String removeInvalidParentheses(String s) {
    List<Integer> index = new ArrayList<>();
    int count = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') count++;
      else if (s.charAt(i) == ')') {
        if (--count < 0) {
          index.add(i);
          count = 0;
        }
      }
    }
    int j = s.length() - 1;
    while (count > 0) {
      if (s.charAt(j) == '(') {
        index.add(j);
        count--;
      }
      j--;
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if (index.contains(i)) continue;
      sb.append(s.charAt(i));
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    A301RemoveInvalidParentheses in = new A301RemoveInvalidParentheses();
    String ret = in.removeInvalidParentheses("(()))sdf");
    System.out.println(ret);
  }
}
