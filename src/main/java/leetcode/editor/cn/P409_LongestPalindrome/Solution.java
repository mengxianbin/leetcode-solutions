//Given a string which consists of lowercase or uppercase letters, find the leng
//th of the longest palindromes that can be built with those letters. 
//
// This is case sensitive, for example "Aa" is not considered a palindrome here.
// 
//
// Note: 
//Assume the length of given string will not exceed 1,010.
// 
//
// Example: 
// 
//Input:
//"abccccdd"
//
//Output:
//7
//
//Explanation:
//One longest palindrome that can be built is "dccaccd", whose length is 7.
// 
// Related Topics 哈希表


package leetcode.editor.cn.P409_LongestPalindrome;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1 {
    public int longestPalindrome1(String s) {
        int[] countArray = new int[52];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c >= 'a' ? c - 'a' + 26 : c - 'A';
            ++countArray[index];
        }

        int answer = 0;
        for (int i : countArray) {
            answer += i / 2 * 2;
            if (i % 2 == 1 && answer % 2 == 0) {
                ++answer;
            }
        }

        return answer;
    }
}

class Solution2 {
    public int longestPalindrome1(String s) {
        int[] countArray = new int[128];
        for (int i = 0; i < s.length(); i++) {
            ++countArray[s.charAt(i)];
        }

        int answer = 0;
        for (int i : countArray) {
            answer += i / 2 * 2;
            if (i % 2 == 1 && answer % 2 == 0) {
                ++answer;
            }
        }

        return answer;
    }
}

class Solution3 {
    public int longestPalindrome1(String s) {
        int[] countArray = new int[128];
        for (char c : s.toCharArray()) {
            ++countArray[c];
        }

        int answer = 0;
        for (int i : countArray) {
            answer += i / 2 * 2;
            if (i % 2 == 1 && answer % 2 == 0) {
                ++answer;
            }
        }

        return answer;
    }
}

class Solution {
    public int longestPalindrome(String s) {
        return new Solution3().longestPalindrome1(s);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

