//Given an integer array nums, find the sum of the elements between indices i an
//d j (i ≤ j), inclusive. 
//
// The update(i, val) function modifies nums by updating the element at index i 
//to val. 
//
// Example: 
//
// 
//Given nums = [1, 3, 5]
//
//sumRange(0, 2) -> 9
//update(1, 2)
//sumRange(0, 2) -> 8
//
//
// Note:
//
//
// The array is only modifiable by the update function.
// You may assume the number of calls to update and sumRange function is distrib
//uted evenly.
//
// Related Topics 树状数组 线段树


package leetcode.editor.cn.P307_RangeSumQueryMutable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {

    private Solver solver = SolverManager.getSolver();

    private void test(int[] sum, int[] numArray, int[] range, int[] update) {
        solver.init(numArray);
        int s1 = solver.sumRange(range[0], range[1]);
        Assertions.assertEquals(sum[0], s1);

        solver.update(update[0], update[1]);
        int s2 = solver.sumRange(range[0], range[1]);
        Assertions.assertEquals(sum[1], s2);
    }

    @Test
    public void test1() {

        /* ["NumArray","sumRange","update","sumRange"]
[[[1,3,5]],[0,2],[1,2],[0,2]] */

        int[] numArray = {1, 3, 5};
        int[] range = {0, 2};
        int[] update = {1, 2};
        int[] sum = {9, 8};
        test(sum, numArray, range, update);
    }

    @Test
    public void testArrayEquals() {
        int[] a1 = {1, 2, 3};
        int[] a2 = {1, 2, 3};
        Assertions.assertNotEquals(a1, a2);
        Assertions.assertArrayEquals(a1, a2);
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
interface Solver {
    void init(int[] nums);

    void update(int i, int val);

    int sumRange(int i, int j);
}

class Solution1 implements Solver {

    int[] segmentTree;
    int count;

    private void buildTree(int[] tree, int[] nums, int pos, int left, int right) {
        if (left > right) {
            return;
        }
        if (left == right) {
            tree[pos] = nums[left];
            return;
        }

        int mid = (left + right) / 2;
        int posLeft = pos * 2 + 1;
        int posRight = pos * 2 + 2;
        buildTree(tree, nums, posLeft, left, mid);
        buildTree(tree, nums, posRight, mid + 1, right);
        tree[pos] = tree[posLeft] + tree[posRight];
    }

    @Override
    public void init(int[] nums) {
        int length = 2;
        for (int l = nums.length; l > 0; l >>= 1) {
            length <<= 1;
        }
        int[] tree = new int[length];
        buildTree(tree, nums, 0, 0, nums.length - 1);

        this.segmentTree = tree;
        this.count = nums.length;
    }

    private void updateTree(int[] tree, int pos, int left, int right, int index, int value) {
        if (index < left || index > right) {
            return;
        }

        if (left == right) {
            tree[pos] = value;
            return;
        }

        int mid = (left + right) / 2;
        int posLeft = pos * 2 + 1;
        int posRight = pos * 2 + 2;

        if (index <= mid) {
            updateTree(tree, posLeft, left, mid, index, value);
        } else {
            updateTree(tree, posRight, mid + 1, right, index, value);
        }

        tree[pos] = tree[posLeft] + tree[posRight];
    }

    @Override
    public void update(int i, int val) {
        updateTree(segmentTree, 0, 0, count - 1, i, val);
    }

    private int sumRange(int[] tree, int pos, int left, int right, int qLeft, int qRight) {
        if (qLeft > right || qRight < left) {
            return 0;
        }

        if (qLeft <= left && qRight >= right) {
            return tree[pos];
        }

        int mid = (left + right) / 2;
        int posLeft = pos * 2 + 1;
        int posRight = pos * 2 + 2;
        int sumLeft = sumRange(tree, posLeft, left, mid, qLeft, qRight);
        int sumRight = sumRange(tree, posRight, mid + 1, right, qLeft, qRight);
        return sumLeft + sumRight;
    }

    @Override
    public int sumRange(int i, int j) {
        return sumRange(segmentTree, 0, 0, count - 1, i, j);
    }
}

class SolverManager {
    static Solver getSolver() {
        return new Solution1();
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
class NumArray {

    private Solver solver = SolverManager.getSolver();

    public NumArray(int[] nums) {
        solver.init(nums);
    }

    public void update(int i, int val) {
        solver.update(i, val);
    }

    public int sumRange(int i, int j) {
        return solver.sumRange(i, j);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
