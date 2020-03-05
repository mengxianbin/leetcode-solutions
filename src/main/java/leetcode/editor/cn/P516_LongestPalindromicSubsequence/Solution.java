//
//Given a string s, find the longest palindromic subsequence's length in s. You 
//may assume that the maximum length of s is 1000.
// 
//
// Example 1: 
//Input: 
// 
//"bbbab"
// 
//Output: 
// 
//4
// 
//One possible longest palindromic subsequence is "bbbb".
// 
//
// Example 2: 
//Input:
// 
//"cbbd"
// 
//Output:
// 
//2
// 
//One possible longest palindromic subsequence is "bb".
// Related Topics 动态规划


package leetcode.editor.cn.P516_LongestPalindromicSubsequence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][s.length() - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class SolutionTest {

    private Solution solution = new Solution();

    private void test(int expected, String input) {
        int actual = solution.longestPalindromeSubseq(input);
        Assertions.assertEquals(expected, actual);
    }

    /* "bbbab" */
    @Test
    public void test1() {
        test(4, "bbbab");
    }

    @Test
    public void test2() {
        test(2, "cbbd");
    }
}
