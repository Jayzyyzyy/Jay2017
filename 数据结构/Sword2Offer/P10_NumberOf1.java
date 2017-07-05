package Sword2Offer;

/**
 * 二进制中1的个数
 */
public class P10_NumberOf1 {
    


    public int NumberOf1(int n) {
        String s = Integer.toBinaryString( n );

        char[] c = s.toCharArray();

        int cnt = 0;
        for(int i=0;i<c.length;i++){
            if(c[i] == '1'){
                cnt ++;
            }
        }
        return cnt;
    }
}
