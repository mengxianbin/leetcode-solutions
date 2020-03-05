package leetcode.editor.cn.P5_LongestPalindromicSubstring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {

    private Solution solution = new Solution();

    private void test(String expected, String input) {
        String actual = solution.longestPalindrome(input);
        Assertions.assertEquals(expected, actual);
    }

    /* "babad" */
    @Test
    public void test1() {
        test("bab", "babad");
    }

    @Test
    public void test2() {
        test("bb", "cbbd");
    }
}
