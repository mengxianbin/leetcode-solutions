//给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。 
//
// 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0,
// 2, 5]。 
//
// 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。 
//
// 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？ 
//
// 
//
// 示例： 
//
// 输入: words = ["time", "me", "bell"]
//输出: 10
//说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 2000 
// 1 <= words[i].length <= 7 
// 每个单词都是小写字母 。 
// 
//


package leetcode.editor.cn.P820_ShortEncodingOfWords;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success:
 * Runtime:23 ms, faster than 66.92% of Java online submissions.
 * Memory Usage:41.6 MB, less than 16.66% of Java online submissions.
 */
class Solution1 {
    public int minimumLengthEncoding(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        if (words.length == 1) {
            return words[0].length() + 1;
        }

        Set<String> wordSet = new HashSet<>();
        Collections.addAll(wordSet, words);

        Arrays.sort(words, (w1, w2) -> w2.length() - w1.length());
        int result = 0;
        for (String word : words) {
            if (wordSet.remove(word)) {
                result += word.length() + 1;
            }
            for (int i = 1; i < word.length(); i++) {
                wordSet.remove(word.substring(i));
            }
        }

        return result;
    }
}

/**
 * Success:
 * Runtime:15 ms, faster than 98.97% of Java online submissions.
 * Memory Usage:44 MB, less than 6.06% of Java online submissions.
 */
class Solution2 {
    class TreeNode {
        private TreeNode[] children = new TreeNode[26];

        private int insert(String word) {
            boolean isNew = false;
            TreeNode current = this;
            for (int i = word.length() - 1; i >= 0; i--) {
                char c = word.charAt(i);
                TreeNode child = current.children[c - 'a'];
                if (child == null) {
                    child = current.children[c - 'a'] = new TreeNode();
                    isNew = true;
                }
                current = child;
            }

            return isNew ? word.length() + 1 : 0;
        }
    }

    public int minimumLengthEncoding(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        if (words.length == 1) {
            return words[0].length() + 1;
        }

        int result = 0;
        TreeNode tree = new TreeNode();
        Arrays.sort(words, (w1, w2) -> w2.length() - w1.length());
        for (String word : words) {
            result += tree.insert(word);
        }

        return result;
    }
}


class Solution {
    public int minimumLengthEncoding(String[] words) {
        return new Solution2().minimumLengthEncoding(words);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class SolutionTest {

    private void test(int expected, String[] input) {
        int actual = new Solution().minimumLengthEncoding(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test1() {
        String[] input = {"time", "me", "bell"};
        test(10, input);
    }

    @Test
    public void test2() {
        String[] input = {"me", "time"};
        test(5, input);
    }
}
