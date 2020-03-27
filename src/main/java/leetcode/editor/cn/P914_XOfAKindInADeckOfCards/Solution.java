//给定一副牌，每张牌上都写着一个整数。 
//
// 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组： 
//
// 
// 每组都有 X 张牌。 
// 组内所有的牌上都写着相同的整数。 
// 
//
// 仅当你可选的 X >= 2 时返回 true。 
//
// 
//
// 示例 1： 
//
// 输入：[1,2,3,4,4,3,2,1]
//输出：true
//解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
// 
//
// 示例 2： 
//
// 输入：[1,1,1,2,2,2,3,3]
//输出：false
//解释：没有满足要求的分组。
// 
//
// 示例 3： 
//
// 输入：[1]
//输出：false
//解释：没有满足要求的分组。
// 
//
// 示例 4： 
//
// 输入：[1,1]
//输出：true
//解释：可行的分组是 [1,1]
// 
//
// 示例 5： 
//
// 输入：[1,1,2,2,2,2]
//输出：true
//解释：可行的分组是 [1,1]，[2,2]，[2,2]
// 
//
// 
//提示： 
//
// 
// 1 <= deck.length <= 10000 
// 0 <= deck[i] < 10000 
// 
//
// 
// Related Topics 数组 数学


package leetcode.editor.cn.P914_XOfAKindInADeckOfCards;

import leetcode.editor.util.InputParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success:
 * Runtime:23 ms, faster than 9.05% of Java online submissions.
 * Memory Usage:42 MB, less than 5.64% of Java online submissions.
 */
class Solution1 {
    public boolean hasGroupsSizeX(int[] deck) {
        if (deck == null || deck.length < 2) {
            return false;
        }

        Map<Integer, Integer> countMap = new HashMap<>(deck.length);
        for (int num : deck) {
            countMap.compute(num, (k, v) -> v == null ? 1 : v + 1);
        }

        if (countMap.size() > deck.length / 2) {
            return false;
        }

        if (countMap.size() == 1) {
            return true;
        }

        for (int m = 2; m == deck.length || (m >> 1) <= deck.length; m++) {
            int local = m;
            if (countMap.values().stream().allMatch(c -> c % local == 0)) {
                return true;
            }
            if ((m & 1) == 1) {
                m++;
            }
        }

        return false;
    }
}

/**
 * Success:
 * Runtime:9 ms, faster than 48.87% of Java online submissions.
 * Memory Usage:42.5 MB, less than 5.64% of Java online submissions.
 */
class Solution2 {
    public boolean hasGroupsSizeX(int[] deck) {
        if (deck == null || deck.length < 2) {
            return false;
        }

        Map<Integer, Integer> countMap = new HashMap<>(deck.length);
        for (int num : deck) {
            countMap.compute(num, (k, v) -> v == null ? 1 : v + 1);
        }

        if (countMap.size() > deck.length / 2) {
            return false;
        }

        if (countMap.size() == 1) {
            return true;
        }

        int g = deck.length;
        for (Integer count : countMap.values()) {
            if (count == 1) {
                return false;
            }

            g = gcd(g, count);
            if (g == 1) {
                return false;
            }
        }

        return g > 1;
    }

    private int gcd(int x, int y) {
        int a = Math.min(x, y);
        int b = x - a + y;
        int c = b % a;
        return c == 0 ? a : gcd(a, c);
    }
}

/**
 * Success:
 * Runtime:3 ms, faster than 84.46% of Java online submissions.
 * Memory Usage:41.2 MB, less than 5.64% of Java online submissions.
 */
class Solution3 {
    public boolean hasGroupsSizeX(int[] deck) {
        int[] count = new int[10000];
        for (int num : deck) {
            count[num]++;
        }

        int g = deck.length;
        for (int c : count) {
            if (c == 0) {
                continue;
            }

            if (c == 1) {
                return false;
            }
            g = gcd(g, c);
            if (g == 1) {
                return false;
            }
        }

        return g > 1;
    }

    private int gcd(int x, int y) {
        return x == 0 ? y : gcd(y % x, x);
    }
}

class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        return new Solution3().hasGroupsSizeX(deck);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class SolutionTest {

    private InputParser parser = new InputParser();

    private void test(boolean expected, int[] input) {
        boolean actual = new Solution().hasGroupsSizeX(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test1() {
        int[] input = parser.parseArray("// 输入：[1,2,3,4,4,3,2,1]");
        test(true, input);
    }

    @Test
    public void test2() {
        int[] input = parser.parseArray("// 输入：[1,1,1,2,2,2,3,3]");
        test(false, input);
    }

    @Test
    public void test3() {
        int[] input = parser.parseArray("// 输入：[1]");
        test(false, input);
    }

    @Test
    public void test4() {
        int[] input = parser.parseArray("// 输入：[1,1]");
        test(true, input);
    }

    @Test
    public void test5() {
        int[] input = parser.parseArray("// 输入：[1,1,2,2,2,2]");
        test(true, input);
    }

    @Test
    public void test6() {
        int[] input = parser.parseArray("// input:[0,0,0,1,1,1,2,2,2]");
        test(true, input);
    }

    @Test
    public void test7() {
        int[] input = parser.parseArray("// input:[0,0,0,0,0,1,1,1,1,1]");
        test(true, input);
    }
}
