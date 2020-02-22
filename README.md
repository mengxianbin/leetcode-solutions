# Leetcode Solutions

## Leetcode-Editor Plugin Configuration

* CodeFileName

```shell script
P${question.frontendQuestionId}_$!velocityTool.camelCaseName(${question.titleSlug})/Solution
```

* Code Template

```text
${question.content}

package leetcode.editor.cn.P${question.frontendQuestionId}_$!velocityTool.camelCaseName(${question.titleSlug});

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;


//leetcode submit region begin(Prohibit modification and deletion)
interface Solver {
}

class Solver1 implements Solver {
}

${question.code}

class SolutionTest {

    private Solution solution = new Solution();

    /**
     * input: (expected, args...)
     * solve: solution
     * assert: equals(expected, actual)  
     */
    private void test() {
        
    }

    /* ${question.testCase} */
    @Test
    public void test1() {
        test();
    }
}
```

---
