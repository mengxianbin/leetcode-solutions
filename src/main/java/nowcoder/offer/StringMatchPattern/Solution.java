package nowcoder.offer.StringMatchPattern;

public class Solution {

    private boolean match(char[] str, char[] pattern, int i, int j) {
        if (j == pattern.length) {
            return i == str.length;
        }

        if (j + 1 < pattern.length && pattern[j + 1] == '*') {
            if (i < str.length && (pattern[j] == '.' || pattern[j] == str[i])) {
                if (match(str, pattern, i + 1, j)) {
                    return true;
                }
            }

            return match(str, pattern, i, j + 2);
        }

        if (i < str.length && (pattern[j] == '.' || pattern[j] == str[i])) {
            return match(str, pattern, i + 1, j + 1);
        }

        return false;
    }

    public boolean match(char[] str, char[] pattern) {
        return match(str, pattern, 0, 0);
    }
}
