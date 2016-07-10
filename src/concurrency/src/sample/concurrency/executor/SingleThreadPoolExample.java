package sample.concurrency.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by eugenis on 27/4/16.
 */
public class SingleThreadPoolExample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Runnable task = () -> {
            try {
                String name = Thread.currentThread().getName();
                System.out.format("Foo %s%n", name);
                TimeUnit.SECONDS.sleep(5);
                System.out.format("Bar %s%n", name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        executor.submit(task);

        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("tasks interrupted");
        } finally {
            if (!executor.isTerminated()) {
                System.out.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }

}
