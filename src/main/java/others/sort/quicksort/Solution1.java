package others.sort.quicksort;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Solution1 implements QuickSort {

    private static final Logger logger = LoggerFactory.getLogger(Solution1.class);

    @Test
    public void testRandom() {
        Random random = new Random();
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(90) + 10;
        }
        printArray(array);

        sort(array, 0, array.length - 1);
        printArray(array);
    }

    private void printArray(int[] array) {
        System.out.print("{");
        for (int i : array) {
            System.out.printf("%d, ", i);
        }
        System.out.println("}");
    }

    public void exchange(int[] sum, int i, int j) {
        int temp = sum[i];
        sum[i] = sum[j];
        sum[j] = temp;

        printArray(sum);
        System.out.print("{");
        for (int k = 0; k < sum.length; k++) {
            System.out.printf("%s, ", k == i ? "i." : k == j ? "j." : "  ");
        }
        System.out.println("}");
        System.out.printf("i=%d, j=%d.\n\n", i, j);
    }

    public void sort(int[] nums, int top, int tail) {
        if (top >= tail) {
            return;
        }
        int lt = top, gt = tail, i = top + 1;
        int pivot = nums[top];
        System.out.printf("top=%d/(%d), tail=%d/(%d), pivot=%d/(%d).\n", top, nums[top], tail, nums[tail], top, nums[top]);
        while (i <= gt) {
            System.out.printf("lt=%d/(%d), gt=%d/(%d), i=%d/(%d).\n", lt, nums[lt], gt, nums[gt], i, nums[i]);
            if (nums[i] > pivot) {
                exchange(nums, i, gt--);
            } else if (nums[i] < pivot) {
                exchange(nums, i++, lt++);
            } else {
                i++;
            }
        }

        sort(nums, top, lt - 1);
        sort(nums, gt + 1, tail);
    }

    @Override
    public Partition getPartition() {
        return null;
    }

}
