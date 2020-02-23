package others.sort.heapsort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeapSort implements ArraySorter {

    static final Logger logger = LoggerFactory.getLogger(HeapSort.class);

    private void build(int[] arr) {
        for (int i = arr.length / 2; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        print(arr, 0, 0);
        logger.info("\n");
    }

    private void print(int[] arr, int i, int layer) {
        if (i < 0 || i >= arr.length) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int l = 0; l < layer; l++) {
            sb.append("    ");
        }
        sb.append(arr[i]);
        logger.info(sb.toString());

        print(arr, i * 2 + 1, layer + 1);
        print(arr, i * 2 + 2, layer + 1);
    }

    private void heapify(int[] arr, int i, int len) {
        int max = i;
        int left = i * 2 + 1;
        int right = i * 2 + 2;

        if (left < len && arr[left] > arr[max]) {
            max = left;
        }
        if (right < len && arr[right] > arr[max]) {
            max = right;
        }

        if (max != i) {
            swap(arr, max, i);
            heapify(arr, max, len);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        build(arr);

        int len = arr.length;
        while (len > 0) {
            swap(arr, 0, len - 1);
            len--;
            heapify(arr, 0, len);
        }
    }

}
