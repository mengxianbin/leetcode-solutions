package leetcode.editor.cn.P876_MiddleOfTheLinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {
    private void test(int expected, int[] input) {
        ListNode head = new ListNode(input[0]);
        ListNode next = head;
        for (int i = 1; i < input.length; i++) {
            next.next = new ListNode(input[i]);
            next = next.next;
        }

        ListNode actual = new Solution().middleNode(head);
        Assertions.assertEquals(expected, actual.val);
    }

    /* [1,2,3,4,5] */
    @Test
    public void test1() {
        test(3, new int[]{1, 2, 3, 4, 5});
    }

    @Test
    public void test2() {
        test(4, new int[]{1, 2, 3, 4, 5, 6});
    }
}
