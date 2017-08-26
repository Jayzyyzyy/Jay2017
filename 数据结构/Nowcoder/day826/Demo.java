package Nowcoder.day826;

import java.util.Arrays;

/**
 * Created by Jay on 2017/8/26
 */
public class Demo {
    public static void sort(int[] arr, int lo, int hi){
        if(hi <= lo) return;

        int part = partition(arr, lo, hi);

        sort(arr, lo, part-1);
        sort(arr, part + 1, hi);
    }

    public static int partition(int[] arr, int lo, int hi){
        int i = lo, j = hi + 1;
        int v = arr[lo];

        while(true){
            while(arr[++i] <= v){
                if( i == hi) break;
            }
            while(arr[--j] >= v){
                if( j == lo) break;
            }

            if(i >= j) break;

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        int temp = arr[lo];
        arr[lo] = arr[j];
        arr[j] = temp;

        return j;
    }

    public static void main(String[] args) {
        int[] arr  = {45,67,33,21};
        sort(arr, 0, arr.length-1);
        Arrays.toString(arr);
    }
}
