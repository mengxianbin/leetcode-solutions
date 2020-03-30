package leetcode.editor.cn.PInterview_01_06_CompressString;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {

    private void test(String expected, String input) {
        String actual = new Solution1().compressString(input);
        Assertions.assertEquals(expected, actual);
    }

    /* "aabcccccaa" */
    @Test
    public void test1() {
        test("a2b1c5a3", "aabcccccaaa");
    }

    @Test
    public void test2() {
        test("abbccd", "abbccd");
    }
}
