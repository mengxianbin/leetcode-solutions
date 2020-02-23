//Given an array nums and a value val, remove all instances of that value in-pla
//ce and return the new length. 
//
// Do not allocate extra space for another array, you must do this by modifying 
//the input array in-place with O(1) extra memory. 
//
// The order of elements can be changed. It doesn't matter what you leave beyond
// the new length. 
//
// Example 1: 
//
// 
//Given nums = [3,2,2,3], val = 3,
//
//Your function should return length = 2, with the first two elements of nums be
//ing 2.
//
//It doesn't matter what you leave beyond the returned length.
// 
//
// Example 2: 
//
// 
//Given nums = [0,1,2,2,3,0,4,2], val = 2,
//
//Your function should return length = 5, with the first five elements of nums c
//ontaining 0, 1, 3, 0, and 4.
//
//Note that the order of those five elements can be arbitrary.
//
//It doesn't matter what values are set beyond the returned length. 
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
//int len = removeElement(nums, val);
//
//// any modification to nums in your function would be known by the caller.
//// using the length returned by your function, it prints the first len element
//s.
//for (int i = 0; i < len; i++) {
//    print(nums[i]);
//} Related Topics 数组 双指针


package leetcode.editor.cn.P27_RemoveElement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


//leetcode submit region begin(Prohibit modification and deletion)
interface Solver {
    int removeElement(int[] nums, int val);
}

class Solver1 implements Solver {
    @Override
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int p = 0;
        int q = nums.length - 1;
        while (p < q) {
            while (p < q && nums[q] == val) {
                q--;
            }
            while (p < q && nums[p] != val) {
                p++;
            }
            if (p != q) {
                int t = nums[p];
                nums[p] = nums[q];
                nums[q] = t;
            }
        }

        return nums[p] == val ? p : p + 1;
    }
}

class Solver2 implements Solver {

    @Override
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int p = 0;
        int q = nums.length - 1;

        while (p < q) {
            while(p < q && nums[q] == val) {
                q--;
            }
            while (p < q && nums[p] != val) {
                p++;
            }
            nums[p] = nums[q];
            q--;
        }

        return nums[p] != val ? p + 1 : p;
    }
}

class Solution {
    Solver solver = new Solver2();

    public int removeElement(int[] nums, int val) {
        return solver.removeElement(nums, val);
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
    private void test(int expected, int[] nums, int val) {
        int actual = solution.removeElement(nums, val);
        Assertions.assertEquals(expected, actual);
    }

    /* [3,2,2,3]
3 */
    @Test
    public void test1() {
        int[] nums = {3, 2, 2, 3};
        test(2, nums, 3);
    }

    @Test
    public void test2() {
        int[] nums = {1};
        test(0, nums, 1);
    }
}
