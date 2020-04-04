//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Mar
//cos 贡献此图。 
//
// 示例: 
//
// 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
//输出: 6 
// Related Topics 栈 数组 双指针


package leetcode.editor.cn.P42_TrappingRainWater;

import leetcode.editor.util.InputParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;

class SolutionTest {

    private InputParser parser = new InputParser();

    private void test(int expected, int[] input) {
        int actual = new Solution().trap(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test1() {
        int[] input = parser.parseArray("[0,1,0,2,1,0,1,3,2,1,2,1]");
        test(6, input);
    }

    @Test
    public void test2() {
        int[] input = parser.parseArray("[2,0,2]");
        test(2, input);
    }
}

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Double for
 * <p>
 * Success:
 * Runtime:100 ms, faster than 7.47% of Java online submissions.
 * Memory Usage:39.5 MB, less than 11.78% of Java online submissions.
 */
class Solution1 {
    public int trap(int[] height) {
        int result = 0;

        for (int i = 1; i < height.length - 1; i++) {
            int lMax = 0;
            for (int l = 0; l < i; l++) {
                lMax = Math.max(lMax, height[l]);
            }

            int rMax = 0;
            for (int r = height.length - 1; r > i; r--) {
                rMax = Math.max(rMax, height[r]);
            }

            int h = height[i];
            if (lMax > h && rMax > h) {
                result += Math.min(lMax, rMax) - h;
            }
        }

        return result;
    }
}

/**
 * Double array
 * <p>
 * Success:
 * Runtime:1 ms, faster than 99.98% of Java online submissions.
 * Memory Usage:39.1 MB, less than 11.91% of Java online submissions.
 */
class Solution2 {
    public int trap(int[] height) {
        int[] lMax = new int[height.length];
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            lMax[i] = max;
            max = Math.max(max, height[i]);
        }

        max = 0;
        int[] rMax = new int[height.length];
        for (int i = height.length - 1; i >= 0; i--) {
            rMax[i] = max;
            max = Math.max(max, height[i]);
        }

        int result = 0;
        for (int i = 0; i < height.length; i++) {
            int min = Math.min(lMax[i], rMax[i]);
            int h = height[i];
            if (min > h) {
                result += min - h;
            }
        }

        return result;
    }
}

/**
 * Stack
 * <p>
 * Success:
 * Runtime:3 ms, faster than 32.65% of Java online submissions.
 * Memory Usage:39.4 MB, less than 11.78% of Java online submissions.
 */
class Solution3 {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        Deque<Integer> stack = new LinkedList<>();
        stack.addFirst(0);

        int result = 0;
        for (int i = 1; i < height.length; i++) {
            int h = height[i];
            int l = -1;
            while (!stack.isEmpty() && height[stack.peekFirst()] < h) {
                l = stack.pollFirst();
            }
            if (!stack.isEmpty()) {
                l = stack.peekFirst();
            }
            int min = Math.min(height[l], h);
            for (int j = l + 1; j < i; j++) {
                result += min - height[j];
                height[j] = min;
            }

            stack.addFirst(i);
        }

        return result;
    }
}

/**
 * Tow Pointer
 * <p>
 * Success:
 * Runtime:1 ms, faster than 99.98% of Java online submissions.
 * Memory Usage:39.7 MB, less than 11.78% of Java online submissions.
 */
class Solution4 {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int l = 0;
        int r = height.length - 1;
        int lMax = height[l];
        int rMax = height[r];
        int result = 0;
        while (l < r) {
            int h = Math.min(lMax, rMax);
            if (lMax <= rMax) {
                if (height[l] < h) {
                    result += h - height[l];
                }
                l++;
                lMax = Math.max(lMax, height[l]);
            } else {
                if (height[r] < h) {
                    result += h - height[r];
                }
                r--;
                rMax = Math.max(rMax, height[r]);
            }
        }

        return result;
    }
}

class Solution {
    public int trap(int[] height) {
        return new Solution4().trap(height);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
