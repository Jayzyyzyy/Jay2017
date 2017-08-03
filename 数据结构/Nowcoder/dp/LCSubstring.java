package Nowcoder.dp;

public class LCSubstring {
    public static int findLongest(String A, int n, String B, int m) {
        if(A == null || n <= 0 || B == null || m <= 0) return 0;

        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        int[][] dp = new int[n+1][m+1];
        int maxLength = 0;

        for(int i=1; i<n+1; i++){
            for(int j = 1;j<m+1; j++){
                if(a[i-1] == b[j-1])
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = 0;
                maxLength  =Math.max(maxLength, dp[i][j]);
            }
        }
        return maxLength;
    }
}
