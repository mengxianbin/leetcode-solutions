package others.sort.quicksort;

public class Util {

    static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
