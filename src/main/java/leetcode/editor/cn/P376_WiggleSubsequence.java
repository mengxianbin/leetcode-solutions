//A sequence of numbers is called a wiggle sequence if the differences between s
//uccessive numbers strictly alternate between positive and negative. The first di
//fference (if one exists) may be either positive or negative. A sequence with few
//er than two elements is trivially a wiggle sequence. 
//
// For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3
//,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,
//7,4,5,5] are not wiggle sequences, the first because its first two differences a
//re positive and the second because its last difference is zero. 
//
// Given a sequence of integers, return the length of the longest subsequence th
//at is a wiggle sequence. A subsequence is obtained by deleting some number of el
//ements (eventually, also zero) from the original sequence, leaving the remaining
// elements in their original order. 
//
// Example 1: 
//
// 
//Input: [1,7,4,9,2,5]
//Output: 6
//Explanation: The entire sequence is a wiggle sequence. 
//
// 
// Example 2: 
//
// 
//Input: [1,17,5,10,13,15,10,5,16,8]
//Output: 7
//Explanation: There are several subsequences that achieve this length. One is [
//1,17,10,13,10,16,8]. 
//
// 
// Example 3: 
//
// 
//Input: [1,2,3,4,5,6,7,8,9]
//Output: 2 
//
// Follow up: 
//Can you do it in O(n) time? 
// 
// 
// Related Topics 贪心算法 动态规划


package leetcode.editor.cn;

public class P376_WiggleSubsequence {
    public static void main(String[] args) {
        P376_WiggleSubsequence problem = new P376_WiggleSubsequence();
        Solution solution = problem.new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int wiggleMaxLength(int[] nums) {
            if (nums == null || nums.length == 0)
                return 0;
            if (nums.length < 2) {
                return nums.length;
            }

            int count = 1;


            for (int i = 1, j = 0; i < nums.length; j = i, i++) {
                if (nums[j] < nums[i]) {
                    count++;
                    while (i < nums.length - 1 && nums[i] <= nums[i + 1]) {
                        i++;
                    }
                } else if (nums[j] > nums[i]) {
                    count++;
                    while (i < nums.length - 1 && nums[i] >= nums[i + 1]) {
                        i++;
                    }
                }
            }

            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
