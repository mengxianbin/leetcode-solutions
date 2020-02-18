//Given a pattern and a string str, find if str follows the same pattern. 
//
// Here follow means a full match, such that there is a bijection between a lett
//er in pattern and a non-empty word in str. 
//
// Example 1: 
//
// 
//Input: pattern = "abba", str = "dog cat cat dog"
//Output: true 
//
// Example 2: 
//
// 
//Input:pattern = "abba", str = "dog cat cat fish"
//Output: false 
//
// Example 3: 
//
// 
//Input: pattern = "aaaa", str = "dog cat cat dog"
//Output: false 
//
// Example 4: 
//
// 
//Input: pattern = "abba", str = "dog dog dog dog"
//Output: false 
//
// Notes: 
//You may assume pattern contains only lowercase letters, and str contains lower
//case letters that may be separated by a single space. 
// Related Topics 哈希表


package leetcode.editor.cn.P290_WordPattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class SolutionTest {
    private void test(String pattern, String str, boolean expected) {
        Solution solution = new Solution();
        boolean result = solution.wordPattern(pattern, str);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test1() {
        String pattern = "abba", str = "dog cat cat dog";
        test(pattern, str, true);
    }


    @Test
    public void test2() {
        String pattern = "abba", str = "dog cat cat fish";
        test(pattern, str, false);
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private boolean solve1(String pattern, String str) {
        String[] wordArray = str.split(" ");
        if (wordArray.length != pattern.length()) {
            return false;
        }

        Set<String> wordSet = new HashSet<>();

        Map<Character, Integer> patternIndices = new HashMap<>();
        for (int patternIndex = 0; patternIndex < pattern.length(); patternIndex++) {
            Character patternChar = pattern.charAt(patternIndex);
            String word = wordArray[patternIndex];

            Integer firstMatchIndex = patternIndices.get(patternChar);
            if (firstMatchIndex == null) {
                if (wordSet.contains(word)) {
                    return false;
                }
                patternIndices.put(patternChar, patternIndex);
                wordSet.add(word);
            } else {
                String firstMatchWord = wordArray[firstMatchIndex];
                if (!word.equals(firstMatchWord)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean solve2(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }

        Map<String, Character> wordPatternMap = new HashMap<>();
        for (int index = 0; index < words.length; index++) {
            String word = words[index];
            Character patternChar = pattern.charAt(index);
            if (wordPatternMap.containsKey(word)) {
                if (wordPatternMap.get(word) != patternChar) {
                    return false;
                }
            } else {
                if (wordPatternMap.containsValue(patternChar)) {
                    return false;
                }

                wordPatternMap.put(word, patternChar);
            }
        }

        return true;
    }

    private boolean solve3(String pattern, String str) {
        Map<String, Character> wordPatternMap = new HashMap<>();
        StringBuilder wordBuilder = new StringBuilder();
        int patternIndex = 0;

        for (int strIndex = 0; strIndex <= str.length(); ++strIndex) {
            if (strIndex < str.length() && str.charAt(strIndex) != ' ') {
                wordBuilder.append(str.charAt(strIndex));
                continue;
            }

            if (patternIndex == pattern.length()) {
                return false;
            }

            String word = wordBuilder.toString();
            wordBuilder.delete(0, wordBuilder.length());

            char patternChar = pattern.charAt(patternIndex);
            if (wordPatternMap.containsKey(word)) {
                if (wordPatternMap.get(word) != patternChar) {
                    return false;
                }
            } else {
                if (wordPatternMap.containsValue(patternChar)) {
                    return false;
                }
                wordPatternMap.put(word, patternChar);
            }

            ++patternIndex;
        }

        return patternIndex == pattern.length();
    }

    public boolean wordPattern(String pattern, String str) {
        return solve3(pattern, str);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

