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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

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
}

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Double for
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
 */
class Solution2 {
    public int trap(int[] height) {
        return 0;
    }
}

/**
 * Stack
 */
class Solution3 {
    public int trap(int[] height) {
        return 0;
    }
}

/**
 * Tow Pointer
 */
class Solution4 {
    public int trap(int[] height) {
        return 0;
    }
}

class Solution {
    public int trap(int[] height) {
        return new Solution1().trap(height);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
