//有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？ 
//
// 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。 
//
// 你允许： 
//
// 
// 装满任意一个水壶 
// 清空任意一个水壶 
// 从一个水壶向另外一个水壶倒水，直到装满或者倒空 
// 
//
// 示例 1: (From the famous "Die Hard" example) 
//
// 输入: x = 3, y = 5, z = 4
//输出: True
// 
//
// 示例 2: 
//
// 输入: x = 2, y = 6, z = 5
//输出: False
// 
// Related Topics 数学


package leetcode.editor.cn.P365_WaterAndJugProblem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * StackOverflow
 */
class Solution1 {
    public boolean canMeasureWater(int a, int b, int x, int y, int z, Set<Long> seen) {
        long kX = x;
        long kY = y;
        if (kX > kY) {
            long t = kX;
            kX = kY;
            kY = t;
        }
        long key = kX << 32 | kY;
        if (seen.contains(key)) {
            return false;
        }

        if (x == z || y == z || x + y == z) {
            return true;
        }

        if (canMeasureWater(a, b, 0, y, z, seen)) {
            return true;
        }
        if (canMeasureWater(a, b, x, 0, z, seen)) {
            return true;
        }
        if (canMeasureWater(a, b, a, y, z, seen)) {
            return true;
        }
        if (canMeasureWater(a, b, x, b, z, seen)) {
            return true;
        }

        int sum = x + y;
        int bMin = Math.min(x + y, b);
        if (canMeasureWater(a, b, sum - bMin, bMin, z, seen)) {
            return true;
        }

        int aMin = Math.min(x + y, a);
        if (canMeasureWater(a, b, aMin, sum - aMin, z, seen)) {
            return true;
        }

        seen.add(key);

        return false;
    }

    public boolean canMeasureWater(int x, int y, int z) {
        Set<Long> seen = new HashSet<>();
        return canMeasureWater(x, y, x, y, z, seen);
    }
}

/**
 * 1000 ms / 5.36 %
 * 307.9 MB / 5.43 %
 */
class Solution2 {
    private long toKey(long a, long b) {
        return a << 32 | b;
    }

    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) {
            return false;
        }

        Queue<Long> tasks = new LinkedBlockingQueue<>();
        Set<Long> seen = new HashSet<>();

        tasks.offer(((long) x) << 32 | y);
        Long k;
        while ((k = tasks.poll()) != null) {
            long a = k >> 32;
            long b = k & ~(a << 32);
            long sum = a + b;
            if (seen.contains(k)) {
                continue;
            }

            seen.add(k);

            if (a == z || b == z || (a + b) == z) {
                return true;
            }

            tasks.offer(toKey(0, b));
            tasks.offer(toKey(a, 0));
            tasks.offer(toKey(x, b));
            tasks.offer(toKey(a, y));

            long xMin = Math.min(sum, x);
            tasks.offer(toKey(xMin, sum - xMin));
            long yMin = Math.min(sum, y);
            tasks.offer(toKey(sum - yMin, yMin));
        }

        return false;
    }
}

/**
 * x -= y
 * 1 ms / 38.76 %
 * 36.3 MB / 13.93 %
 *
 * x %= y
 * 0 ms, 100.00 %
 * 36.3 MB, 13.93 %
 */
class Solution3 {
    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) {
            return false;
        }

        if (z == 0 || x == z || y == z || x + y == z) {
            return true;
        }

        if (x == y || x == 0 || y == 0) {
            return false;
        }

        for (; x % y != 0; x %= y) {
            if (x < y) {
                int t = x;
                x = y;
                y = t;
            }
        }

        return z % y == 0;
    }
}

class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
        return new Solution3().canMeasureWater(x, y, z);
    }
}

//leetcode submit region end(Prohibit modification and deletion)
class SolutionTest {
    private void test(boolean expected, int x, int y, int z) {
        boolean actual = new Solution().canMeasureWater(x, y, z);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test1() {
        test(true, 3, 5, 4);
    }

    @Test
    public void test2() {
        test(false, 2, 6, 5);
    }
}
