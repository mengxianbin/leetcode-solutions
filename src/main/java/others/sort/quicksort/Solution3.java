package others.sort.quicksort;

import static others.sort.quicksort.SolutionTest.logger;
import static others.sort.quicksort.Util.swap;

/**
 * 单向分区
 * <p>
 * 如何保证以 pivot 分割：最后额外 swap
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
 * <p>
 * 如何保证以 pivot 分割：单向，尾轴
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
 * <p>
 * 如何保证以 pivot 分割： p, q 夹逼; p, q 都不判等; 置换游标 i 独立于 p, q
 * 独立游标 i 的代价, 是 p, q 需要分别置换, 相较于 p q 相互置换 swap 次数更多
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
 * <p>
 * 如何保证以 pivot 分割: 最后额外 swap
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
 * <p>
 * 如何保证以 pivot 分割: 最后的 pivot 赋值
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
 * <p>
 * 如何保证以 pivot 分割: 最后的 pivot 赋值
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
 * <p>
 * 如何保证以 pivot 分割: 最后的 pivot 赋值
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
 * for @Solution5: (l, i-1), (i, r)
 * not for @Solution3: (l, i-1), (i, i), (i+1, r)
 */
class Partition8 implements Partition {

    @Override
    public int partition(int[] arr, int l, int r) {
        int p = l;
        int q = r;
        int v = arr[r];
        while (p < q) {
            while (p < q && arr[p] <= v) {
                p++;
            }
            while (p < q && arr[q] > v) {
                q--;
            }
            if (p < q) {
                int t = arr[p];
                arr[p] = arr[q];
                arr[q] = t;
            }
        }

        return p;
    }
}

/**
 * 区间法: (l, i-1), (i, r)
 * <p>
 * Q1. 区间法是否可能死循环？
 * (l, i - 1) 一定是真子集，没问题；
 * 分区点 i 为 0 时，会有问题
 * 优先 p++ 时，分区点不可能恒为 0
 * 优先 q-- 时，分区变为 (l, i) (i + 1, r)
 * 所以，综合各种情况，不会死循环
 * <p>
 * Q2. 区间法是否有必要？是否等价于排除 [l, i),[i, i],(i, r] 的方案？
 * 以优先 p++ 为例，轴值必须置为序列尾部，以应对 p++ 直接到序列尾部到情况
 * 分区结束时，arr[p] >= pivot 恒成立，分区为 (l, i-1), (i, r)
 * <p>
 * 如果 p++ 首次直接到达序列尾部，p == i
 * 如果 p++ 从未移动：此情况不可能为一次分区的最终状态
 * 如果 p++ 到达序列中间停止，此时 arr[p] >= pivot
 * (l, i-1) 确认安全，那么 (i+1, l) >= arr[i] 是否成立？
 * 如果成立，要求 arr[p] == pivot
 * 单向 p++，不存在 q-- 时，须要求 arr[p] < pivot, 而非 <=
 * p, q 双向夹逼时，须要求 结束时做一次 swap(arr, q, r)
 */
class Solution5 implements QuickSort {

    Partition partition = new Partition8();

    @Override
    public void sort(int[] arr, int l, int r) {
        if (arr == null || arr.length < 2 || l >= r) {
            return;
        }

        int i = partition.partition(arr, l, r);
        if (l < i - 1) {
            sort(arr, l, i - 1);
        }
        if (r > i) {
            sort(arr, i, r);
        }
    }

    @Override
    public Partition getPartition() {
        return partition;
    }
}

/**
 * pivot 分区法: [l, i),[i, i],(i, r]
 * <p>
 * 总结 保证 pivot 为分区点位置的方法：
 * 1. 单向迭代
 * 2. 最终额外 swap
 * 3. 左右夹逼，独立游标
 * 4. 循环替换，首尾 pivot 赋值
 */
public class Solution3 implements QuickSort {

    Partition partition = new Partition3();

    @Override
    public void sort(int[] arr, int l, int r) {
        if (arr == null || arr.length < 2 || l >= r) {
            return;
        }

        int p = partition.partition(arr, l, r);
        logger.debug("{}", arr);
        logger.debug("l={}, r={}, p={}.", l, r, p);

        if (l < p - 1) {
            sort(arr, l, p - 1);
        }
        if (r > p + 1) {
            sort(arr, p + 1, r);
        }
    }

    @Override
    public Partition getPartition() {
        return partition;
    }
}
