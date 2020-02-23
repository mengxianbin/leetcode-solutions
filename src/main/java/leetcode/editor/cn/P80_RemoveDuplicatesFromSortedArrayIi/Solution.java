//Given a sorted array nums, remove the duplicates in-place such that duplicates
// appeared at most twice and return the new length. 
//
// Do not allocate extra space for another array, you must do this by modifying 
//the input array in-place with O(1) extra memory. 
//
// Example 1: 
//
// 
//Given nums = [1,1,1,2,2,3],
//
//Your function should return length = 5, with the first five elements of nums b
//eing 1, 1, 2, 2 and 3 respectively.
//
//It doesn't matter what you leave beyond the returned length. 
//
// Example 2: 
//
// 
//Given nums = [0,0,1,1,1,1,2,3,3],
//
//Your function should return length = 7, with the first seven elements of nums 
//being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
//
//It doesn't matter what values are set beyond the returned length.
// 
//
// Clarification: 
//
// Confused why the returned value is an integer but your answer is an array? 
//
// Note that the input array is passed in by reference, which means modification
// to the input array will be known to the caller as well. 
//
// Internally you can think of this: 
//
// 
//// nums is passed in by reference. (i.e., without making a copy)
//int len = removeDuplicates(nums);
//
//// any modification to nums in your function would be known by the caller.
//// using the length returned by your function, it prints the first len element
//s.
//for (int i = 0; i < len; i++) {
//    print(nums[i]);
//}
// 
// Related Topics 数组 双指针


package leetcode.editor.cn.P80_RemoveDuplicatesFromSortedArrayIi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;


//leetcode submit region begin(Prohibit modification and deletion)
interface Solver {
}

class Solver1 implements Solver {
}

class Solution {
    public int removeDuplicates(int[] nums) {
        int count = 1;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
                count = 1;
            }
            else if (count < 2) {
                i++;
                nums[i] = nums[j];
                count++;
            }
        }

        return i + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class SolutionTest {

    private Solution solution = new Solution();

    /**
     * input: (expected, args...)
     * solve: solution
     * assert: equals(expected, actual)  
     */
    private void test() {
        
    }

    /* [1,1,1,2,2,3] */
    @Test
    public void test1() {
        test();
    }
}
