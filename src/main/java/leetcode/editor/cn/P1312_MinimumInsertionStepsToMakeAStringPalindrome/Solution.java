//Given a string s. In one step you can insert any character at any index of the
// string. 
//
// Return the minimum number of steps to make s palindrome. 
//
// A Palindrome String is one that reads the same backward as well as forward. 
//
// 
// Example 1: 
//
// 
//Input: s = "zzazz"
//Output: 0
//Explanation: The string "zzazz" is already palindrome we don't need any insert
//ions.
// 
//
// Example 2: 
//
// 
//Input: s = "mbadm"
//Output: 2
//Explanation: String can be "mbdadbm" or "mdbabdm".
// 
//
// Example 3: 
//
// 
//Input: s = "leetcode"
//Output: 5
//Explanation: Inserting 5 characters the string becomes "leetcodocteel".
// 
//
// Example 4: 
//
// 
//Input: s = "g"
//Output: 0
// 
//
// Example 5: 
//
// 
//Input: s = "no"
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 500 
// All characters of s are lower case English letters. 
// Related Topics 动态规划


package leetcode.editor.cn.P1312_MinimumInsertionStepsToMakeAStringPalindrome;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1 {

    private int minInsert(String s, int left, int right, int[][] dp) {
        int result = 0;
        if (right - left < 1) {
            dp[left][right] = result;
            return result;
        }

        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        if (s.charAt(left) == s.charAt(right)) {
            result = minInsert(s, left + 1, right - 1, dp);
            dp[left][right] = result;
            return result;
        }

        result = Math.min(minInsert(s, left + 1, right, dp), minInsert(s, left, right - 1, dp)) + 1;
        dp[left][right] = result;
        return result;
    }

    public int minInsertions(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                dp[i][j] = -1;
            }
        }

        return minInsert(s, 0, s.length() - 1, dp);
    }
}

class Solution2 {

    public int minInsertions(String s) {
        int[][] dp = new int[s.length()][s.length()];

        for (int dis = 1; dis < s.length(); dis++) {
            for (int i = 0; i + dis < s.length(); i++) {
                int j = i + dis;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                }
                else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }

        return dp[0][s.length() - 1];
    }
}

class Solution3 {

    public int minInsertions(String s) {
        int[][] dp = new int[s.length()][s.length()];

        for (int k = 0; k < s.length(); k++) {
            dp[k][k] = 1;
        }

        for (int dis = 1; dis < s.length(); dis++) {
            for (int i = 0; i + dis < s.length(); i++) {
                int j = i + dis;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }
                else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return s.length() - dp[0][s.length() - 1];
    }
}

class Solution {

    public int minInsertions(String s) {
        return new Solution3().minInsertions(s);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class SolutionTest {

    private Solution solution = new Solution();

    private void test(int expected, String input) {
        int actual = solution.minInsertions(input);
        Assertions.assertEquals(expected, actual);
    }

    /* "zzazz" */
    @Test
    public void test1() {
        test(0, "zzazz");
    }
    @Test
    public void test2() {
        test(2, "mbadm");
    }
    @Test
    public void test3() {
        test(5, "leetcode");
    }
    @Test
    public void test4() {
        test(0, "g");
    }
    @Test
    public void test5() {
        test(1, "no");
    }
}
