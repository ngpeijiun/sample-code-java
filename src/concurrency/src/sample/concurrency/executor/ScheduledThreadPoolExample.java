package sample.concurrency.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by eugenis on 27/4/16.
 */
public class ScheduledThreadPoolExample {

    public static void main(String[] args) {
        Runnable task = () -> System.out.format("Scheduling: %d%n", System.nanoTime());

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);

        try {
            TimeUnit.MILLISECONDS.sleep(1337);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);

        System.out.format("Remaining Delay: %dms%n", remainingDelay);

        int initialDelay = 0;
        int period = 1;

        executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
        // or executor.scheduleWithFixedDelay(task, initialDelay, period, TimeUnit.SECONDS);
    }

}
