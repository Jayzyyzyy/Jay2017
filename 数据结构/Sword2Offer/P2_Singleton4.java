package Sword2Offer;

/**
 * Created by Jay on 2017/4/21.
 */
public class P2_Singleton4 {
    private static P2_Singleton4 instance = new P2_Singleton4(); //饿汉式

    private P2_Singleton4(){}

    public static P2_Singleton4 getInstance(){
        return instance;
    }



}
