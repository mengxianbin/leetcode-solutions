//给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的
//四个边缘都被水包围着。 
//
// 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。) 
//
// 示例 1: 
//
// 
//[{0,0,1,0,0,0,0,1,0,0,0,0,0},
// {0,0,0,0,0,0,0,1,1,1,0,0,0},
// {0,1,1,0,1,0,0,0,0,0,0,0,0},
// {0,1,0,0,1,1,0,0,1,0,1,0,0},
// {0,1,0,0,1,1,0,0,1,1,1,0,0},
// {0,0,0,0,0,0,0,0,0,0,1,0,0},
// {0,0,0,0,0,0,0,1,1,1,0,0,0},
// {0,0,0,0,0,0,0,1,1,0,0,0,0}]
// 
//
// 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。 
//
// 示例 2: 
//
// 
//[{0,0,0,0,0,0,0,0}] 
//
// 对于上面这个给定的矩阵, 返回 0。 
//
// 注意: 给定的矩阵grid 的长度和宽度都不超过 50。 
// Related Topics 深度优先搜索 数组


package leetcode.editor.cn.P695_MaxAreaOfIsland;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int getIslandSize(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length) {
            return 0;
        }
        if (grid[i][j] == 0) {
            return 0;
        }

        int size = 1;
        grid[i][j] = 0;

        size += getIslandSize(grid, i + 1, j);
        size += getIslandSize(grid, i - 1, j);
        size += getIslandSize(grid, i, j + 1);
        size += getIslandSize(grid, i, j - 1);

        return size;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                max = Math.max(max, getIslandSize(grid, i, j));
            }
        }

        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class SolutionTest {
    private Solution solution = new Solution();

    private void test(int expected, int[][] grid) {
        int actual = solution.maxAreaOfIsland(grid);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test1() {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        test(6, grid);
    }
}
