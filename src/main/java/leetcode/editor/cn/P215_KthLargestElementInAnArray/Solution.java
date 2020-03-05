//Find the kth largest element in an unsorted array. Note that it is the kth lar
//gest element in the sorted order, not the kth distinct element. 
//
// Example 1: 
//
// 
//Input: [3,2,1,5,6,4] and k = 2
//Output: 5
// 
//
// Example 2: 
//
// 
//Input: [3,2,3,1,2,4,5,5,6] and k = 4
//Output: 4 
//
// Note: 
//You may assume k is always valid, 1 ≤ k ≤ array's length. 
// Related Topics 堆 分治算法


package leetcode.editor.cn.P215_KthLargestElementInAnArray;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;


//leetcode submit region begin(Prohibit modification and deletion)
interface Solver {
    int findKthLargest(int[] nums, int k);
}

class Solver1 implements Solver {

    private int partition(int[] arr, int l, int r) {
        int m = l;
        int n = r;
        if (arr[m] < arr[n]) {
            m = r;
            n = l;
        }
        int w = (l + r) / 2;
        int u = arr[w] > arr[m] ? m : arr[w] < arr[n] ? n : w;
        int v = arr[u];

        if (u != r) {
            arr[u] = arr[r];
            arr[r] = v;
        }

        int p = l;
        int q = r;
        while (p < q) {
            while (p < q && arr[p] >= v) {
                p++;
            }
            arr[q] = arr[p];

            while (p < q && arr[q] <= v) {
                q--;
            }
            arr[p] = arr[q];
        }

        arr[q] = v;

        return q;
    }

    private void findK(int[] nums, int l, int r, int k) {
        int i = partition(nums, l, r);
        if (i > k) {
            findK(nums, l, i - 1, k);
        } else if (i < k) {
            findK(nums, i + 1, r, k);
        }
    }

    @Override
    public int findKthLargest(int[] nums, int k) {
        if (nums == null) {
            return 0;
        }

        --k;
        if (k < 0 || k >= nums.length) {
            return 0;
        }

        findK(nums, 0, nums.length - 1, k);

        return nums[k];
    }
}

class Solver2 implements Solver {

    @Override
    public int findKthLargest(int[] nums, int k) {
        if (k <= 0 || nums.length < k) {
            return 0;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int n : nums) {
            queue.offer(n);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        return queue.peek();
    }
}

class Solution {
    private Solver solver = new Solver1();

    public int findKthLargest(int[] nums, int k) {
        return solver.findKthLargest(nums, k);
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
    private void test(int expected, int[] nums, int k) {
        Assertions.assertEquals(expected, solution.findKthLargest(nums, k));
    }

    /* [3,2,1,5,6,4]
2 */
    @Test
    public void test1() {
        test(5, new int[]{3, 2, 1, 5, 6, 4}, 2);
    }

    @Test
    public void test2() {
        test(4, new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4);
    }
}
