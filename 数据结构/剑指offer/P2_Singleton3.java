package 剑指offer;

/**
 * Created by Jay on 2017/4/21.
 */
public class P2_Singleton3 {
    private volatile static P2_Singleton3 instance = null;

    private P2_Singleton3(){}

    public static P2_Singleton3 getInstance(){
        if(instance == null){  //双重检查
            synchronized (P2_Singleton3.class){  //同步锁，多线程环境
                if(instance == null){
                    instance = new P2_Singleton3();
                }
            }
        }

        return instance;
    }



}
