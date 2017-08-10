package LeetCode.Array;

import java.util.Arrays;

public class Ex66_Plus_One {
    public int[] plusOne(int[] digits) {

        int t = 0;
        digits[digits.length-1] += 1;
        for(int i=digits.length-1; i>=0; i--){

            int temp = digits[i] + t;
            if(temp >= 10){
                t = 1;
            }else{
                t = 0;
            }
            digits[i] = temp % 10;
        }

        if(t == 1){
            int[] res = new int[digits.length +1];
            res[0] = t;
            for (int i = 1; i < res.length; i++) {
                res[i] = digits[i-1];
            }
            return res;
        }else{
            return digits;
        }
    }

    public static void main(String[] args) {
        int[] res = new Ex66_Plus_One().plusOne(new int[]{9,0,9});
        System.out.println(Arrays.toString(res));
    }
}
