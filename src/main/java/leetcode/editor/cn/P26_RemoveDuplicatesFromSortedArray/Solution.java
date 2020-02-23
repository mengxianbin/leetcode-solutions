//Given a sorted array nums, remove the duplicates in-place such that each eleme
//nt appear only once and return the new length. 
//
// Do not allocate extra space for another array, you must do this by modifying 
//the input array in-place with O(1) extra memory. 
//
// Example 1: 
//
// 
//Given nums = [1,1,2],
//
//Your function should return length = 2, with the first two elements of nums be
//ing 1 and 2 respectively.
//
//It doesn't matter what you leave beyond the returned length. 
//
// Example 2: 
//
// 
//Given nums = [0,0,1,1,1,2,2,3,3,4],
//
//Your function should return length = 5, with the first five elements of nums b
//eing modified to 0, 1, 2, 3, and 4 respectively.
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
//} Related Topics 数组 双指针


package leetcode.editor.cn.P26_RemoveDuplicatesFromSortedArray;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;


//leetcode submit region begin(Prohibit modification and deletion)
interface Solver {
}

class Solver1 implements Solver {
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private int partition(int[] nums, int l, int r) {
        int p = l;
        int q = r;
        int v = nums[r];
        while (p < q) {
            while (p < q && nums[p] <= v) {
                p++;
            }
            nums[q] = nums[p];

            while (p < q && nums[q] > v) {
                q--;
            }
            nums[p] = nums[q];
        }
        nums[q] = v;

        return q;
    }

    private void sort(int[] nums, int l, int r) {
        if (l >= r || l < 0 || r >= nums.length) {
            return;
        }

        int i = partition(nums, l, r);
        sort(nums, l, i - 1);
        sort(nums, i + 1, r);
    }

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        sort(nums, 0, nums.length);

        int i = 0;
        int j = 1;
        while (j < nums.length) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
            j++;
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

    /* [1,1,2] */
    @Test
    public void test1() {
        test();
    }
}
