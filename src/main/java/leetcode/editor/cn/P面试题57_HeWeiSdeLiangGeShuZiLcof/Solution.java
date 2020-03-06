//输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
//
//
//
// 示例 1：
//
// 输入：nums = [2,7,11,15], target = 9
//输出：[2,7] 或者 [7,2]
//
//
// 示例 2：
//
// 输入：nums = [10,26,30,31,47,60], target = 40
//输出：[10,30] 或者 [30,10]
//
//
//
//
// 限制：
//
//
// 1 <= nums.length <= 10^5
// 1 <= nums[i] <= 10^6
//
//


package leetcode.editor.cn.P面试题57_HeWeiSdeLiangGeShuZiLcof;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(target - num)) {
                return new int[]{target - num, num};
            }
            set.add(num);
        }

        return null;
    }
}

class Solution2 {
    public int[] twoSum(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int sum = nums[low] + nums[high];
            if (sum == target) {
                return new int[]{nums[low], nums[high]};
            } else if (sum > target) {
                high--;
            } else {
                low++;
            }
        }

        return null;
    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        return new Solution2().twoSum(nums, target);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class SolutionTest {

    private Solution solution = new Solution();

    private void test(int[] expected, int[] nums, int target) {
        int[] actual = solution.twoSum(nums, target);
        Assertions.assertArrayEquals(expected, actual);
    }

    /* [2,7,11,15]
9 */
    @Test
    public void test1() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] expected = {2, 7};
        test(expected, nums, target);
    }


    @Test
    public void test2() {
        int[] nums = {10, 26, 30, 31, 47, 60};
        int target = 40;
        int[] expected = {10, 30};
        test(expected, nums, target);
    }
}
