//package airbnb;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class PrintTriangle2 {
//  public void print(int n) {
//
//  }
//
//  private void printSpace(int n) {
//    while (n-- > 0) {
//      print(' ');
//    }
//  }
//
//  private void print1(int offset) {
//    print(offset);
//    print(" /\\");
//  }
//
//  private void print2(int offset) {
//    print(offset);
//    System.out.println("/  \\");
//    System.out.print("----");
//  }
//
//  private void print(int n, int offset) {
//    print(n-1, offset);
//  }
//
//  private void printDouble(int n) {
//    int len = 1 << n;
//    int indent = len - 2;
//    int interval = len * 2 - 3;
//
//    for (int i = 1; i <= len; i++) {
//      print1(indent);
//      int count = 1 << i;
//      for (int j = 1; j < count; j++) {
//        print1(interval);
//      }
//      print2(indent);
//      for (int j = 1; j < count; j++) {
//        print2(interval-1);
//      }
//      indent -= 2;
//      interval
//    }
//  }
//
//  private void print(String s) {
//    System.out.print(s);
//  }
//
//  public static void main(String[] args) {
//    PrintTriangle2 in = new PrintTriangle2();
//    in.print(3);
//  }
//}
