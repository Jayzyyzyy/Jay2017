package Sword2Offer;

/**
 * Created by Jay on 2017/4/21.
 */
public class P2_Singleton2 {
    private static P2_Singleton2 instance = null;

    private P2_Singleton2(){}

    public static P2_Singleton2 getInstance(){
        synchronized (P2_Singleton2.class){  //同步锁，多线程环境
            if(instance == null){
                instance = new P2_Singleton2();
            }
        }
        return instance;
    }



}
