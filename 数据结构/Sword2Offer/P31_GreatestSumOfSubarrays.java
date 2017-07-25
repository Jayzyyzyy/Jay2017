package Sword2Offer;

/**
 * 连续子数组的最大和
 */
public class P31_GreatestSumOfSubarrays {
    public int FindGreatestSumOfSubArray(int[] array) {
        if(array == null || array.length <= 0) return 0;

        int cur = 0; //一轮中的和
        int total = Integer.MIN_VALUE; //遍历中总的连续子数组最大和

        for(int i =0;i < array.length; i++){
            if(cur < 0){
                cur = array[i]; //新一轮
            }else{
                cur += array[i];
            }
            if(cur > total){
                total = cur;
            }
        }
        return total;
    }

    public static void main(String[] args) {
//        int[] a = new int[]{1,-2,3,10,-4,7,2,-5};
//        int[] a = new int[]{1,-2,3,5,-2,6,-1};
        int[] a = new int[]{-2,-8,-1,-5,-9};
        System.out.println(new P31_GreatestSumOfSubarrays().FindGreatestSumOfSubArray(a));
    }
}
