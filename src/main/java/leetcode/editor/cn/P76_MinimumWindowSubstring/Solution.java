//Given a string S and a string T, find the minimum window in S which will conta
//in all the characters in T in complexity O(n). 
//
// Example: 
//
// 
//Input: S = "ADOBECODEBANC", T = "ABC"
//Output: "BANC"
// 
//
// Note: 
//
// 
// If there is no such window in S that covers all characters in T, return the e
//mpty string "". 
// If there is such window, you are guaranteed that there will always be only on
//e unique minimum window in S. 
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window

package leetcode.editor.cn.P76_MinimumWindowSubstring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class SolutionTest {
    private void test(String s, String t, Object expected) {
        Solution solution = new Solution();
        Object actual = solution.minWindow(s, t);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test1() {
        String S = "ADOBECODEBANC", T = "ABC";
        Object expected = "BANC";
        test(S, T, expected);
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private <T> int getValue(Map<T, Integer> map, T key) {
        return map.getOrDefault(key, 0);
    }

    private <T> void addValue(Map<T, Integer> map, T key, int value) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + value);
        } else {
            map.put(key, value);
        }
    }

    private Map<Character, Integer> strToMap(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : str.toCharArray()) {
            addValue(map, c, 1);
        }
        return map;
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> tMap = strToMap(t);
        if (t.isEmpty()) {
            return "";
        }

        String result = "";
        int begin = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0; end < s.length(); end++) {
            char endChar = s.charAt(end);
            addValue(map, endChar, 1);

            for (Character beginChar = s.charAt(begin); begin < end
                    && getValue(map, beginChar) > getValue(tMap, beginChar); ++begin, beginChar = s.charAt(begin)) {
                addValue(map, beginChar, -1);
            }

            if (tMap.entrySet().stream().noneMatch(e ->
                    getValue(map, e.getKey()) < e.getValue())) {
                int len = end - begin + 1;
                if (result.isEmpty() || len < result.length()) {
                    result = s.substring(begin, end + 1);
                }
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

