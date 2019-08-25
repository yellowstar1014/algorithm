package leetcode;

public class A768MaxChunksToMakeSortedII {
  public int maxChunksToSorted(int[] arr) {
    int N = arr.length;
    int[] max = new int[N], min = new int[N];

    max[0] = arr[0];
    for (int i = 1; i < N; i++) {
      max[i] = Math.max(max[i-1], arr[i-1]);
    }


    min[N-1] = arr[N-1];
    for (int i = N-2; i >= 0; i--) {
      min[i] = Math.min(min[i+1], arr[i+1]);
    }

    int ret = 0;
    for (int i = 0; i < N; i++) {
      if (arr[i] >= max[i] && arr[i] <= min[i]) ret++;
    }
    return ret;
  }

  public static void main(String[] args) {
    A768MaxChunksToMakeSortedII in = new A768MaxChunksToMakeSortedII();
    int r = in.maxChunksToSorted(new int[]{1,2,3});
    System.out.println(r);
  }
}
