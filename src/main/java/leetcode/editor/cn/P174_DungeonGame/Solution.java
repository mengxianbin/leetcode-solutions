//
// The demons had captured the princess (P) and imprisoned her in the bottom-rig
//ht corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D gri
//d. Our valiant knight (K) was initially positioned in the top-left room and must
// fight his way through the dungeon to rescue the princess. 
//
// The knight has an initial health point represented by a positive integer. If 
//at any point his health point drops to 0 or below, he dies immediately. 
//
// Some of the rooms are guarded by demons, so the knight loses health (negative
// integers) upon entering these rooms; other rooms are either empty (0's) or cont
//ain magic orbs that increase the knight's health (positive integers). 
//
// In order to reach the princess as quickly as possible, the knight decides to 
//move only rightward or downward in each step. 
//
// 
//
// Write a function to determine the knight's minimum initial health so that he 
//is able to rescue the princess. 
//
// For example, given the dungeon below, the initial health of the knight must b
//e at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN. 
//
// 
// 
// 
// -2 (K) 
// -3 
// 3 
// 
// 
// -5 
// -10 
// 1 
// 
// 
// 10 
// 30 
// -5 (P) 
// 
// 
// 
//
// 
//
// Note: 
//
// 
// The knight's health has no upper bound. 
// Any room can contain threats or power-ups, even the first room the knight ent
//ers and the bottom-right room where the princess is imprisoned. 
// 
// Related Topics 二分查找 动态规划


package leetcode.editor.cn.P174_DungeonGame;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {

    private void test(Object expected, Object... args) {
        Object actual = SolutionManager.solver.solve(args);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test1() {

        int[][] input = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5},
        };

        test(7, (Object) input);
    }
}


//leetcode submit region begin(Prohibit modification and deletion)
interface Solver {
    Object solve(Object... args);
}

class Solution1 implements Solver {

    @Override
    public Object solve(Object... args) {
        int[][] dungeon = (int[][]) args[0];

        int row = dungeon.length;
        int column = dungeon[0].length;
        int[][] life = new int[row][column];
        life[row - 1][column - 1] = Math.max(1, 1 - dungeon[row - 1][column - 1]);

        for (int i = row - 2; i >= 0; i--) {
            life[i][column - 1] = Math.max(1, life[i + 1][column - 1] - dungeon[i][column - 1]);
        }
        for (int j = column - 2; j >= 0; j--) {
            life[row - 1][j] = Math.max(1, life[row - 1][j + 1] - dungeon[row - 1][j]);
        }

        for (int i = row - 2; i >= 0; i--) {
            for (int j = column - 2; j >= 0; j--) {
                int lifeMin = Math.min(life[i][j + 1], life[i + 1][j]);
                life[i][j] = Math.max(1, lifeMin - dungeon[i][j]);
            }
        }

        return life[0][0];
    }
}

class SolutionManager {
    static Solver solver = new Solution1();
}

class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        return (int) SolutionManager.solver.solve((Object) dungeon);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
