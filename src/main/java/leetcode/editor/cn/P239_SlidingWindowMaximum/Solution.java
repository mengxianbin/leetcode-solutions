//给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。 
//
//
// 返回滑动窗口中的最大值。 
//
// 
//
// 进阶： 
//
// 你能在线性时间复杂度内解决此题吗？ 
//
// 
//
// 示例: 
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7] 
//解释: 
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10^5 
// -10^4 <= nums[i] <= 10^4 
// 1 <= k <= nums.length 
// 
// Related Topics 堆 Sliding Window


package leetcode.editor.cn.P239_SlidingWindowMaximum;

import leetcode.editor.util.InputParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

class SolutionTest {

    private InputParser parser = new InputParser();

    private void test(int[] expected, int[] input, int k) {
        int[] actual = new Solution().maxSlidingWindow(input, k);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void test1() {
        int[] nums = parser.parseArray("[1,3,-1,-3,5,3,6,7]");
        int[] expected = parser.parseArray("[3,3,5,5,6,7]");
        test(expected, nums, 3);
    }
}

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success:
 * Runtime:11 ms, faster than 76.32% of Java online submissions.
 * Memory Usage:47.7 MB, less than 6.00% of Java online submissions.
 */
class Solution1 {
    private void addLast(Deque<Integer> deque, int newValue) {
        while (!deque.isEmpty() && deque.getLast() < newValue) {
            deque.pollLast();
        }
        deque.addLast(newValue);
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return null;
        }

//        Deque<Integer> deque = new LinkedList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            addLast(deque, nums[i]);
        }

        int[] result = new int[nums.length - k + 1];
        result[0] = deque.getFirst();

        for (int index = 1; index < result.length; index++) {
            if (nums[index - 1] == deque.getFirst()) {
                deque.pollFirst();
            }

            addLast(deque, nums[index + k - 1]);

            result[index] = deque.getFirst();
        }

        return result;
    }
}

/**
 * Success:
 * Runtime:5 ms, faster than 88.63% of Java online submissions.
 * Memory Usage:47.6 MB, less than 6.00% of Java online submissions.
 */
class Solution2 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k < 1 || nums == null || nums.length < k) {
            return null;
        }

        int[] right = new int[nums.length];
        int[] left = new int[nums.length];
        int temp = 0;
        for (int i = 0; i < right.length; i++) {
            temp = i % k == 0 ? nums[i] : Math.max(temp, nums[i]);
            right[i] = temp;
        }
        temp = nums[nums.length - 1];
        for (int j = left.length - 1; j >= 0; j--) {
            temp = j % k == k - 1 ? nums[j] : Math.max(temp, nums[j]);
            left[j] = temp;
        }

        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = Math.max(left[i], right[i + k - 1]);
        }

        return result;
    }
}

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        return new Solution2().maxSlidingWindow(nums, k);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

