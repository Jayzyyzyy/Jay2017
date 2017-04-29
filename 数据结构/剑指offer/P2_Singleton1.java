package 剑指offer;

/**
 * Created by Jay on 2017/4/21.
 */
public class P2_Singleton1 {

    private static P2_Singleton1 instance = null;

    private P2_Singleton1(){}

    public static P2_Singleton1 getInstance(){ //懒汉式
        if(instance == null){
            instance = new P2_Singleton1();
        }
        return instance;
    }



}
