//一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩
//师找到最优的预约集合（总预约时间最长），返回总的分钟数。 
//
// 注意：本题相对原题稍作改动 
//
// 
//
// 示例 1： 
//
// 输入： [1,2,3,1]
//输出： 4
//解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
// 
//
// 示例 2： 
//
// 输入： [2,7,9,3,1]
//输出： 12
//解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
// 
//
// 示例 3： 
//
// 输入： [2,1,4,5,3,1,1,3]
//输出： 12
//解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
// 
// Related Topics 动态规划


package leetcode.editor.cn.PInterview1716_TheMasseuse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int massage(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int refuseMax = 0;
        int acceptMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int refuse = Math.max(refuseMax, acceptMax);
            acceptMax = refuseMax + nums[i];
            refuseMax = refuse;
        }

        return Math.max(acceptMax, refuseMax);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class SolutionTest {
    private void test(int expected, int[] input) {
        int actual = new Solution().massage(input);
        Assertions.assertEquals(expected, actual);
    }

    /* [1,2,3,1] */
    @Test
    public void test1() {
        test(4, new int[]{1, 2, 3, 1});
    }


    @Test
    public void test2() {
        test(12, new int[]{2, 1, 4, 5, 3, 1, 1, 3});
    }
}
