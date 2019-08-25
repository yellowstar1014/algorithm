package leetcode;

public class A394DecodeString {
  public String decodeString(String s) {
    return parse(s, 0);
  }
  // 3[a]2[bc]
  private String parse(String s, int i) {
    StringBuilder sb = new StringBuilder();
    i = getChars(s, i, sb);
    if (i == s.length()) return sb.toString();
    int j = i; // j=0 i=0
    i = getCount(s, i); // i=1
    int c = Integer.valueOf(s.substring(j, i)); //c=3
    j = ++i; //j=2
    i = getSubExp(s, i); //i=3
    duplicate(parse(s.substring(j,i), 0), c, sb);
    sb.append(parse(s, ++i));
    return sb.toString();
  }

  private int getChars(String s, int i, StringBuilder sb) {
    while (s.charAt(i) < '0' && s.charAt(i) > '9') {
      sb.append(s.charAt(i++));
    }
    return i;
  }

  private int getCount(String s, int i) {
    while (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
      i++;
    }
    return i;
  }

  private int getSubExp(String s, int i) {
    while (s.charAt(i) != ']') i++;
    return i;
  }

  private void duplicate(String s, int count, StringBuilder sb) {
    while (count-- > 0) {
      sb.append(s);
    }
  }

  public static void main(String[] args) {
    A394DecodeString in = new A394DecodeString();
    in.decodeString("3[a]2[bc]");
  }
}
