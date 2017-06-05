package JUCLocks;

import java.util.concurrent.CountDownLatch;

/**
 *  锁存器（共享锁）
 */
public class CountDownLatchTest {
    private static final int LATCH_SIZE = 5;
    public static CountDownLatch doneSignal;

    public static void main(String[] args) {
        try {
            doneSignal = new CountDownLatch(LATCH_SIZE);
            //新建5个任务
            for (int i = 0; i < LATCH_SIZE; i++) {
                new InnerThread().start();
            }

            System.out.println("main thread wait");
            //主线程main等待其余5个线程执行完成
            doneSignal.await();  //1s

            System.out.println("main thread start");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static class InnerThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "1000 ms");

                //count -1
                doneSignal.countDown();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
