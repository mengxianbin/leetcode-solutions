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

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
interface Solver {
    int coinChange(int[] coins, int amount);
}

class Solution1 implements Solver {

    public int coinChange(int[] coins, int amount) {
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

/**
 * 没必要两层循环，大量的重复计算
 */
class Solution2 implements Solver {

    private int solve(int[] coins, int amount, int coinIndex, int answer) {
        System.out.printf("amount=%d, coinIndex=%d, answer=%d.\n", amount, coinIndex, answer);

        int curAnswer = answer;
        for (int i = coinIndex; i >= 0; i--) {
            int coin = coins[i];
            int curCount = amount / coin;
            if (curCount >= curAnswer) {
                System.out.printf("far: amount=%d, coinIndex=%d, count=%d, curAnswer=%d.\n", amount, coinIndex, curCount, curAnswer);
                return curAnswer;
            }

            int remainAmount = amount - coin * curCount;
            if (remainAmount == 0) {
                System.out.printf("final: coin=%d, count=%d, curAnswer=%d.\n", coin, curCount, curAnswer);
                return curCount;
            }

            while (curCount > 0) {
                int remainCount = solve(coins, remainAmount, coinIndex - 1, curAnswer - curCount);
                if (remainCount < curAnswer - curCount) {
                    curAnswer = curCount + remainCount;
                    System.out.printf("while: coin=%d, count=%d, curAnswer=%d.\n", coin, curCount, curAnswer);
                }

                curCount--;
                remainAmount += coin;
            }
        }

        System.out.printf("return: amount=%d, coinIndex=%d, answer=%d, curAnswer=%d.\n", amount, coinIndex, answer, curAnswer);
        return curAnswer;
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }

        Arrays.sort(coins);
        int answer = solve(coins, amount, coins.length - 1, Integer.MAX_VALUE);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}

class Solution5 implements Solver {

    private int solve(int[] coins, int amount, int coinIndex, int answer) {
        System.out.printf("amount=%d, coinIndex=%d, answer=%d.\n", amount, coinIndex, answer);

        int curAnswer = answer;
        int coin = coins[coinIndex];
        int curCount = amount / coin;
        if (curCount >= curAnswer) {
            System.out.printf("far: amount=%d, coinIndex=%d, count=%d, curAnswer=%d.\n", amount, coinIndex, curCount, curAnswer);
            return -1;
        }

        int remainAmount = amount - coin * curCount;
        if (remainAmount == 0) {
            System.out.printf("final: coin=%d, count=%d, curAnswer=%d.\n", coin, curCount, curAnswer);
            return curCount;
        }

        if (coinIndex <= 0) {
            System.out.printf("index: coinIndex=%d, count=%d, curAnswer=%d.\n", coinIndex, curCount, curAnswer);
            return -1;
        }

        while (curCount >= 0) {
            int remainCount = solve(coins, remainAmount, coinIndex - 1, curAnswer - curCount);
            if (remainCount != -1) {
                curAnswer = curCount + remainCount;
                System.out.printf("while: coin=%d, count=%d, curAnswer=%d.\n", coin, curCount, curAnswer);
            }

            curCount--;
            remainAmount += coin;
        }

        System.out.printf("return: amount=%d, coinIndex=%d, answer=%d, curAnswer=%d.\n", amount, coinIndex, answer, curAnswer);
        return curAnswer < answer ? curAnswer : -1;
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }

        Arrays.sort(coins);
        int answer = solve(coins, amount, coins.length - 1, Integer.MAX_VALUE);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}

class Solution4 implements Solver {

//    static int counter = 0;

    private int solve(int[] coins, int amount, int coinIndex, int answer) {
//        System.out.printf("amount=%d, coinIndex=%d, answer=%d.\n", amount, coinIndex, answer);
//        ++counter;

        int curAnswer = answer;
        int coin = coins[coinIndex];

        int curCount = amount / coin;
        if (curCount >= answer) {
            return -1;
        }

        int remainAmount = amount - coin * curCount;
        if (remainAmount == 0) {
//            System.out.printf("coin=%d, count=%d.\n", coin, curCount);
            return curCount;
        }

        if (coinIndex <= 0) {
            return -1;
        }

        while (curCount >= 0) {
            int remainCount = solve(coins, remainAmount, coinIndex - 1, curAnswer - curCount);
            if (remainCount != -1) {
//                System.out.printf("coin=%d, count=%d.\n", coin, curCount);
                curAnswer = curCount + remainCount;
            }

            curCount--;
            remainAmount += coin;
        }

        return curAnswer < answer ? curAnswer : -1;
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }

        Arrays.sort(coins);
        int answer = solve(coins, amount, coins.length - 1, Integer.MAX_VALUE);

//        System.out.printf("%s -> %d.", getClass(), counter);

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}

class Solution3 implements Solver {
    static int counter = 0;

    void coinChange(int[] coins, int amount, int coinIndex, int count, int[] ans) {
//        System.out.printf("amount=%d, coinIndex=%d, answer=%d.\n", amount, coinIndex, ans[0]);
//        ++counter;

        if (amount == 0) {
            ans[0] = Math.min(ans[0], count);
            return;
        }
        if (coinIndex < 0) return;

        for (int k = amount / coins[coinIndex]; k >= 0 && k + count < ans[0]; k--) {
            coinChange(coins, amount - k * coins[coinIndex], coinIndex - 1, count + k, ans);
        }
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        Arrays.sort(coins);
        int[] ans = {Integer.MAX_VALUE};
        coinChange(coins, amount, coins.length - 1, 0, ans);

//        System.out.printf("%s -> %d.", getClass(), counter);

        return ans[0] == Integer.MAX_VALUE ? -1 : ans[0];
    }
}

class Solution6 implements Solver {

    private int coinChange(int[] coins, int amount, int coinIndex, int answer) {
        int curAnswer = answer;

        int coin = coins[coinIndex]; // coinIndex is valid
        int curCount = amount / coin;
        if (curCount >= answer) {
            return -1;
        }

        int remainAmount = amount - coin * curCount;
        if (remainAmount == 0) {
            return curCount;
        }

        if (coinIndex <= 0) {
            return -1;
        }

        int remainCount;
        while (curCount >= 0) {
            remainCount = coinChange(coins, remainAmount, coinIndex - 1, curAnswer - curCount);
            if (remainCount != -1 && remainCount < curAnswer - curCount) {
                curAnswer = curCount + remainCount;
            }

            curCount--;
            remainAmount += coin;
        }

        return curAnswer < answer ? curAnswer : -1;
    }

    @Override
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        if (coins == null || coins.length == 0) {
            return -1;
        }

        Arrays.sort(coins);

        return coinChange(coins, amount, coins.length - 1, Integer.MAX_VALUE);
    }
}

class Solution7 implements Solver {
    public int coinChange(int[] coins, int amount, int coinIndex, int countLimit) {
        if (amount == 0) {
            return 0;
        }
        if (coinIndex < 0) {
            return -1;
        }

        int coin = coins[coinIndex];
        int minCount = countLimit;
        int leftCount = 0;
        for (int count = amount / coin; count >= 0 && (minCount < 0 || count + leftCount < minCount); count--) {
            leftCount = coinChange(coins, amount - count * coin, coinIndex - 1, minCount - count);
            if (leftCount >= 0) {
                if (minCount < 0 || minCount > count + leftCount) {
                    minCount = count + leftCount;
                }
            }
        }

        return minCount;
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (coins.length == 0) {
            return -1;
        }

        Arrays.sort(coins);
        return coinChange(coins, amount, coins.length - 1, -1);
    }
}

class Solution8 implements Solver {
    private int coinChange(int[] coins, int amount, int coinIndex, int usedCount, int minCount) {
        if (amount == 0) {
            return usedCount;
        }
        if (coinIndex < 0) {
            return -1;
        }

        int coin = coins[coinIndex];
        for (int count = amount / coin; count >= 0 && (minCount == -1 || count + usedCount < minCount); count--) {
            int changeCount = coinChange(coins, amount - coin * count, coinIndex - 1, usedCount + count, minCount);
            if (changeCount != -1) {
                if (minCount == -1 || minCount > changeCount) {
                    minCount = changeCount;
                }
            }
        }

        return minCount;
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (coins.length == 0) {
            return -1;
        }

        Arrays.sort(coins);
        return coinChange(coins, amount, coins.length - 1, 0, -1);
    }
}

class Solution {
    static Solver solver = new Solution7();

    public int coinChange(int[] coins, int amount) {
        return solver.coinChange(coins, amount);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
