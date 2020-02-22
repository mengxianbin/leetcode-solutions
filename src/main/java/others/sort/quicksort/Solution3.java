package others.sort.quicksort;

import static others.sort.quicksort.SolutionTest.logger;
import static others.sort.quicksort.Util.swap;

interface Partition {
    int partition(int[] arr, int l, int r);
}

/**
 * 单向分区
 */
class Partition1 implements Partition {

    @Override
    public int partition(int[] arr, int l, int r) {
        int v = arr[l];
        int i = l;
        for (int j = l + 1; j <= r; j++) {
            if (arr[j] < v) {
                i++;
                swap(arr, j, i);
            }
        }

        swap(arr, l, i);

        return i;
    }
}

/**
 * 双向分区
 */
class Partition2 implements Partition {

    @Override
    public int partition(int[] arr, int l, int r) {
        return 0;
    }
}

/**
 * 交换分区
 */
class Partition3 implements Partition {

    @Override
    public int partition(int[] arr, int l, int r) {
        return 0;
    }
}

/**
 * 最少交换分区
 */
class Partition4 implements Partition {

    @Override
    public int partition(int[] arr, int l, int r) {
        return 0;
    }
}

/**
 * 多选轴分区
 */
class Partition5 implements Partition {

    @Override
    public int partition(int[] arr, int l, int r) {
        return 0;
    }
}

/**
 * 简单分区法
 */
public class Solution3 implements QuickSort {

    private Partition partition = new Partition1();

    @Override
    public void sort(int[] arr, int l, int r) {
        if (arr == null || arr.length < 2 || l >= r) {
            return;
        }

        int p = partition.partition(arr, l, r);
        logger.debug("{}", arr);
        logger.debug("l={}, r={}, p={}.", l, r, p);

        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }
}
