//Given a string s, find the longest palindromic substring in s. You may assume 
//that the maximum length of s is 1000. 
//
// Example 1: 
//
// 
//Input: "babad"
//Output: "bab"
//Note: "aba" is also a valid answer.
// 
//
// Example 2: 
//
// 
//Input: "cbbd"
//Output: "bb"
// 
// Related Topics 字符串 动态规划


package leetcode.editor.cn.P5_LongestPalindromicSubstring;

//leetcode submit region begin(Prohibit modification and deletion)

class Solution1 {
    private boolean isPalindrome(String s, int start, int end, int[][] cache) {
        if (start < 0 || end > s.length() - 1 || start > end) {
            return false;
        }

        if (end - start < 3) {
            return s.charAt(start) == s.charAt(end);
        }

        if (cache[start][end] != 0) {
            return cache[start][end] > 0;
        }

        boolean result = s.charAt(start) == s.charAt(end) && isPalindrome(s, start + 1, end - 1, cache);
        cache[start][end] = result ? 1 : -1;

        return result;
    }

    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }

        int[][] cache = new int[s.length()][s.length()];
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = s.length() - 1; j >= i; j--) {
                if (isPalindrome(s, i, j, cache)) {
                    if (j - i > end - start) {
                        start = i;
                        end = j;
                    }
                    break;
                }
            }
        }

        return s.substring(start, end + 1);
    }
}

class Solution2 {

    private void extend(String s, int start, int end, int[] pair) {
        int i = start;
        int j = end;

        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }

        i++;
        j--;

        if (j - i > pair[1] - pair[0]) {
            pair[0] = i;
            pair[1] = j;
        }
    }

    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }

        int[] startEndPair = {0, 0};
        for (int i = 0; i < s.length() - 1; i++) {
            extend(s, i, i, startEndPair);
            extend(s, i, i + 1, startEndPair);
        }

        return s.substring(startEndPair[0], startEndPair[1] + 1);
    }
}

class Solution {
    private Solution1 solution = new Solution1();

    public String longestPalindrome(String s) {
        return solution.longestPalindrome(s);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


