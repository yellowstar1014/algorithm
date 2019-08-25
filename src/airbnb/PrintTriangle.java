package airbnb;

import java.util.ArrayList;
import java.util.List;

public class PrintTriangle {
  public void print(int n) {
    List<String> ret = printSingle();
    int len = 1;
    for (int i = 2; i <= n; i++) {
      len <<= 1;
      List<String> tmp = new ArrayList<>(printOffsetAhead(ret, len));
      int offset = len-1;
      for (int j = 0; j < ret.size(); j++) {
        String newLine = printOffsetBetween(ret.get(j), ret.get(j), offset);
        tmp.add(newLine);
        if (j%3 != 1) {
          offset--;
        }
      }
      ret = tmp;
    }

    for (String s : ret) {
      System.out.println(s);
    }
  }

  private List<String> printOffsetAhead(List<String> original, int offset) {
    List<String> ret = new ArrayList<>();
    for (String s : original) {
      ret.add(printOffsetAhead(s, offset));
    }
    return ret;
  }

  private String printOffsetAhead(String s, int offset) {
    StringBuilder sb = new StringBuilder();
    while (offset-- > 0) {
      sb.append(' ');
    }
    sb.append(s);
    return sb.toString();
  }


  private String printOffsetBetween(String head, String tail, int offset) {
    StringBuilder sb = new StringBuilder(head);
    while (offset-- > 0) {
      sb.append(' ');
    }
    sb.append(tail);
    return sb.toString();
  }

  private List<String> printSingle() {
    List<String> lines = new ArrayList<>();
    lines.add(" /\\");
    lines.add("/  \\");
    lines.add("----");
    return lines;
  }

  public static void main(String[] args) {
    PrintTriangle in = new PrintTriangle();
    in.print(3);
  }
}
