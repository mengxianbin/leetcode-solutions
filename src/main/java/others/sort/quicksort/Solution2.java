package others.sort.quicksort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class Solution2 implements QuickSort {

    private boolean debug = false;

    private void test(int[] arr) {
        printArray(arr);

        sort(arr, 0, arr.length - 1);
        printArray(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (debug) {
                System.out.printf("i=%d, arr[i]=%d, arr[i+1]=%d.\n", i, arr[i], arr[i + 1]);
            }
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

    private void printArray(int[] array) {
        System.out.print("{");
        for (int i : array) {
            System.out.printf("%d, ", i);
        }
        System.out.println("}");
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;

        if (debug) {
            printArray(arr);
            System.out.print("{");
            for (int k = 0; k < arr.length; k++) {
                System.out.printf("%s, ", k == i ? "i." : k == j ? "j." : "  ");
            }
            System.out.println("}");
            System.out.printf("i=%d, j=%d.\n\n", i, j);
        }
    }

    public void sort(int[] arr, int left, int right) {
        if (arr == null || arr.length < 2) {
            return;
        }
        if (left < 0 || right >= arr.length) {
            return;
        }

        if (left >= right) {
            return;
        }

        int p = left;
        int q = right;
        int u = (left + right) / 2;

        int m = p;
        int n = q;
        if (arr[m] > arr[q]) {
            m = q;
            n = p;
        }
        int v = arr[u] < arr[m] ? m : arr[u] > arr[n] ? n : u;
        int pivot = arr[v];

        if (debug) {
            System.out.printf("l=%d/(%d), r=%d/(%d), pivot=%d.\n", left, arr[left], right, arr[right], pivot);
        }

        swap(arr, left, v);

        while (p < q) {
            while (p < q && arr[q] >= pivot) {
                q--;
            }
            while (p < q && arr[p] <= pivot) {
                p++;
            }
            if (arr[p] > arr[q]) {
                swap(arr, p, q);
            }
        }

        swap(arr, left, p);

        if (debug) {
            printArray(arr);
            System.out.printf("p=%d/(%d), q=%d/(%d).\n\n", p, arr[p], q, arr[q]);
        }

        sort(arr, left, p - 1);
        sort(arr, p + 1, right);
    }

    @Override
    public Partition getPartition() {
        return null;
    }
}
