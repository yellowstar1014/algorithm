package leetcode;

import java.util.Stack;

public class A844BackspaceStringCompare {
  public static boolean backspaceCompare(String S, String T) {
    int i = S.length() - 1, j = T.length() - 1;
    while (i >= 0 || j >= 0) { // i=2   j=0
      int l1 = 0;
      while (i >= 0 && S.charAt(i) == '#') l1++;
      i -= l1;
      int l2 = 0;
      while (j >= 0 && T.charAt(j) == '#') l2++;
      j -= l2;
      if (i >= 0 && j >= 0) {
        if (S.charAt(i) != T.charAt(j)) return false;
        i--;
        j--;
      }
    }
    return i < 0 && j < 0;
  }

  public static void main(String[] args) {
    A844BackspaceStringCompare.backspaceCompare("a#c","b");


  }

  public String licenseKeyFormatting(String S, int K) {
    Stack<Character> stack = new Stack<>();
    for (int i = S.length() - 1; i >= 0; i--) {
      if (S.charAt(i) != '-') stack.push(S.charAt(i));
      if (stack.size()%(K+1) == K) stack.push('-');
    }
    String ret = "";
    while (!stack.isEmpty()) ret += stack.pop();
    return ret;
  }
}
