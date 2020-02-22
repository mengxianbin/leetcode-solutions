//The n-queens puzzle is the problem of placing n queens on an n×n chessboard su
//ch that no two queens attack each other. 
//
// 
//
// Given an integer n, return all distinct solutions to the n-queens puzzle. 
//
// Each solution contains a distinct board configuration of the n-queens' placem
//ent, where 'Q' and '.' both indicate a queen and an empty space respectively. 
//
// Example: 
//
// 
//Input: 4
//Output: [
// [".Q..",  // Solution 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // Solution 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//Explanation: There exist two distinct solutions to the 4-queens puzzle as show
//n above.
// 
// Related Topics 回溯算法


package leetcode.editor.cn.P51_NQueens;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

//leetcode submit region begin(Prohibit modification and deletion)
interface Solver {
    List<List<String>> solveNQueens(int n);
}

class Solver1 implements Solver {

    class Context {
        List<List<String>> solutions;
        int[] queen;
        boolean[] row;
        boolean[] col;
        boolean[] mainDiagonal;
        boolean[] subDiagonal;

        public Context(int n) {
            solutions = new ArrayList<>();
            queen = new int[n];
            row = new boolean[n];
            col = new boolean[n];
            mainDiagonal = new boolean[n * 2];
            subDiagonal = new boolean[n * 2];

            Arrays.fill(queen, -1);
        }

        private boolean isSafe(int i, int j) {
            return !(row[i]
                    || col[j]
                    || mainDiagonal[i - j + queen.length]
                    || subDiagonal[i + j]);
        }

        private void mark(int i, int j) {
            queen[i] = j;
            row[i] = true;
            col[j] = true;
            mainDiagonal[i - j + queen.length] = true;
            subDiagonal[i + j] = true;
        }

        private void revert(int i, int j) {
            queen[i] = -1;
            row[i] = false;
            col[j] = false;
            mainDiagonal[i - j + queen.length] = false;
            subDiagonal[i + j] = false;
        }
    }

    private List<String> generateSolution(int[] queen) {
        List<String> solution = new ArrayList<>();
        for (int pos : queen) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < queen.length; j++) {
                builder.append(j == pos ? 'Q' : '.');
            }
            solution.add(builder.toString());
        }

        return solution;
    }

    private void placeQueen(Context context, int i) {
        if (i == context.queen.length) {
            List<String> solution = generateSolution(context.queen);
            context.solutions.add(solution);
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
    public List<List<String>> solveNQueens(int n) {
        Context context = new Context(n);
        placeQueen(context, 0);

        return context.solutions;
    }
}

class SolverManager {
    static Solver getSolver() {
        return new Solver1();
    }
}

class Solution {
    public List<List<String>> solveNQueens(int n) {
        return SolverManager.getSolver().solveNQueens(n);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class SolutionTest {

    private Solver solver = SolverManager.getSolver();

    private void test(List<List<String>> expected, int n) {
        List<List<String>> actual = solver.solveNQueens(n);

        for (int i = 0; i < expected.size(); i++) {
            List<String> expectedLine = expected.get(i);
            List<String> actualLine = actual.get(i);
            assertLinesMatch(expectedLine, actualLine);
        }
    }

    @Test
    public void test1() {
        // [".Q..",  // Solution 1
        //  "...Q",
        //  "Q...",
        //  "..Q."],
        //
        // ["..Q.",  // Solution 2
        //  "Q...",
        //  "...Q",
        //  ".Q.."]

        List<List<String>> expected = new ArrayList<>();

        String[] solution1 = {
                ".Q..",
                "...Q",
                "Q...",
                "..Q.",
        };
        expected.add(Arrays.stream(solution1).collect(Collectors.toList()));

        String[] solution2 = {
                "..Q.",
                "Q...",
                "...Q",
                ".Q..",
        };
        expected.add(Arrays.stream(solution2).collect(Collectors.toList()));

        test(expected, 4);
    }
}
