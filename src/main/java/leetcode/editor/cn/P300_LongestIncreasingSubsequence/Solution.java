//Given an unsorted array of integers, find the length of longest increasing sub
//sequence. 
//
// Example: 
//
// 
//Input: [10,9,2,5,3,7,101,18]
//Output: 4 
//Explanation: The longest increasing subsequence is [2,3,7,101], therefore the 
//length is 4. 
//
// Note: 
//
// 
// There may be more than one LIS combination, it is only necessary for you to r
//eturn the length. 
// Your algorithm should run in O(n2) complexity. 
// 
//
// Follow up: Could you improve it to O(n log n) time complexity? 
// Related Topics 二分查找 动态规划


package leetcode.editor.cn.P300_LongestIncreasingSubsequence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SolutionTest {

    private void test(Object expected, Object... args) {
        Object actual = SolutionManager.solver.solve(args);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test1() {
        int[] input = {10, 9, 2, 5, 3, 7, 101, 18};
        test(4, input, null);
    }

    @Test
    public void testVarargs1() {
        int[] args = {1, 2, 3};
//        System.out.printf("%d, %d, %d.", (Object[]) args);
//        System.out.printf("%d, %d, %d.", Arrays.stream(args).boxed().toArray(Integer[]::new));
        System.out.printf("%d, %d, %d.", Arrays.stream(args).boxed().toArray());
    }

    @Test
    @SuppressWarnings("all")
    public void testVarargs2() {
        Integer[] args = {1, 2, 3};
//        System.out.printf("%d, %d, %d.", args);
//        System.out.printf("%d, %d, %d.", (Object) args);
        System.out.printf("%d, %d, %d.", (Object[]) args);
//        System.out.printf("%d, %d, %d.", Object[].class.cast(args));
    }


    @Test
    public void testVarargs3() {
        Object[] args = {1, 2, 3};
        System.out.printf("%d, %d, %d.", args);
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
interface Solver {
    Object solve(Object... args);
}

class Solution1 implements Solver {

    public Object solve(Object... args) {
        int[] nums = (int[]) args[0];
        if (nums == null) {
            return 0;
        }
        if (nums.length < 2) {
            return nums.length;
        }

        int[] lengthData = new int[nums.length];
        lengthData[0] = 1;

        int maxLength = 1;
        for (int i = 1; i < nums.length; i++) {
            lengthData[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && lengthData[j] + 1 > lengthData[i]) {
                    lengthData[i] = lengthData[j] + 1;
                }
            }
            if (lengthData[i] > maxLength) {
                maxLength = lengthData[i];
            }
        }

        return maxLength;
    }
}

class Solution2 implements Solver {

    private int binarySearch(List<Integer> list, int target) {
        if (list == null || list.isEmpty()) {
            return -1;
        }
        if (list.get(list.size() - 1) < target) {
            return -1;
        }

        int low = 0;
        int high = list.size() - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            int midValue = list.get(mid);
            if (midValue >= target && (mid == 0 || list.get(mid - 1) < target)) {
                return mid;
            } else if (midValue >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    public Object solve(Object... args) {
        int[] nums = (int[]) args[0];
        if (nums == null) {
            return 0;
        }
        if (nums.length < 2) {
            return nums.length;
        }

        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > list.get(list.size() - 1)) {
                list.add(nums[i]);
            } else {

//                for (int j = 0; j < list.size(); j++) {
//                    if (list.get(j) >= nums[i]) {
//                        list.set(j, nums[i]);
//                        break;
//                    }
//                }

                int replaceIndex = binarySearch(list, nums[i]);
                if (replaceIndex >= 0) {
                    list.set(replaceIndex, nums[i]);
                }
            }
        }

        return list.size();
    }
}

class SolutionManager {
    static Solver solver = new Solution2();
}

class Solution {
    public int lengthOfLIS(int[] nums) {
        return (int) SolutionManager.solver.solve(nums, null);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

