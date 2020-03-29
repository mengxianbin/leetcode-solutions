//你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，
//你知道距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。 
//
// 我们这里说的距离是『曼哈顿距离』（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x
//1| + |y0 - y1| 。 
//
// 如果我们的地图上只有陆地或者海洋，请返回 -1。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：[[1,0,1],[0,0,0],[1,0,1]]
//输出：2
//解释： 
//海洋区域 (1, 1) 和所有陆地区域之间的距离都达到最大，最大距离为 2。
// 
//
// 示例 2： 
//
// 
//
// 输入：[[1,0,0],[0,0,0],[0,0,0]]
//输出：4
//解释： 
//海洋区域 (2, 2) 和所有陆地区域之间的距离都达到最大，最大距离为 4。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= grid.length == grid[0].length <= 100 
// grid[i][j] 不是 0 就是 1 
// 
// Related Topics 广度优先搜索 图


package leetcode.editor.cn.P1162_AsFarFromLandAsPossible;

import leetcode.editor.util.InputParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success:
 * Runtime:15 ms, faster than 92.84% of Java online submissions.
 * Memory Usage:42.4 MB, less than 99.00% of Java online submissions.
 */
class Solution {
    static final int[][] directions = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int maxDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        Queue<int[]> seen = new ArrayDeque<>(grid.length * grid[0].length); // 15 ~ 26 ms
//        Queue<int[]> seen = new LinkedBlockingQueue<>(grid.length * grid[0].length); // 36 ~ 54 ms
//        Queue<int[]> seen = new ArrayBlockingQueue<>(grid.length * grid[0].length); // 40 ~ 52 ms
//        Queue<int[]> seen = new LinkedList<>(); // 17 ~ 22 ms
//        Queue<int[]> seen = new ConcurrentLinkedQueue<>(); // 29 ~ 44 ms
//        Queue<int[]> seen = new ConcurrentLinkedDeque<>(); // 36 ~ 47 ms
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] > 0) {
                    seen.offer(new int[]{i, j});
                }
            }
        }

        int[] lastPos = null;
        while (!seen.isEmpty()) {
            int[] curPos = seen.poll();
            int curY = curPos[0];
            int curX = curPos[1];
            int curDis = grid[curY][curX];

            for (int[] direction : directions) {
                int nextY = curY + direction[0];
                int nextX = curX + direction[1];

                if (nextY < 0 || nextY >= grid.length) {
                    continue;
                }
                if (nextX < 0 || nextX >= grid.length) {
                    continue;
                }
                if (grid[nextY][nextX] > 0) {
                    continue;
                }

                grid[nextY][nextX] = curDis + 1;
                lastPos = new int[]{nextY, nextX};
                seen.offer(lastPos);
            }
        }

        if (lastPos == null) {
            return -1;
        }

        return grid[lastPos[0]][lastPos[1]] - 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class SolutionTest {

    private InputParser parser = new InputParser();

    private void test(int expected, int[][] input) {
        int actual = new Solution().maxDistance(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test1() {
        int[][] input = parser.parseArray2("// 输入：[[1,0,1],[0,0,0],[1,0,1]]");
        test(2, input);
    }

    @Test
    public void test2() {
        int[][] input = parser.parseArray2("// 输入：[[1,0,0],[0,0,0],[0,0,0]]");
        test(4, input);
    }

    @Test
    public void test3() {
        int[][] input = parser.parseArray2("input:[[1,1,1,1,1],[1,1,1,1,1],[1,1,1,1,1],[1,1,1,1,1],[1,1,1,1,1]]");
        test(-1, input);
    }

}
