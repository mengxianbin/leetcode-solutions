//You are climbing a stair case. It takes n steps to reach to the top. 
//
// Each time you can either climb 1 or 2 steps. In how many distinct ways can yo
//u climb to the top? 
//
// Note: Given n will be a positive integer. 
//
// Example 1: 
//
// 
//Input: 2
//Output: 2
//Explanation: There are two ways to climb to the top.
//1. 1 step + 1 step
//2. 2 steps
// 
//
// Example 2: 
//
// 
//Input: 3
//Output: 3
//Explanation: There are three ways to climb to the top.
//1. 1 step + 1 step + 1 step
//2. 1 step + 2 steps
//3. 2 steps + 1 step
// 
// Related Topics 动态规划


package leetcode.editor.cn.P70_ClimbingStairs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {
    Solver solver = new Solution2();

    private void test(int n, Object expected) {
        Object actual = solver.solve(n);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test1() {
        test(2, 2);
    }

    @Test
    public void test2() {
        test(3, 3);
    }

    @Test
    public void testN() {
        for (int i = 0; i <= 10; i++) {
            System.out.println(solver.solve(i));
        }
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
interface Solver {
    int solve(int n);
}

class Solution1 implements Solver {

    static Solver instance = new Solution1();

    @Override
    public int solve(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int previous = 1;
        int current = 2;
        int next = 0;

        for (int step = 1; step <= n - 2; step++) {
            next = previous + current;
            previous = current;
            current = next;
        }

        return next;
    }
}

class Solution2 implements Solver {

    static Solver instance = new Solution2();

    private int[][] multiply(int[][] m1, int[][] m2) {
        int[][] result = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    result[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }

        return result;
    }

    private int[][] power(int[][] m, int exponent) {
        int[][] result = new int[m.length][m.length];
        for (int i = 0; i < m.length; i++) {
            result[i][i] = 1;
        }

        int[][] base = m;
        for (int bits = exponent; bits > 0; bits >>= 1) {
            if ((bits & 1) == 1) {
                result = multiply(result, base);
            }
            base = multiply(base, base);
        }

        return result;
    }

    @Override
    public int solve(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int[][] trans = {{0, 1}, {1, 1}};
        int[][] p = power(trans, n - 2);
        int[][] start = {{1, 2}};
        int[][] result = multiply(start, p);

        return result[0][1];
    }
}

class Solution {
    public int climbStairs(int n) {
        return Solution1.instance.solve(n);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

