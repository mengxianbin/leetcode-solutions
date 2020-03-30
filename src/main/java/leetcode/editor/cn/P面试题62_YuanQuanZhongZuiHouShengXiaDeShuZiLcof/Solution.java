//0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。 
//
// 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。 
//
// 
//
// 示例 1： 
//
// 输入: n = 5, m = 3
//输出: 3
// 
//
// 示例 2： 
//
// 输入: n = 10, m = 17
//输出: 2
// 
//
// 
//
// 限制： 
//
// 
// 1 <= n <= 10^5 
// 1 <= m <= 10^6 
// 
//


package leetcode.editor.cn.P面试题62_YuanQuanZhongZuiHouShengXiaDeShuZiLcof;

import leetcode.editor.util.InputParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Time Limit Exceeded
 */
class Solution1 {
    public int lastRemaining(int n, int m) {
        // Queue<Integer> queue = new ArrayDeque<>(n); // Time Limit Exceeded
        Queue<Integer> queue = new LinkedList<>(); // Time Limit Exceeded
        for (int i = 0; i < n; i++) {
            queue.offer(i);
        }

        while (queue.size() > 1) {
            for (int j = 1; j < m; j++) {
                queue.offer(queue.poll());
            }
            queue.poll();
        }

        return queue.poll();
    }
}

/**
 * Success:
 * Runtime:7 ms, faster than 99.88% of Java online submissions.
 * Memory Usage:36.4 MB, less than 100.00% of Java online submissions.
 */
class Solution2 {
    public int lastRemaining(int n, int m) {
        // the final index in the remaining sequence
        int index = 0;
        for (int len = 2; len <= n; len++) {
            // calculate the index in the previous remaining sequence
            index = (index + m) % len;
        }

        // the origin index in the origin sequence
        return index;
    }
}

class Solution {
    public int lastRemaining(int n, int m) {
        return new Solution2().lastRemaining(n, m);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class SolutionTest {

    private InputParser parser = new InputParser();

    private void test(int expected, int input1, int input2) {
        int actual = new Solution().lastRemaining(input1, input2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test1() {
        test(3, 5, 3);
    }

    @Test
    public void test2() {
        test(2, 10, 17);
    }
}
