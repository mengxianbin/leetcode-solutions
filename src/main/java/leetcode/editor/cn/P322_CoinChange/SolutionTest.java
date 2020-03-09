package leetcode.editor.cn.P322_CoinChange;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {
    private Solution solution = new Solution();

    private void test(int expected, int[] coins, int amount) {
        int actual = solution.coinChange(coins, amount);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test1() {
        int[] coins = {1, 2, 5};
        test(3, coins, 11);
    }

    @Test
    public void test2() {
        int[] coins = {1};
        test(0, coins, 0);
    }

    @Test
    public void test3() {
        int[] coins = {1, 2147483647};
        test(2, coins, 2);
    }

    @Test
    public void test4() {
        int[] coins = {186, 419, 83, 408};
        test(20, coins, 6249);
    }
}
