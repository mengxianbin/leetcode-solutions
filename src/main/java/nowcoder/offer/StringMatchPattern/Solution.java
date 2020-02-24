package nowcoder.offer.StringMatchPattern;

public class Solution {

    private boolean match(char[] str, char[] pattern, int i, int j) {
        if (i == str.length && j == pattern.length) {
            return true;
        }
        if (i == str.length || j == pattern.length) {
            return false;
        }

        if (j + 1 < pattern.length && pattern[j + 1] == '*') {
            if (pattern[j] == '.' || pattern[j] == str[i]) {
                if (match(str, pattern, i + 1, j)) {
                    return true;
                }
            }

            return match(str, pattern, i + 1, j + 2);
        }

        if (pattern[j] == '.' || pattern[j] == str[i]) {
            return match(str, pattern, i + 1, j + 1);
        }

        return false;
    }

    public boolean match(char[] str, char[] pattern) {
        return match(str, pattern, 0, 0);
    }
}
