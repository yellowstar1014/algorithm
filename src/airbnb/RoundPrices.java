package airbnb;

import java.util.Arrays;
import java.util.Random;

public class RoundPrices {
  static class Number {
    int in;
    double frac;
    int index;

    Number(double val, int index) {
      this.in = (int)val;
      this.frac = val - in;
      this.index = index;
    }
  }
  // closest subset sum
  public static int[] roundUp(double[] prices) {
      double sum = 0, floorSum = 0;
      Number[] numbers = new Number[prices.length];
      for (int i = 0; i < prices.length; i++) {
        sum += prices[i];
        floorSum += (int)prices[i];
        numbers[i] = new Number(prices[i], i);
      }

      Arrays.sort(numbers, (a, b) -> Double.compare(b.frac, a.frac));

      int[] ret = new int[prices.length];
      int i = 0;

      int diff = (int)Math.round(sum - floorSum);

      for (; i < diff; i++) {
        ret[numbers[i].index] = numbers[i].in + 1;
      }

      for (; i < numbers.length; i++) {
        ret[numbers[i].index] = numbers[i].in;
      }

      return ret;
  }

  public static void main(String args[])
  {
    double[] input = new double[100];
    StringBuilder sb = new StringBuilder();
    Random rand = new Random();
    for (int i = 0; i < 100; i++) {
      int num1 = rand.nextInt(100);
      double num2 = rand.nextDouble();
      double num = (double) num1 + num2;
      input[i] = num;
      sb.append(num).append(",");
    }
    System.out.println(sb.toString());
    sb.setLength(0);
    int[] result = roundUp(input);
    for (int i = 0; i < result.length; i++) {
      sb.append(result[i]).append(",");
    }
    System.out.println(sb.toString());
  }
}
