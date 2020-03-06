//输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。 
//
// 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。 
//
// 
//
// 示例 1： 
//
// 输入：target = 9
//输出：[[2,3,4],[4,5]]
// 
//
// 示例 2： 
//
// 输入：target = 15
//输出：[[1,2,3,4,5],[4,5,6],[7,8]]
// 
//
// 
//
// 限制： 
//
// 
// 1 <= target <= 10^5 
// 
//
// 
//


package leetcode.editor.cn.P面试题57_II_HeWeiSdeLianXuZhengShuXuLieLcof;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] findContinuousSequence(int target) {
        List<int[]> resultList = new ArrayList<>();
        int i = 1, j = 2, sum = i + j;

        while (i * 2 < target) {
            if (sum == target) {
                int[] result = new int[j - i + 1];
                for (int k = i; k <= j; k++) {
                    result[k - i] = k;
                }
                resultList.add(result);

                j++;
                sum += j;
            } else if (sum > target) {
                if (i < j) {
                    sum -= i;
                    i++;
                } else {
                    break;
                }
            } else {
                if (j < target) {
                    j++;
                    sum += j;
                } else {
                    break;
                }
            }
        }

        return resultList.toArray(new int[resultList.size()][]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class SolutionTest {

    private Solution solution = new Solution();

    private void test(int[][] expected, int input) {
        int[][] actual = solution.findContinuousSequence(input);
        Assertions.assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            Assertions.assertArrayEquals(expected[i], actual[i]);
        }
    }

    /* 9 */
    @Test
    public void test1() {
        int[][] expected = {{2, 3, 4}, {4, 5}};
        test(expected, 9);
    }

    @Test
    public void test2() {
        int[][] expected = {{1, 2, 3, 4, 5}, {4, 5, 6}, {7, 8}};
        test(expected, 15);
    }
}
