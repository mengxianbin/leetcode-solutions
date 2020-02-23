package others.sort.quicksort;

public class Solution4 implements QuickSort {

    public void sort(int[] nums, int top, int tail) {
        if (top >= tail)
            return;
        
        int left = top;
        int right = tail;
        int mid = nums[(top + tail) / 2];
        while (left <= right) {
            while (nums[left] < mid) {
                ++left;
            }
            while (nums[right] > mid) {
                --right;
            }
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                ++left;
                --right;
            } else if (left == right) {
                ++left;
            }
        }
        sort(nums, top, right);
        sort(nums, left, tail);
    }

    @Override
    public Partition getPartition() {
        return null;
    }
}
