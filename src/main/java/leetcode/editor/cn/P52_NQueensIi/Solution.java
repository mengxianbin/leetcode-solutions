//The n-queens puzzle is the problem of placing n queens on an n×n chessboard su
//ch that no two queens attack each other. 
//
// 
//
// Given an integer n, return the number of distinct solutions to the n-queens p
//uzzle. 
//
// Example: 
//
// 
//Input: 4
//Output: 2
//Explanation: There are two distinct solutions to the 4-queens puzzle as shown 
//below.
//[
// [".Q..",  // Solution 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // Solution 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
// 
// Related Topics 回溯算法


package leetcode.editor.cn.P52_NQueensIi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


//leetcode submit region begin(Prohibit modification and deletion)
interface Solver {
    int totalNQueens(int n);
}

class Solver1 implements Solver {

    private class Context {
        int solutionCount;
        int[] queen;
        boolean[] row;
        boolean[] col;
        boolean[] mainDiagonal;
        boolean[] subDiagonal;

        public Context(int n) {
            solutionCount = 0;
            row = new boolean[n];
            col = new boolean[n];
            mainDiagonal = new boolean[n * 2];
            subDiagonal = new boolean[n * 2];

            queen = new int[n];
            Arrays.fill(queen, -1);
        }

        boolean isSafe(int i, int j) {
            return !(row[i] ||
                    col[j] ||
                    mainDiagonal[i - j + queen.length] ||
                    subDiagonal[i + j]);
        }

        void mark(int i, int j) {
            queen[i] = j;
            row[i] = true;
            col[j] = true;
            mainDiagonal[i - j + queen.length] = true;
            subDiagonal[i + j] = true;
        }

        void revert(int i, int j) {
            queen[i] = -1;
            row[i] = false;
            col[j] = false;
            mainDiagonal[i - j + queen.length] = false;
            subDiagonal[i + j] = false;
        }
    }

    private void placeQueen(Context context, int i) {
        if (i == context.queen.length) {
            context.solutionCount++;
            return;
        }

        for (int j = 0; j < context.queen.length; j++) {
            if (context.isSafe(i, j)) {
                context.mark(i, j);
                placeQueen(context, i + 1);
                context.revert(i, j);
            }
        }
    }

    @Override
    public int totalNQueens(int n) {
        Context context = new Context(n);
        placeQueen(context, 0);

        return context.solutionCount;
    }
}

class Solution {
    private Solver solver = new Solver1();

    public int totalNQueens(int n) {
        return solver.totalNQueens(n);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class SolutionTest {

    private Solution solution = new Solution();

    /**
     * input: (expected, args...)
     * solve: solution
     * assert: equals(expected, actual)
     */
    private void test(int expected, int n) {
        int actual = solution.totalNQueens(n);
        Assertions.assertEquals(expected, actual);
    }

    /* 4 */
    @Test
    public void test1() {
        test(2, 4);
    }
}
