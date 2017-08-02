package Nowcoder.dp;

import java.util.Arrays;

/**
 * 求出最长递增子序列LIS   O(N²)或者O(NlgN)
 */
public class LIS {
    public static void main(String[] args) {
        int[] arr = {2,1,5,3,6,4,8,9,7};
        System.out.println(Arrays.toString(lis1(arr)));
    }

    public static int[] lis1(int[] arr){
        if(arr == null || arr.length == 0) return null;

        int[] dp = getdp1(arr);
        return generateLIS(arr,  dp);
    }
    //dp数组，每个元素表示以每个位置结束的最长递增子序列长度
    public static int[] getdp1(int[] arr){
        int[] dp = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        return dp;
    }
    //根据状态数组dp，还原LIS
    public static int[] generateLIS(int[] arr, int[] dp){
        int len = dp[0]; //LIS长度
        int index = 0; //位置
        for (int i = 1; i < dp.length; i++) { //找到最长长度及位置
            if(dp[i] > len){
                len = dp[i];
                index = i;
            }
        }

        int[] lis = new int[len]; //LIS数组
        lis[--len] = arr[index]; //最后一个值

        for (int i = index; i >= 0; i--) { //从右往左扫描
            if(arr[index] > arr[i] && dp[index] == dp[i] + 1){
                lis[--len] = arr[i];
                index = i;
            }
        }
        return lis;
    }
}
