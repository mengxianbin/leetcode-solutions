package leetcode.editor.cn.P1160_FindWordsThatCanBeFormedByCharacters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {

    private void test(int expected, String[] words, String chars) {
        int actual = new Solution().countCharacters(words, chars);
        Assertions.assertEquals(expected, actual);
    }

    /* ["cat","bt","hat","tree"]
"atach" */
    @Test
    public void test1() {
        String[] words = {"cat", "bt", "hat", "tree"};
        test(6, words, "atach");
    }
}
