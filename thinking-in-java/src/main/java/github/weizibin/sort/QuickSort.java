package github.weizibin.sort;

import java.util.Arrays;

public class QuickSort {

    public static void sort(int arr[], int l, int r) {
        if (l < r) {
            int m = partition(arr, l, r);
            sort(arr, l, m - 1);
            sort(arr, m + 1, r);
        }
    }

    public static int partition(int[] arr, int l, int r) {
        int key = arr[l];
        int i = l;
        int j = r + 1;
        while(i < j) {
            while(i < j && arr[--j] >= key);
                arr[i] = arr[j];
            while(i < j && arr[++i] <= key);
                arr[j] = arr[i];
        }
        arr[j] = key;
        return j;
    }

    public static void main(String[] args) {
        int[] a = {5, 9, 8, 7, 6, 5, 5, 5};
        QuickSort.sort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
        int[] b = {5, 1, 9, 3, 8, 4, 7};
        QuickSort.sort(b, 0, b.length - 1);
        System.out.println(Arrays.toString(b));
    }
}
