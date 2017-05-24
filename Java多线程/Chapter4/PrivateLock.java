package Chapter4;

import Chapter2.Widget;

/**
 * 私有锁保护状态
 */
public class PrivateLock {
    private final Object lock = new Object();

    Widget widget;


    void someMethod(){
        synchronized (lock){
            //访问或者修改Widget的状态
        }
    }


}
