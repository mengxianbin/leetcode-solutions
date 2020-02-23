package nowcoder.offer.FindGreatestSumOfSubArray;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {

    Solution solution = new Solution();

    void test(int expected, int[] array) {
        int actual = solution.FindGreatestSumOfSubArray(array);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test1() {
        test(18, new int[]{1, -2, 3, 10, -4, 7, 2, -5});
    }
}
