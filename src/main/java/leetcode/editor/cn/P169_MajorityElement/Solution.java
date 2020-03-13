//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 示例 1: 
//
// 输入: [3,2,3]
//输出: 3 
//
// 示例 2: 
//
// 输入: [2,2,1,1,1,2,2]
//输出: 2
// 
// Related Topics 位运算 数组 分治算法


package leetcode.editor.cn.P169_MajorityElement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 排序法
 */
class Solution1 {

    private int partition(int[] arr, int l, int r) {
        int p = l;
        int q = r;
        int v = arr[r];
        while (p < q) {

            while (p < q && arr[p] <= v) {
                p++;
            }
            arr[q] = arr[p];

            while (p < q && arr[q] > v) {
                q--;
            }
            arr[p] = arr[q];
        }
        arr[q] = v;

        return q;
    }

    private void sort(int[] arr, int l, int r) {
        if (r - l < 1) {
            return;
        }
        int i = partition(arr, l, r);
        sort(arr, l, i - 1);
        sort(arr, i + 1, r);
    }

    public int majorityElement(int[] nums) {
        sort(nums, 0, nums.length - 1);
        return nums[nums.length / 2];
    }
}

/**
 * 随机法
 */
class Solution2 {
    private int getCount(int[] nums, int num) {
        int count = 0;
        for (int n : nums) {
            if (n == num) {
                count++;
            }
        }

        return count;
    }

    public int majorityElement(int[] nums) {
        Random random = new Random();
        int[] memory = new int[nums.length];
        while (true) {
            int index = random.nextInt(nums.length);
            if (memory[index] != 0) {
                continue;
            }

            int num = nums[index];
            if (getCount(nums, num) > nums.length / 2) {
                return num;
            }

            memory[index] = 1;
        }
    }
}

/**
 * 分治法
 */
class Solution3 {
    private int getCount(int[] nums, int l, int r, int num) {
        int count = 0;
        for (int i = l; i <= r; i++) {
            if (nums[i] == num) {
                count++;
            }
        }

        return count;
    }

    private int getMost(int[] nums, int l, int r) {
        if (l == r) {
            return nums[l];
        }

        int mid = (l + r) / 2;
        int left = getMost(nums, l, mid);
        int right = getMost(nums, mid + 1, r);
        if (left == right) {
            return left;
        }

        int lCount = getCount(nums, l, mid, left);
        int rCount = getCount(nums, mid + 1, r, right);
        return lCount > rCount ? left : right;
    }

    public int majorityElement(int[] nums) {
        return getMost(nums, 0, nums.length - 1);
    }
}

/**
 * 投票法
 */
class Solution4 {
    public int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}

class Solution {
    public int majorityElement(int[] nums) {
        return new Solution2().majorityElement(nums);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class SolutionTest {

    private Solution solution = new Solution();

    private void test(int expected, int[] input) {
        int actual = solution.majorityElement(input);
        Assertions.assertEquals(expected, actual);
    }

    /* [3,2,3] */
    @Test
    public void test1() {
        int[] input = {3, 2, 3};
        test(3, input);
    }

    /* [2,2,1,1,1,2,2] */
    @Test
    public void test2() {
        int[] input = {2, 2, 1, 1, 1, 2, 2};
        test(2, input);
    }
}
