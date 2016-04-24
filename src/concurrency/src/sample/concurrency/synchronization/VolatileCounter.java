package sample.concurrency.synchronization;

import java.util.Random;

/**
 * Created by eugenis on 24/4/16.
 */
public class VolatileCounter {

    public static void main(String[] args) throws InterruptedException {
        VolatileCounter counter = new VolatileCounter();

        Runnable run = () -> {
            Random rand = new Random();

            while (true) {
                if (rand.nextBoolean()) {
                    counter.increment();
                } else {
                    counter.decrement();
                }

                if (Thread.interrupted()) {
                    return;
                }
            }
        };

        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);

        t1.start();
        t2.start();

        Thread.sleep(5000);

        t1.interrupt();
        t2.interrupt();

        System.out.println(counter.value());
    }

    private volatile int c = 0;

    public void increment() {
        c++;
    }

    public void decrement() {
        c--;
    }

    public int value() {
        return c;
    }

}
