//Given two strings text1 and text2, return the length of their longest common s
//ubsequence. 
//
// A subsequence of a string is a new string generated from the original string 
//with some characters(can be none) deleted without changing the relative order of
// the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is
// not). A common subsequence of two strings is a subsequence that is common to bo
//th strings. 
//
// 
//
// If there is no common subsequence, return 0. 
//
// 
// Example 1: 
//
// 
//Input: text1 = "abcde", text2 = "ace" 
//Output: 3  
//Explanation: The longest common subsequence is "ace" and its length is 3.
// 
//
// Example 2: 
//
// 
//Input: text1 = "abc", text2 = "abc"
//Output: 3
//Explanation: The longest common subsequence is "abc" and its length is 3.
// 
//
// Example 3: 
//
// 
//Input: text1 = "abc", text2 = "def"
//Output: 0
//Explanation: There is no such common subsequence, so the result is 0.
// 
//
// 
// Constraints: 
//
// 
// 1 <= text1.length <= 1000 
// 1 <= text2.length <= 1000 
// The input strings consist of lowercase English characters only. 
// 
// Related Topics 动态规划


package leetcode.editor.cn.P1143_LongestCommonSubsequence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//leetcode submit region begin(Prohibit modification and deletion)

class Solution1 {
    private int solve(String text1, String text2, int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }

        if (text1.charAt(i) == text2.charAt(j)) {
            return 1 + solve(text1, text2, i - 1, j - 1);
        }

        return Math.max(solve(text1, text2, i - 1, j), solve(text1, text2, i, j - 1));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }

        return solve(text1, text2, text1.length() - 1, text2.length() - 1);
    }
}

class Solution2 {
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }

        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }
}

class Solution {

    public int longestCommonSubsequence(String text1, String text2) {
        return new Solution2().longestCommonSubsequence(text1, text2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class SolutionTest {

    private Solution solution = new Solution();

    private void test(int expected, String text1, String text2) {
        int actual = solution.longestCommonSubsequence(text1, text2);
        Assertions.assertEquals(expected, actual);
    }

    /* "abcde"
"ace" */
    @Test
    public void test1() {
        test(3, "abcde", "ace");
    }

    @Test
    public void test2() {
        test(3, "abc", "abc");
    }

    @Test
    public void test3() {
        test(0, "abc", "def");
    }
}
