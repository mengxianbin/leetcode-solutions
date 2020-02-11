//Given an array of non-negative integers, you are initially positioned at the f
//irst index of the array. 
//
// Each element in the array represents your maximum jump length at that positio
//n. 
//
// Determine if you are able to reach the last index. 
//
// Example 1: 
//
// 
//Input: [2,3,1,1,4]
//Output: true
//Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
// 
//
// Example 2: 
//
// 
//Input: [3,2,1,0,4]
//Output: false
//Explanation: You will always arrive at index 3 no matter what. Its maximum
//             jump length is 0, which makes it impossible to reach the last ind
//ex.
// 
// Related Topics 贪心算法 数组


package leetcode.editor.cn;

public class P55_JumpGame {
    public static void main(String[] args) {
        P55_JumpGame problem = new P55_JumpGame();
        Solution solution = problem.new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canJump(int[] nums) {
            int reach = 0;
            for (int index = 0; index < nums.length; index++) {
                if (index > reach || reach >= nums.length - 1) {
                    break;
                }
                reach = Math.max(reach, index + nums[index]);
            }

            return reach >= nums.length - 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}