//给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。 
//
// 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。 
//
// 你可以假设 nums1 和 nums2 不会同时为空。 
//
// 示例 1: 
//
// nums1 = [1, 3]
//nums2 = [2]
//
//则中位数是 2.0
// 
//
// 示例 2: 
//
// nums1 = [1, 2]
//nums2 = [3, 4]
//
//则中位数是 (2 + 3)/2 = 2.5
// 
// Related Topics 数组 二分查找 分治算法


package leetcode.editor.cn.P4_MedianOfTwoSortedArrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int i = 0;
        int j = 0;
        int cur = Integer.MIN_VALUE;
        int pre = Integer.MIN_VALUE;
        for (int k = 0; k <= (m + n) / 2; k++) {
            pre = cur;
            if (i < m && (j >= n || nums1[i] < nums2[j])) {
                cur = nums1[i++];
            } else {
                cur = nums2[j++];
            }
        }

        if (((m + n) & 1) == 0) {
            return (cur + pre) / 2.0;
        }

        return cur;
    }
}

class Solution2 {

    private int getKth(int[] nums1, int[] nums2, int start1, int start2, int k) {
        int len1 = nums1.length - start1;
        int len2 = nums2.length - start2;
        if (len1 > len2) {
            return getKth(nums2, nums1, start2, start1, k);
        }

        if (len1 == 0) {
            return nums2[start2 + k - 1];
        }

        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int half1 = Math.min(len1, k / 2);
        int half2 = Math.min(len2, k / 2);
        if (nums1[start1 + half1 - 1] < nums2[start2 + half2 - 1]) {
            return getKth(nums1, nums2, start1 + half1, start2, k - half1);
        }

        return getKth(nums1, nums2, start1, start2 + half2, k - half2);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int left = (nums1.length + nums2.length + 1) / 2;
        int right = (nums1.length + nums2.length + 2) / 2;
        int k1 = getKth(nums1, nums2, 0, 0, left);
        int k2 = getKth(nums1, nums2, 0, 0, right);
        return (k1 + k2) * .5;
    }
}

class Solution3 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int iMin = 0;
        int iMax = m;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = (m + n + 1) / 2 - i;

            if (i != 0 && j != n && nums1[i - 1] > nums2[j]) {
                iMax = i - 1;
            } else if (i != m && j != 0 && nums1[i] < nums2[j - 1]) {
                iMin = i + 1;
            } else {
                int lMax;
                if (i == 0) {
                    lMax = nums2[j - 1];
                }
                else if (j == 0) {
                    lMax = nums1[i - 1];
                }
                else {
                    lMax = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if (((m + n) & 1) == 1) {
                    return lMax;
                }

                int rMin;
                if (i == m) {
                    rMin = nums2[j];
                }
                else if (j == n) {
                    rMin = nums1[i];
                }
                else {
                    rMin = Math.min(nums1[i], nums2[j]);
                }

                return (lMax + rMin) * .5;
            }
        }

        return 0;
    }
}

class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return new Solution3().findMedianSortedArrays(nums1, nums2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class SolutionTest {

    private Solution solution = new Solution();

    private void test(double expected, int[] input1, int[] input2) {
        double actual = solution.findMedianSortedArrays(input1, input2);
        Assertions.assertEquals(expected, actual);
    }

    /* [1,3]
[2] */
    @Test
    public void test1() {
        int[] input1 = {1, 3};
        int[] input2 = {2};
        test(2, input1, input2);
    }

    @Test
    public void test2() {
        int[] input1 = {1, 3};
        int[] input2 = {2, 4};
        test(2.5, input1, input2);
    }

    @Test
    public void test3() {
        int[] input1 = {1, 3, 4};
        int[] input2 = {2};
        test(2.5, input1, input2);
    }
}
