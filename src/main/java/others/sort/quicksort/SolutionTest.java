package others.sort.quicksort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class SolutionTest {

    static final Logger logger = LoggerFactory.getLogger(SolutionTest.class);

    private final QuickSort quickSort = new Solution3();

    private void printArray(int[] array) {
        logger.info("{}", array);
    }

    private void test(int[] arr) {
        printArray(arr);

        quickSort.sort(arr, 0, arr.length - 1);
        printArray(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            logger.debug("i={}, arr[i]={}, arr[i+1]={}.", i, arr[i], arr[i + 1]);
            Assertions.assertTrue(arr[i] <= arr[i + 1]);
        }
    }

    @Test
    public void testRandom() {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(90) + 10;
        }
        test(arr);
    }

    @Test
    public void test1() {
        int[] arr = {63, 75, 80, 37, 57, 63, 11, 32, 60, 59,};
        test(arr);
    }

    @Test
    public void test2() {
        int[] arr = {22, 61, 50, 12, 88, 34, 31, 46, 27, 73,};
        test(arr);
    }
}
