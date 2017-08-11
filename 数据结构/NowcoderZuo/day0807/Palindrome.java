package NowcoderZuo.day0807;

/**
 * 给定一个字符串str，返回str中最长回文子串的长度
 */
public class Palindrome {
    //O(N³) 暴力求解
    public int getLongestPalindrome(String A, int n) {
        if(A == null || n <= 0) return 0;

        int maxL = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j <= n; j++) {
                if(isHuiWen(A.substring(i, j), j-i)){
                    maxL = Math.max(maxL, j-i);
                }
            }
        }
        return maxL;
    }
    public boolean isHuiWen(String s, int length){
        int k = length / 2;
        for (int i = 0; i < k; i++) {
            if(s.charAt(i) != s.charAt(length - i-1)){
                return false;
            }
        }
        return true;
    }

    //动态规划


    public static void main(String[] args) {
        System.out.println(new Palindrome().getLongestPalindrome("12133211", 6));
    }
}
