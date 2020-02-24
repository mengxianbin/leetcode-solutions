package nowcoder.offer.StringMatchPattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {

    Solution solution = new Solution();

    void test(boolean expected, String str, String pattern) {
        boolean actual = solution.match(str.toCharArray(), pattern.toCharArray());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test1() {
        test(true, "\",", ".*");
    }

    @Test
    public void test2() {
        test(true, "", ".*");
    }
}
