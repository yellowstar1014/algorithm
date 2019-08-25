package leetcode.dp;

/**
 * Your music player contains N different songs and she wants to listen to L (not necessarily different) songs during
 * your trip.  You create a playlist so that:

 Every song is played at least once
 A song can only be played again only if K other songs have been played
 Return the number of possible playlists.  As the answer can be very large, return it modulo 10^9 + 7.



 Example 1:

 Input: N = 3, L = 3, K = 1
 Output: 6
 Explanation: There are 6 possible playlists. [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1].
 Example 2:

 Input: N = 2, L = 3, K = 0
 Output: 6
 Explanation: There are 6 possible playlists. [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 2, 1], [2, 1, 2], [1, 2, 2]
 Example 3:

 Input: N = 2, L = 3, K = 1
 Output: 2
 Explanation: There are 2 possible playlists. [1, 2, 1], [2, 1, 2]


 Note:

 0 <= K < N <= L <= 100
 */

public class A920NumberOfMusicPlaylists {
  // f(i,j) = f(i-1,j-1)*(N-j+1) + f(i-1,j)*(j-K)  j >= K i >= j
  // f(i,0) = 0;
  // f(0,j) = 0;
  // f(0,0) = 1;
  int M = 1000000007;
  public int numMusicPlaylists(int N, int L, int K) {
    int[][] f = new int[L+1][N+1];
    f[0][0] = 1;
    for (int i = 1; i < L+1; i++) {
      for (int j = 1; j <= i && j < N+1; j++) {
        f[i][j] = f[i-1][j-1]*(N-j+1);
        if (j > K) {
          f[i][j] += f[i-1][j]*(j-K);
        }
        f[i][j] %= M;
      }
    }
    return f[L][N];
  }
}
