package leetcode;

public class A245ShortestWordDistanceIII {
  /**
   * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
   * <p>
   * word1 and word2 may be the same and they represent two individual words in the list.
   * <p>
   * Example:
   * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
   * <p>
   * Input: word1 = “makes”, word2 = “coding”
   * Output: 1
   * Input: word1 = "makes", word2 = "makes"
   * Output: 3
   * Note:
   * You may assume word1 and word2 are both in the list.
   */
  public int shortestWordDistance(String[] words, String word1, String word2) {
    long dist = Integer.MAX_VALUE, i1 = Integer.MIN_VALUE, i2 = Integer.MAX_VALUE;
    boolean isSame = word1.equals(word2);
    for (int i = 0; i < words.length; i++) {
      if (isSame) {
        if (words[i].equals(word1)) {
          i1 = i2;
          i2 = i;
        } else {
          if (words[i].equals(word1)) {
            i1 = i;
          } else if (words[i].equals(word2)) {
            i2 = i;
          }
        }
        dist = Math.min(dist, Math.abs(i1 - i2));
      }
    }
    return (int)dist;
  }
}
