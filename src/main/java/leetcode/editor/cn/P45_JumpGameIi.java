//Given an array of non-negative integers, you are initially positioned at the f
//irst index of the array. 
//
// Each element in the array represents your maximum jump length at that positio
//n. 
//
// Your goal is to reach the last index in the minimum number of jumps. 
//
// Example: 
//
// 
//Input: [2,3,1,1,4]
//Output: 2
//Explanation: The minimum number of jumps to reach the last index is 2.
//    Jump 1 step from index 0 to 1, then 3 steps to the last index. 
//
// Note: 
//
// You can assume that you can always reach the last index. 
// Related Topics 贪心算法 数组


package leetcode.editor.cn;

public class P45_JumpGameIi {
    public static void main(String[] args) {
        P45_JumpGameIi problem = new P45_JumpGameIi();
        Solution solution = problem.new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int jump(int[] nums) {
            if (nums == null || nums.length < 2) {
                return 0;
            }

            int curMax = nums[0];
            int nextMax = nums[0];
            int jumpNum = 1;
            for (int index = 0; index < nums.length; index++) {
                if (index > curMax) {
                    jumpNum++;
                    curMax = nextMax;
                }
                nextMax = Math.max(nextMax, index + nums[index]);
            }

            return jumpNum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}