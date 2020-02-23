package nowcoder.offer.FindGreatestSumOfSubArray;

public class Solution {
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int[] sum = new int[array.length];
        sum[0] = array[0];

        // int[] len = new int[array.length];
        // len[0] = 1;

        int maxSum = sum[0];

        for (int i = 1; i < array.length; i++) {
            if (sum[i - 1] < 0) {
                sum[i] = array[i];
                // len[i] = 1;
            } else {
                sum[i] = array[i] + sum[i - 1];
                // len[i] = len[i - 1] + 1;
            }

            if (sum[i] > maxSum) {
                maxSum = sum[i];
            }
        }

        return maxSum;
    }
}
