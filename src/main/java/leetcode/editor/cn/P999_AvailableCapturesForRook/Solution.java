//在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。它们分别以字符 “R”，“.”，“B
//” 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。 
//
// 车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝那个方向移动，直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜
//色相反的卒。另外，车不能与其他友方（白色）象进入同一个方格。 
//
// 返回车能够在一次移动中捕获到的卒的数量。 
// 
//
// 示例 1： 
//
// 
//
// 输入：[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",
//".",".","R",".",".",".","p"],[".",".",".",".",".",".",".","."],[".",".",".",".",
//".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".",
//"."],[".",".",".",".",".",".",".","."]]
//输出：3
//解释：
//在本例中，车能够捕获所有的卒。
// 
//
// 示例 2： 
//
// 
//
// 输入：[[".",".",".",".",".",".",".","."],[".","p","p","p","p","p",".","."],[".",
//"p","p","B","p","p",".","."],[".","p","B","R","B","p",".","."],[".","p","p","B",
//"p","p",".","."],[".","p","p","p","p","p",".","."],[".",".",".",".",".",".",".",
//"."],[".",".",".",".",".",".",".","."]]
//输出：0
//解释：
//象阻止了车捕获任何卒。
// 
//
// 示例 3： 
//
// 
//
// 输入：[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",
//".",".","p",".",".",".","."],["p","p",".","R",".","p","B","."],[".",".",".",".",
//".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","p",".",".",".",
//"."],[".",".",".",".",".",".",".","."]]
//输出：3
//解释： 
//车可以捕获位置 b5，d6 和 f5 的卒。
// 
//
// 
//
// 提示： 
//
// 
// board.length == board[i].length == 8 
// board[i][j] 可以是 'R'，'.'，'B' 或 'p' 
// 只有一个格子上存在 board[i][j] == 'R' 
// 
// Related Topics 数组


package leetcode.editor.cn.P999_AvailableCapturesForRook;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success:
 * Runtime:0 ms, faster than 100.00% of Java online submissions.
 * Memory Usage:36.8 MB, less than 5.40% of Java online submissions.
 */
class Solution1 {
    private int capture(char[][] board, int[] posR, int moveX, int moveY) {
        int[] posNext = {posR[0] + moveY, posR[1] + moveX};
        int lenY = board.length;
        int lenX = board[0].length;
        while (posNext[0] >= 0 && posNext[0] < lenY
                && posNext[1] >= 0 && posNext[1] < lenX) {

            char c = board[posNext[0]][posNext[1]];
            if (c == 'B') {
                return 0;
            }
            if (c == 'p') {
                return 1;
            }

            posNext[0] += moveY;
            posNext[1] += moveX;
        }

        return 0;
    }

    public int numRookCaptures(char[][] board) {
        int count = 0;

        int[] posR = null;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'R') {
                    posR = new int[]{i, j};
                    break;
                }
            }
        }
        if (posR == null) {
            return count;
        }

        count += capture(board, posR, 0, -1);
        count += capture(board, posR, 0, 1);
        count += capture(board, posR, 1, 0);
        count += capture(board, posR, -1, 0);

        return count;
    }
}

/**
 * Success:
 * Runtime:0 ms, faster than 100.00% of Java online submissions.
 * Memory Usage:36.7 MB, less than 5.40% of Java online submissions.
 */
class Solution {
    public int numRookCaptures(char[][] board) {
        int ri = -1;
        int rj = -1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'R') {
                    ri = i;
                    rj = j;
                    break;
                }
            }
        }

        if (ri < 0) {
            return 0;
        }

        int count = 0;

        int[][] direction = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        for (int[] dir : direction) {

            // loop initial state
            int ni = ri + dir[0];
            int nj = rj + dir[1];

            while (true) {
                // loop conditions
                if (ni < 0 || ni >= board.length) {
                    break;
                }
                if (nj < 0 || nj >= board[ni].length) {
                    break;
                }
                char c = board[ni][nj];
                if (c == 'B') {
                    break;
                }
                if (c == 'p') {
                    // loop action
                    count++;
                    break;
                }

                // loop iterate
                ni += dir[0];
                nj += dir[1];
            }
        }

        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
