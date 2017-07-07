package Sword2Offer;

/**
 * 调整数组顺序，使得数组中奇数位于偶数之前
 * （保证奇数和奇数，偶数和偶数之间的相对位置不变）
 */
public class P14_ReOrderArray {
    //可以保证奇数位于偶数之前，但无法保证相对位置顺序
    public void reOrderArray(int [] array) {
        int len = array.length;
        if(array == null || len == 0){
            return;
        }
        int ps = 0;
        int pe = len-1;
        while(ps < pe){
            while(ps < pe && (array[ps] & 1)!=0){//找到偶数
                ps ++;
            }
            while(ps < pe && (array[pe] & 1)==0){//找到奇数
                pe --;
            }
            if(ps < pe){ //交换
                int temp = array[ps];
                array[ps] = array[pe];
                array[pe] = temp;
            }
        }
    }

    //满足要求，时间复杂度为O（n），空间复杂度为O（n）
    public void reOrderArray2(int [] array) {
        int len = array.length;
        int[] a = new int[len];
        int size = 0;
        for(int i=0;i<len;i++){
            if(array[i]%2 != 0){
                a[size++] = array[i];
            }
        }
        for(int i=0;i<len;i++){
            if(array[i]%2 == 0){
                a[size++] = array[i];
            }
        }
        for(int i=0;i<len;i++){
            array[i] = a[i];
        }
    }

}
