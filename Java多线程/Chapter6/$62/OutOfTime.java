package Chapter6.$62;

import java.util.Timer;
import java.util.TimerTask;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 *  错误的Timer行为
 */
public class OutOfTime {
    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(), 1);  //抛出异常
        SECONDS.sleep(1); //主线程睡1s
        timer.schedule(new ThrowTask(), 1);
        SECONDS.sleep(5);
    }

    static class ThrowTask extends TimerTask{
        @Override
        public void run() {
            throw new RuntimeException();//timer无法处理异常
        }
    }
}
