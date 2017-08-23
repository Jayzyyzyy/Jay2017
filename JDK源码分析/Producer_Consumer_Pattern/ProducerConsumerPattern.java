package Producer_Consumer_Pattern;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerConsumerPattern {
    public static void main(String args[]){

        //Creating shared object
        BlockingQueue sharedQueue = new LinkedBlockingQueue();

        //Creating Producer and Consumer Thread
        Thread prodThread = new Thread(new ProducerDemo(sharedQueue));
        Thread consThread = new Thread(new ConsumerDemo(sharedQueue));

        //Starting producer and Consumer thread
        prodThread.start();
        consThread.start();
    }
}

//Producer Class in java
class ProducerDemo implements Runnable {

    private final BlockingQueue sharedQueue;

    public ProducerDemo(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++){
            try {
                System.out.println("Produced: " + i);
                sharedQueue.put(i);
                Thread.sleep(1000);  //每隔1s放一个
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

//Consumer Class in Java
class ConsumerDemo implements Runnable{

    private final BlockingQueue sharedQueue;

    public ConsumerDemo (BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while(true){
            try {
                System.out.println("Consumed: "+ sharedQueue.take());
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
