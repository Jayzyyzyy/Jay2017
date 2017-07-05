package Sword2Offer;

/**
 * 二进制中1的个数
 */
public class P10_NumberOf1InBinary {



    //位运算
    public int NumberOf1_2(int n){
        int count = 0;
        int flag = 1;
        while(flag != 0){  //从最左边高位移除，变为0
            if((n & flag) == flag){ //与运算结果为flag，说明该位为1
                count ++;
            }
            flag = flag << 1; //flag一直右移，与n进行与运算
        }
        return count;
    }

    //O(n) O(n)
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
