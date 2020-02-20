//You are given coins of different denominations and a total amount of money amo
//unt. Write a function to compute the fewest number of coins that you need to mak
//e up that amount. If that amount of money cannot be made up by any combination o
//f the coins, return -1. 
//
// Example 1: 
//
// 
//Input: coins = [1, 2, 5], amount = 11
//Output: 3 
//Explanation: 11 = 5 + 5 + 1 
//
// Example 2: 
//
// 
//Input: coins = [2], amount = 3
//Output: -1
// 
//
// Note: 
//You may assume that you have an infinite number of each kind of coin. 
// Related Topics 动态规划


package leetcode.editor.cn.P322_CoinChange;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SolutionTest {
    private Solver solver = new Solution1();

    private void test(Object expected, Object... args) {
        Object actual = solver.solve(args);
        Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void test1() {
        int[] coins = {1, 2, 5};
        test(3, coins, 11);
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
interface Solver {
    Object solve(Object... args);
}

class Solution1 implements Solver {
    static Solver solver = new Solution1();

    public Object solve(Object... args) {
        int[] coins = (int[])args[0];
        int amount = (int) args[1];

        int[] money = new int[amount + 1];
        Arrays.fill(money, -1);
        money[0] = 0;

        for (int i = 1; i < money.length; i++) {
            for (int coin : coins) {
                int preIndex = i - coin;
                if (preIndex >= 0 && money[preIndex] != -1) {
                    if (money[i] == -1 || money[i] > money[preIndex] + 1) {
                        money[i] = money[preIndex] + 1;
                    }
                }
            }
        }

        return money[amount];
    }
}

class Solution {
    public int coinChange(int[] coins, int amount) {
        return (int)Solution1.solver.solve(coins, amount);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

