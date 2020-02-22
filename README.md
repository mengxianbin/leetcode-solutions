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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {

    private Solver solver = SolverManager.getSolver();

    private void test(Object expected, Object... args) {
        Object actual = solver.solve(args);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test1() {
    
        /* ${question.testCase} */
    
        test(null, null);
    }
}

interface Solver {
    Object solve(Object... args);
}

class Solution1 implements Solver {

    @Override
    public Object solve(Object... args) {
        return null;
    }
}

class SolverManager {
    static Solver getSolver() {
        return new Solution1();
    }
}

${question.code}
```

---
