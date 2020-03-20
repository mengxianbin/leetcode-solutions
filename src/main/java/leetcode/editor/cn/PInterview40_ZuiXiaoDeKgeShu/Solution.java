//输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [3,2,1], k = 2
//输出：[1,2] 或者 [2,1]
// 
//
// 示例 2： 
//
// 输入：arr = [0,1,2,1], k = 1
//输出：[0] 
//
// 
//
// 限制： 
//
// 
// 0 <= k <= arr.length <= 10000 
// 0 <= arr[i] <= 10000 
// 
// Related Topics 堆 分治算法


package leetcode.editor.cn.PInterview40_ZuiXiaoDeKgeShu;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 分区法
 * <p>
 * 2 ms / 99.72 %
 * 42.8 MB / 100 %
 */
class Solution1 {
    private int partition(int[] arr, int l, int r) {
        int p = l;
        int q = r;
        int v = arr[r];
        while (p < q) {
            while (p < q && arr[p] < v) {
                p++;
            }
            arr[q] = arr[p];

            while (p < q && arr[q] >= v) {
                q--;
            }
            arr[p] = arr[q];
        }
        arr[q] = v;

        return p;
    }

    private void sort(int[] arr, int l, int r, int t) {
        if (l >= r) {
            return;
        }

        int i = partition(arr, l, r);
        if (i == t) {
            return;
        }
        if (i < t) {
            sort(arr, i + 1, r, t);
        } else {
            sort(arr, l, i - 1, t);
        }
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        sort(arr, 0, arr.length - 1, k - 1);
        int[] result = new int[k];
        return Arrays.copyOfRange(arr, 0, k);
    }
}

/**
 * 堆
 * <p>
 * 37 ms / 15.26 %
 * 42.7 MB / 100 %
 */
class Solution2 {
    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((l, r) -> r - l);
        for (int num : arr) {
            queue.offer(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        int[] result = new int[k];
        for (int i = 0; !queue.isEmpty(); i++) {
            result[i] = queue.poll();
        }

        return result;
    }
}

/**
 * 堆
 * <p>
 * 14 ms / 41.23 %
 * 42.8 MB / 100 %
 */
class Solution2_1 {
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] result = new int[k];
        if (k == 0) {
            return result;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (l, r) -> r - l);
        for (int num : arr) {
            if (queue.size() < k) {
                queue.offer(num);
            } else if (num < queue.peek()) {
                queue.poll();
                queue.offer(num);
            }
        }

        int i = 0;
        for (Integer num : queue) {
            result[i++] = num;
        }

        return result;
    }
}

/**
 * 二叉搜索树
 * <p>
 * 40 ms / 13.30 %
 * 43 MB / 100 %
 */
class Solution3 {
    public int[] getLeastNumbers(int[] arr, int k) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int num : arr) {
            treeMap.compute(num, (key, v) -> v == null ? 1 : v + 1);
        }

        int[] result = new int[k];
        int i = 0;
        outer:
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            for (int j = 0; j < entry.getValue(); j++) {
                if (i < k) {
                    result[i++] = entry.getKey();
                } else {
                    break outer;
                }
            }
        }

        return result;
    }
}

/**
 * 二叉搜索树
 * <p>
 * 30 ms / 20.77 %
 * 43.4 MB / 100 %
 */
class Solution3_2 {
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] result = new int[k];
        if (k == 0) {
            return result;
        }

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int count = 0;
        for (int num : arr) {
            if (count == k) {
                if (num < treeMap.lastKey()) {
                    treeMap.compute(treeMap.lastKey(), (key, v) -> v != null && v > 1 ? v - 1 : null);
                } else {
                    continue;
                }
            } else {
                count++;
            }
            treeMap.compute(num, (key, v) -> v == null ? 1 : v + 1);
        }

        int i = 0;
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            for (int j = 0; j < entry.getValue(); i++, j++) {
                result[i] = entry.getKey();
            }
        }

        return result;
    }
}

/**
 * 计数排序
 *
 * 2 ms / 99.72 %
 * 42.5 MB / 100 %
 */
class Solution4 {
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] countArr = new int[10001];
        for (int i : arr) {
            countArr[i]++;
        }

        int[] result = new int[k];
        int i = 0;
        loop:
        for (int num = 0; num < countArr.length; num++) {
            int count = countArr[num];
            for (int j = 0; j < count; j++, i++) {
                if (i == k) {
                    break loop;
                }
                result[i] = num;
            }
        }
        return result;
    }
}

class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        return new Solution1().getLeastNumbers(arr, k);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
