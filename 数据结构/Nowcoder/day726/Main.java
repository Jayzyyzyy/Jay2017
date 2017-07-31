package Nowcoder.day726;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()){
            int N = sc.nextInt();
            int[][] m = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    m[i][j] = sc.nextInt();
                }
            }

            int maxSum = maxSum(m);
            System.out.println(maxSum);
        }
    }

    public static int maxSum(int[][] m){
        if(m == null || m.length == 0 || m[0].length == 0) return 0;

        int N = m.length;
        int maxSum = Integer.MIN_VALUE;
        int cur = 0;
        int[] sums = null;

        for (int i = 0; i < N; i++) {
            sums = new int[N];
            for (int j = i; j < N; j++) {
                cur = 0;
                for (int k = 0; k < N; k++) {
                    sums[k] += m[j][k];
                }
                for (int k = 0; k < N; k++) {
                    if(cur < 0){
                        cur = sums[k];
                    }else {
                        cur += sums[k];
                    }
                    maxSum = Math.max(cur, maxSum);
                }
            }
        }
        return maxSum;
    }
}
