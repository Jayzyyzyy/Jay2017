package Nowcoder.dp;

import java.util.Scanner;

public class LCSequence {
    public static int findLCS(String A, int n, String B, int m) {
        if(A == null || n <= 0 || B == null || m <= 0) return 0;

        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        int[][] dp = new int[n+1][m+1];

        for(int i=1; i<n+1; i++){
            for(int j = 1;j<m+1; j++){
                if(a[i-1] == b[j-1])
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[n][m];
    }

    /*public static void main(String[] args) {
        System.out.println(findLCS("1A2C3D4B56",10,"B1D23CA45B6A",12));
    }*/

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String A = sc.next();
            String B = sc.next();
            System.out.println(findLCS(A, A.length(), B, B.length()));
        }
    }
}
