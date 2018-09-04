package github.weizibin.sort;

import java.util.Arrays;

public class PopSort {

    public static void sort(int[] arr) {
        int lenght = arr.length;
        for (int i = 0; i < lenght; i++) {
            for (int j = 0; j < lenght - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {5, 9, 8, 7, 6, 5, 5, 5};
        PopSort.sort(a);
        System.out.println(Arrays.toString(a));
        int[] b = {5, 1, 9, 3, 8, 4, 7};
        PopSort.sort(b);
        System.out.println(Arrays.toString(b));
    }
}
