//在 N * N 的网格上，我们放置一些 1 * 1 * 1 的立方体。 
//
// 每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。 
//
// 请你返回最终形体的表面积。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：[[2]]
//输出：10
// 
//
// 示例 2： 
//
// 输入：[[1,2],[3,4]]
//输出：34
// 
//
// 示例 3： 
//
// 输入：[[1,0],[0,2]]
//输出：16
// 
//
// 示例 4： 
//
// 输入：[[1,1,1],[1,0,1],[1,1,1]]
//输出：32
// 
//
// 示例 5： 
//
// 输入：[[2,2,2],[2,1,2],[2,2,2]]
//输出：46
// 
//
// 
//
// 提示： 
//
// 
// 1 <= N <= 50 
// 0 <= grid[i][j] <= 50 
// 
// Related Topics 几何 数学


package leetcode.editor.cn.P892_SurfaceAreaOf3dShapes;

import leetcode.editor.util.InputParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success:
 * Runtime:3 ms, faster than 98.69% of Java online submissions.
 * Memory Usage:40.9 MB, less than 91.18% of Java online submissions.
 */
class Solution {
    public int surfaceArea(int[][] grid) {
        int areaSum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int value = grid[i][j];
                if (value > 0) {
                    int area = (value << 2) + 2;
                    if (i > 0) {
                        area -= Math.min(grid[i - 1][j], value) << 1;
                    }
                    if (j > 0) {
                        area -= Math.min(grid[i][j - 1], value) << 1;
                    }
                    areaSum += area;
                }
            }
        }

        return areaSum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class SolutionTest {

    InputParser parser = new InputParser();

    private void test(int expected, int[][] input) {
        int actual = new Solution().surfaceArea(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test1() {
        int[][] input = {{2}};
        int expected = 10;
        test(expected, input);
    }

    @Test
    public void test2() {
        int[][] input = parser.parseArray2("[[1,2],[3,4]]");
        int expected = 34;
        test(expected, input);
    }

    @Test
    public void test3() {
        int[][] input = parser.parseArray2("[[1,0],[0,2]]");
        int expected = 16;
        test(expected, input);
    }

    @Test
    public void test4() {
        int[][] input = parser.parseArray2("[[1,1,1],[1,0,1],[1,1,1]]");
        int expected = 32;
        test(expected, input);
    }

    @Test
    public void test5() {
        int[][] input = parser.parseArray2("[[2,2,2],[2,1,2],[2,2,2]]");
        int expected = 46;
        test(expected, input);
    }
}
