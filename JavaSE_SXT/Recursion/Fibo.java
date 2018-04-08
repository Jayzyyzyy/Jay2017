package Recursion;

/**
 * Created by Jay on 2018/3/21
 * 非递归改造
 */
public class Fibo {
    /**
     * 非递归解法
     * @param n 第几个数
     * @return 对应的值
     */
    private static long fibo(int n){

        if(n <= 0) return -1; //非法参数

        if(n == 2 || n == 1){
            return 1;
        }

        long f1 = 1L;
        long f2 = 1L;

        for (int i = 0; i < (n - 2); i++) { //重复计算n-2次
            long temp = f1;
            f1 = f2;
            f2 = f2 + temp;
        }

        return f2;
    }

    public static void main(String[] args){
        System.out.println(fibo(5));
    }

}
