package Recursion;

/**
 * Created by Jay on 2018/3/21
 */
public class Recursion {
    public static void main(String[] args){
        //System.out.println(method(5));
        System.out.println(fibo(5));
        System.out.println(Character.SIZE);
        System.out.println((int)Character.MIN_VALUE);
    }

    /**
     * 阶乘
     * @param p 阶乘到几
     * @return 结果
     */
    private static int method(int p){
        if( p == 1) return 1;
        else
            return p*method(p-1);
    }

    /**
     * 斐波那契数列递归解法
     * @param p
     * @return
     */
    private static int fibo(int p){
        if(p == 2 || p == 1) return 1;
        else
            return fibo(p-1) + fibo(p-2);
    }

}
