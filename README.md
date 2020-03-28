# Leetcode Solutions

## Leetcode-Editor Plugin Configuration

> https://github.com/shuzijun/leetcode-editor

* CodeFileName

```shell script
P${question.frontendQuestionId}_$!velocityTool.camelCaseName(${question.titleSlug})/Solution
```

* Code Template

```text
${question.content}

package leetcode.editor.cn.P${question.frontendQuestionId}_$!velocityTool.camelCaseName(${question.titleSlug});

import leetcode.editor.util.InputParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

${question.code}

class SolutionTest {

    private InputParser parser = new InputParser();

    private void test(int expected, int input) {
        int actual = new Solution().solve(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test1() {
        test(expected, input);
    }
}
```

---
