package sample.concurrency.executor;

import java.util.concurrent.TimeUnit;

/**
 * Created by eugenis on 27/4/16.
 */
public class PlainThreadExample {

    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                String name = Thread.currentThread().getName();
                System.out.format("Foo %s%n", name);
                TimeUnit.SECONDS.sleep(1);
                System.out.format("Bar %s%n", name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(task);

        thread.start();
    }

}
