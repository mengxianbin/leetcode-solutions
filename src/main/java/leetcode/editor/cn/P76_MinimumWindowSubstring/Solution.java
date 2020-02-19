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

import javafx.util.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SolutionTest {
    private void testIntPool(Object a, Object b, boolean expected) {
        Assertions.assertEquals(expected, a == b);
    }

    @Test
    public void testIntPool1() {
        testIntPool(127, 127, true);
    }

    @Test
    public void testIntPool2() {
        testIntPool(128, 128, false);
    }

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

    @Test
    public void test2() {
        String S = "aacccb", T = "ab";
        Object expected = "acccb";
        test(S, T, expected);
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private <T> int getValue(Map<T, Integer> map, T key) {
        return map.getOrDefault(key, 0);
    }

    private <T> void addValue(Map<T, Integer> map, T key, int delta) {
        int oldValue = map.getOrDefault(key, 0);
        map.put(key, oldValue + delta);
    }

    private Map<Character, Integer> strToMap(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : str.toCharArray()) {
            addValue(map, c, 1);
        }
        return map;
    }

    public String solve1(String s, String t) {
        Map<Character, Integer> required = strToMap(t);
        if (t.isEmpty()) {
            return "";
        }

        String result = "";
        int begin = 0;
        Map<Character, Integer> window = new HashMap<>();
        for (int end = 0; end < s.length(); end++) {
            char endChar = s.charAt(end);
            addValue(window, endChar, 1);

            for (Character beginChar = s.charAt(begin); begin <= end - required.size()
                    && getValue(window, beginChar) > getValue(required, beginChar); ++begin, beginChar = s.charAt(begin)) {
                addValue(window, beginChar, -1);
            }

            if (required.entrySet().stream().noneMatch(e ->
                    getValue(window, e.getKey()) < e.getValue())) {
                int len = end - begin + 1;
                if (result.isEmpty() || len < result.length()) {
                    result = s.substring(begin, end + 1);
                }
            }
        }

        return result;
    }

    public String solve2(String s, String t) {
        Map<Character, Integer> required = strToMap(t);
        if (t.isEmpty()) {
            return "";
        }

        String result = "";
        int begin = 0;
        int count = 0;
        Map<Character, Integer> window = new HashMap<>();
        for (int end = 0; end < s.length(); end++) {
            char endChar = s.charAt(end);
            int endRequireCount = required.getOrDefault(endChar, 0);
            if (endRequireCount > 0) {
                int endCount = window.getOrDefault(endChar, 0) + 1;
                window.put(endChar, endCount);

                if (endCount == endRequireCount) {
                    ++count;
                }
            }

            while (count == required.size()) {
                Character beginChar = s.charAt(begin);
                int beginRequireCount = required.getOrDefault(beginChar, 0);
                if (beginRequireCount > 0) {
                    if (result.isEmpty() || (end - begin + 1 < result.length())) {
                        result = s.substring(begin, end + 1);
                    }

                    int beginCount = window.get(beginChar) - 1;
                    window.put(beginChar, beginCount);
                    if (beginCount < beginRequireCount) {
                        --count;
                    }
                }

                ++begin;
            }
        }

        return result;
    }

    public String minWindow(String s, String t) {
//        return solve1(s, t);
        return solve2(s, t);
//        return Solution1.solve(s, t);
//        return Solution2.solve(s, t);
    }

    static class Solution1 {
        static String solve(String s, String t) {
            if (s.length() == 0 || t.length() == 0) {
                return "";
            }

            // Dictionary which keeps a count of all the unique characters in t.
            Map<Character, Integer> dictT = new HashMap<>();
            for (int i = 0; i < t.length(); i++) {
                int count = dictT.getOrDefault(t.charAt(i), 0);
                dictT.put(t.charAt(i), count + 1);
            }

            // Number of unique characters in t, which need to be present in the desired window.
            int required = dictT.size();

            // Left and Right pointer
            int l = 0, r = 0;

            // formed is used to keep track of how many unique characters in t
            // are present in the current window in its desired frequency.
            // e.g. if t is "AABC" then the window must have two A's, one B and one C.
            // Thus formed would be = 3 when all these conditions are met.
            int formed = 0;

            // Dictionary which keeps a count of all the unique characters in the current window.
            Map<Character, Integer> windowCounts = new HashMap<>();

            // ans list of the form (window length, left, right)
            int[] ans = {-1, 0, 0};

            while (r < s.length()) {
                // Add one character from the right to the window
                char c = s.charAt(r);
                int count = windowCounts.getOrDefault(c, 0);
                windowCounts.put(c, count + 1);

                // If the frequency of the current character added equals to the
                // desired count in t then increment the formed count by 1.
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                    formed++;
                }

                // Try and co***act the window till the point where it ceases to be 'desirable'.
                while (l <= r && formed == required) {
                    c = s.charAt(l);
                    // Save the smallest window until now.
                    if (ans[0] == -1 || r - l + 1 < ans[0]) {
                        ans[0] = r - l + 1;
                        ans[1] = l;
                        ans[2] = r;
                    }

                    // The character at the position pointed by the
                    // `Left` pointer is no longer a part of the window.
                    windowCounts.put(c, windowCounts.get(c) - 1);
                    if (dictT.containsKey(c) && windowCounts.get(c) < dictT.get(c)) {
                        formed--;
                    }

                    // Move the left pointer ahead, this would help to look for a new window.
                    l++;
                }

                // Keep expanding the window once we are done co***acting.
                r++;
            }

            return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
        }
    }

    static class Solution2 {
        static String solve(String s, String t) {

            if (s.length() == 0 || t.length() == 0) {
                return "";
            }

            Map<Character, Integer> dictT = new HashMap<>();

            for (int i = 0; i < t.length(); i++) {
                int count = dictT.getOrDefault(t.charAt(i), 0);
                dictT.put(t.charAt(i), count + 1);
            }

            int required = dictT.size();

            // Filter all the characters from s into a new list along with their index.
            // The filtering criteria is that the character should be present in t.
            List<Pair<Integer, Character>> filteredS = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (dictT.containsKey(c)) {
                    filteredS.add(new Pair<>(i, c));
                }
            }

            int l = 0, r = 0, formed = 0;
            Map<Character, Integer> windowCounts = new HashMap<>();
            int[] ans = {-1, 0, 0};

            // Look for the characters only in the filtered list instead of entire s.
            // This helps to reduce our search.
            // Hence, we follow the sliding window approach on as small list.
            while (r < filteredS.size()) {
                char c = filteredS.get(r).getValue();
                int count = windowCounts.getOrDefault(c, 0);
                windowCounts.put(c, count + 1);

                if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                    formed++;
                }

                // Try and co***act the window till the point where it ceases to be 'desirable'.
                while (l <= r && formed == required) {
                    c = filteredS.get(l).getValue();

                    // Save the smallest window until now.
                    int end = filteredS.get(r).getKey();
                    int start = filteredS.get(l).getKey();
                    if (ans[0] == -1 || end - start + 1 < ans[0]) {
                        ans[0] = end - start + 1;
                        ans[1] = start;
                        ans[2] = end;
                    }

                    windowCounts.put(c, windowCounts.get(c) - 1);
                    if (dictT.containsKey(c) && windowCounts.get(c) < dictT.get(c)) {
                        formed--;
                    }
                    l++;
                }
                r++;
            }
            return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

