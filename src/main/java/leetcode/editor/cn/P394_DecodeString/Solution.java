//给定一个经过编码的字符串，返回它解码后的字符串。 
//
// 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。 
//
// 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。 
//
// 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。 
//
// 示例: 
//
// 
//s = "3[a]2[bc]", 返回 "aaabcbc".
//s = "3[a2[c]]", 返回 "accaccacc".
//s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
// 
// Related Topics 栈 深度优先搜索


package leetcode.editor.cn.P394_DecodeString;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

class SolutionTest {

    private void test(String expected, String input) {
        String actual = new Solution().decodeString(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test1() {
        test("aaabcbc", "3[a]2[bc]");
    }

    @Test
    public void test2() {
        test("accaccacc", "3[a2[c]]");
    }

    @Test
    public void test3() {
        test("abcabccdcdcdef", "2[abc]3[cd]ef");
    }
}

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 栈方案
 * <p>
 * Success:
 * Runtime:1 ms, faster than 90.66% of Java online submissions.
 * Memory Usage:37.7 MB, less than 5.26% of Java online submissions.
 */
class Solution1 {
    public String decodeString(String s) {
        Deque<String> strStack = new ArrayDeque<>();
        Deque<Integer> numStack = new ArrayDeque<>();

        int num = 0;
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < s.length(); index++) {
            char c = s.charAt(index);

            if (Character.isDigit(c)) {
                /** Append number */
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                /** Save old state and create new state */
                numStack.addFirst(num);
                num = 0;
                strStack.addFirst(builder.toString());
                builder = new StringBuilder();
            } else if (c == ']') {
                /** Merge new state into old state */
                StringBuilder localBuilder = builder;
                builder = new StringBuilder();
                builder.append(strStack.removeFirst());
                int count = numStack.removeFirst();
                for (int i = 0; i < count; i++) {
                    builder.append(localBuilder);
                }
            } else {
                /** Append char */
                builder.append(c);
            }
        }

        return builder.toString();
    }
}

/**
 * 递归方案
 * <p>
 * Success:
 * Runtime:1 ms, faster than 90.66% of Java online submissions.
 * Memory Usage:37.5 MB, less than 5.26% of Java online submissions.
 */
class Solution2 {
    public String decodeString(String s) {
        int[] indexPair = {0, -1};
        return decode(s, indexPair).toString();
    }

    private StringBuilder decode(String s, int[] indexPair) {
        int num = 0;
        StringBuilder builder = new StringBuilder();

        for (int index = indexPair[0]; index < s.length(); index++) {
            char c = s.charAt(index);
            if (Character.isDigit(c)) {
                /** Append number */
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                /** Append sub-string */
                int[] subIndexPair = {index + 1, -1};
                StringBuilder subBuilder = decode(s, subIndexPair);
                for (int j = 0; j < num; j++) {
                    builder.append(subBuilder);
                }
                // update index
                index = subIndexPair[1];
                // reset number
                num = 0;
            } else if (c == ']') {
                /** Mark new index and return current builder */
                indexPair[1] = index;
                return builder;
            } else {
                /** Append char */
                builder.append(c);
            }
        }

        return builder;
    }
}

class Solution {
    public String decodeString(String s) {
        return new Solution2().decodeString(s);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

