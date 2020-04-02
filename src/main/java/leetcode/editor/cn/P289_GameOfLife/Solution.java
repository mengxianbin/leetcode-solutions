//根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。 
//
// 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dea
//d）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律： 
//
// 
// 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡； 
// 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活； 
// 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡； 
// 如果死细胞周围正好有三个活细胞，则该位置死细胞复活； 
// 
//
// 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生
//和死亡是同时发生的。 
//
// 
//
// 示例： 
//
// 输入： 
//[
//  [0,1,0],
//  [0,0,1],
//  [1,1,1],
//  [0,0,0]
//]
//输出：
//[
//  [0,0,0],
//  [1,0,1],
//  [0,1,1],
//  [0,1,0]
//] 
//
// 
//
// 进阶： 
//
// 
// 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。 
// 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？ 
// 
// Related Topics 数

package leetcode.editor.cn.P289_GameOfLife;

import leetcode.editor.util.InputParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success:
 * Runtime:0 ms, faster than 100.00% of Java online submissions.
 * Memory Usage:37.8 MB, less than 5.71% of Java online submissions.
 */
class Solution {
    private int calculate(int[][] board, int i, int j) {
        int neighbor = 0;
        for (int r = -1; r < 2; r++) {
            int newR = i + r;
            if (newR < 0 || newR >= board.length) {
                continue;
            }

            for (int c = -1; c < 2; c++) {
                int newC = j + c;
                if (newC < 0 || newC >= board[0].length) {
                    continue;
                }

                neighbor += board[newR][newC] & 1;
            }
        }

        int oldState = board[i][j];
        neighbor -= oldState;

        int newState = 0;
        if (neighbor == 3 || (neighbor == 2 && (oldState & 1) == 1)) {
            newState = 1;
        }

        return newState << 1 | oldState;
    }

    private void calculateNewState(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = calculate(board, i, j);
            }
        }
    }

    private void updateNewState(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    public void gameOfLife(int[][] board) {
        calculateNewState(board);
        updateNewState(board);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class SolutionTest {

    private InputParser parser = new InputParser();

    private void test(int[][] expected, int[][] input) {
        new Solution().gameOfLife(input);
        Assertions.assertArrayEquals(expected, input);
    }

    @Test
    public void test1() {
        int[][] input = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };

        int[][] expected = {
                {0, 0, 0},
                {1, 0, 1},
                {0, 1, 1},
                {0, 1, 0}
        };

        test(expected, input);
    }
}
