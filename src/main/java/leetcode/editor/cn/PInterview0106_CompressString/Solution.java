//字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没
//有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。 
//
// 示例1: 
//
// 
// 输入："aabcccccaaa"
// 输出："a2b1c5a3"
// 
//
// 示例2: 
//
// 
// 输入："abbccd"
// 输出："abbccd"
// 解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
// 
//
// 提示： 
//
// 
// 字符串长度在[0, 50000]范围内。 
// 
// Related Topics 字符串


package leetcode.editor.cn.PInterview0106_CompressString;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1 {
    public String compressString(String S) {
        if (S == null || S.isEmpty()) {
            return "";
        }

        int len = S.length();
        char c = S.charAt(0);
        int count = 1;

        StringBuilder builder = new StringBuilder();
        for (int j = 1; j < len; j++) {
            if (S.charAt(j) != c) {
                builder.append(c);
                builder.append(count);

                c = S.charAt(j);
                count = 1;
            }
            else {
                count++;
            }
        }

        builder.append(c);
        builder.append(count);

        return builder.length() < len ? builder.toString() : S;
    }
}

class Solution {
    public String compressString(String S) {
        int i = 0;
        int j = 0;
        StringBuilder builder = new StringBuilder();
        while (i < S.length()) {
            while (j < S.length() && S.charAt(j) == S.charAt(i)) {
                j++;
            }

            builder.append(S.charAt(i));
            builder.append(j - i);
            i = j;
        }

        return builder.length() < S.length() ? builder.toString() : S;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
