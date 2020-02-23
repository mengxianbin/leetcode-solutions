package others.sort.quicksort;

import static others.sort.quicksort.SolutionTest.logger;
import static others.sort.quicksort.Util.swap;

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
 * 单向分区：取尾
 * 确保轴位最后交换
 */
class Partition6 implements Partition {

    @Override
    public int partition(int[] arr, int l, int r) {
        int v = arr[r];
        int p = l;
        for (int i = l; i <= r; i++) {
            if (arr[i] <= v) {
                swap(arr, i, p);
                p++;
            }
        }

        return p - 1;
    }
}

/**
 * 双向分区
 */
class Partition2 implements Partition {

    @Override
    public int partition(int[] arr, int l, int r) {
        int v = arr[l];
        int p = l;
        int q = r;
        for (int i = l + 1; i <= q; ) {
            if (arr[i] < v) {
                swap(arr, i, p);
                p++;
                i++;
            } else if (arr[i] > v) {
                swap(arr, i, q);
                q--;
            } else {
                i++;
            }
        }

        return p;
    }
}

/**
 * 交换分区
 */
class Partition3 implements Partition {

    @Override
    public int partition(int[] arr, int l, int r) {
        int v = arr[r];
        int p = l;
        int q = r;
        while (p < q) {
            while (p < q && arr[p] <= v) {
                p++;
            }
            while (p < q && arr[q] >= v) {
                q--;
            }
            swap(arr, p, q);
        }

        swap(arr, p, r);

        return p;
    }
}

/**
 * 最少交换分区
 */
class Partition4 implements Partition {

    @Override
    public int partition(int[] arr, int l, int r) {
        int v = arr[r];
        int p = l;
        int q = r;
        int t = arr[q];
        while (p < q) {
            while (p < q && arr[p] <= v) {
                p++;
            }
            arr[q] = arr[p];
            while (p < q && arr[q] >= v) {
                q--;
            }
            arr[p] = arr[q];
        }
        arr[q] = t;

        return q;
    }
}

/**
 * 最少交换分区：取头为轴
 */
class Partition7 implements Partition {

    @Override
    public int partition(int[] arr, int l, int r) {
        int v = arr[l];
        int p = l;
        int q = r;
        int t = arr[p];
        while (p < q) {
            while (p < q && arr[q] >= v) {
                q--;
            }
            arr[p] = arr[q];

            while (p < q && arr[p] <= v) {
                p++;
            }
            arr[q] = arr[p];
        }
        arr[p] = t;

        return p;
    }
}

/**
 * 多选轴分区
 */
class Partition5 implements Partition {

    @Override
    public int partition(int[] arr, int l, int r) {
        int m = l;
        int n = r;
        if (arr[m] > arr[n]) {
            m = r;
            n = l;
        }
        int w = (l + r) / 2;
        int u = arr[w] < arr[m] ? m : arr[w] > arr[n] ? n : w;
        int v = arr[u];

        swap(arr, u, r);

        int p = l;
        int q = r;
        while (p < q) {
            while (p < q && arr[p] <= v) {
                p++;
            }
            arr[q] = arr[p];

            while (p < q && arr[q] >= v) {
                q--;
            }
            arr[p] = arr[q];
        }

        arr[q] = v;

        return q;
    }
}

/**
 * 简单分区法
 */
public class Solution3 implements QuickSort {

    Partition partition = new Partition5();

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

    @Override
    public Partition getPartition() {
        return partition;
    }
}
