//We distribute some number of candies, to a row of n = num_people people in the
// following way: 
//
// We then give 1 candy to the first person, 2 candies to the second person, and
// so on until we give n candies to the last person. 
//
// Then, we go back to the start of the row, giving n + 1 candies to the first p
//erson, n + 2 candies to the second person, and so on until we give 2 * n candies
// to the last person. 
//
// This process repeats (with us giving one more candy each time, and moving to 
//the start of the row after we reach the end) until we run out of candies. The la
//st person will receive all of our remaining candies (not necessarily one more th
//an the previous gift). 
//
// Return an array (of length num_people and sum candies) that represents the fi
//nal distribution of candies. 
//
// 
// Example 1: 
//
// 
//Input: candies = 7, num_people = 4
//Output: [1,2,3,1]
//Explanation:
//On the first turn, ans[0] += 1, and the array is [1,0,0,0].
//On the second turn, ans[1] += 2, and the array is [1,2,0,0].
//On the third turn, ans[2] += 3, and the array is [1,2,3,0].
//On the fourth turn, ans[3] += 1 (because there is only one candy left), and th
//e final array is [1,2,3,1].
// 
//
// Example 2: 
//
// 
//Input: candies = 10, num_people = 3
//Output: [5,2,3]
//Explanation: 
//On the first turn, ans[0] += 1, and the array is [1,0,0].
//On the second turn, ans[1] += 2, and the array is [1,2,0].
//On the third turn, ans[2] += 3, and the array is [1,2,3].
//On the fourth turn, ans[0] += 4, and the final array is [5,2,3].
// 
//
// 
// Constraints: 
//
// 
// 1 <= candies <= 10^9 
// 1 <= num_people <= 1000 
// 
// Related Topics 数学


package leetcode.editor.cn.P1103_DistributeCandiesToPeople;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


//leetcode submit region begin(Prohibit modification and deletion)
interface Solver {
    int[] distributeCandies(int candies, int numPeople);
}

/**
 * Ref: official solution 1
 */
class Solver1 implements Solver {
    @Override
    public int[] distributeCandies(int candies, int numPeople) {
        int[] distribution = new int[numPeople];

        int current = 1;
        int remaining = candies;
        while (remaining > 0) {
            int dist = Math.min(current, remaining);
            distribution[(current - 1) % numPeople] += dist;
            remaining -= dist;
            current++;
        }

        return distribution;
    }
}

/**
 * Ref: official solution 2
 */
class Solver2 implements Solver {
    @Override
    public int[] distributeCandies(int candies, int numPeople) {
        int[] distribution = new int[numPeople];

        int step = (int) (Math.sqrt(2 * candies + .25) - .5);
        int loop = step / numPeople;
        int additional = step % numPeople;
        for (int i = 0; i < numPeople; i++) {
            distribution[i] = (int) ((loop - 1) * numPeople * loop * .5) + (i + 1) * loop;
            if (i < additional) {
                distribution[i] += numPeople * loop + (i + 1);
            }
        }
        distribution[additional] += (int) (candies - (1 + step) * step * .5);

        return distribution;
    }
}

class Solution {
    public int[] distributeCandies(int candies, int numPeople) {
        return new Solver2().distributeCandies(candies, numPeople);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class SolutionTest {

    private Solution solution = new Solution();

    private void test(int[] expected, int candies, int numPeople) {
        int[] actual = solution.distributeCandies(candies, numPeople);
        Assertions.assertArrayEquals(expected, actual);
    }

    /* 7
4 */
    @Test
    public void test1() {
        test(new int[]{1, 2, 3, 1}, 7, 4);
    }


    @Test
    public void test2() {
        test(new int[]{5, 2, 3}, 10, 3);
    }
}
