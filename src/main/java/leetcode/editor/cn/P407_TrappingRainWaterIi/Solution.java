//Given an m x n matrix of positive integers representing the height of each uni
//t cell in a 2D elevation map, compute the volume of water it is able to trap aft
//er raining. 
//
// 
//
// Note: 
//
// Both m and n are less than 110. The height of each unit cell is greater than 
//0 and is less than 20,000. 
//
// 
//
// Example: 
//
// 
//Given the following 3x6 height map:
//[
//  [1,4,3,1,3,2],
//  [3,2,1,3,2,4],
//  [2,3,3,2,3,1]
//]
//
//Return 4.
// 
//
// 
//
// The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,
//3,3,2,3,1]] before the rain. 
//
// 
//
// 
//
// After the rain, water is trapped between the blocks. The total volume of wate
//r trapped is 4. 
// Related Topics 堆 广度优先搜索


package leetcode.editor.cn.P407_TrappingRainWaterIi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;
import java.util.Queue;

class SolutionTest {

    @Test
    public void testPriorotyQueue() {
        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(1);
        queue.offer(3);
        queue.offer(2);

        queue.forEach(System.out::println);
        System.out.println();

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    private void test(int[][] heightMap, Object expected) {
        Solution solution = new Solution();
        Object actual = solution.trapRainWater(heightMap);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test1() {

        int[][] input = {
                {1, 4, 3, 1, 3, 2},
                {3, 2, 1, 3, 2, 4},
                {2, 3, 3, 2, 3, 1}
        };

        Object expected = 4;
        test(input, expected);
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }

        class PosInfo implements Comparable<PosInfo> {
            int r;
            int c;
            int h;

            public PosInfo(int r, int c, int h) {
                this.r = r;
                this.c = c;
                this.h = h;
            }

            @Override
            public int compareTo(PosInfo o) {
                return this.h - o.h;
            }
        }

        // 初始化位置信息队列
        Queue<PosInfo> posInfoQueue = new PriorityQueue<>();

        // 将周边位置入队
        for (int row = 0; row < heightMap.length; row++) {
            posInfoQueue.offer(new PosInfo(row, 0, heightMap[row][0]));
            posInfoQueue.offer(new PosInfo(row, heightMap[0].length - 1, heightMap[row][heightMap[0].length - 1]));
        }
        for (int col = 1; col < heightMap[0].length - 1; col++) {
            posInfoQueue.offer(new PosInfo(0, col, heightMap[0][col]));
            posInfoQueue.offer(new PosInfo(heightMap.length - 1, col, heightMap[heightMap.length - 1][col]));
        }

        // 初始化访问标记
        boolean[][] markMap = new boolean[heightMap.length][heightMap[0].length];

        // 循环取出当前最低的位置
        int result = 0;
        while (!posInfoQueue.isEmpty()) {
            PosInfo posInfo = posInfoQueue.poll();
            for (Direction dir : Direction.values()) {
                int newR = posInfo.r + dir.r;
                int newC = posInfo.c + dir.c;
                if (newR <= 0 || newR >= heightMap.length - 1
                        || newC <= 0 || newC >= heightMap[0].length - 1
                        || markMap[newR][newC]) {
                    continue;
                }

                int newH = heightMap[newR][newC];
                if (posInfo.h > newH) {
                    result += posInfo.h - newH;
                    newH = posInfo.h;
                }

                posInfoQueue.offer(new PosInfo(newR, newC, newH));
                markMap[newR][newC] = true;
            }
        }

        return result;
    }

    enum Direction {
        UP(-1, 0), DOWN(1, 0), RIGHT(0, 1), LEFT(0, -1);

        int r;
        int c;

        Direction(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

