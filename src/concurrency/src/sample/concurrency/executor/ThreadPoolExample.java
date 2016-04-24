package sample.concurrency.executor;

import java.util.concurrent.*;

/**
 * Created by eugenis on 27/4/16.
 */
public class ThreadPoolExample {

    public static void main(String[] args) {
        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                return 123;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<Integer> future = executor.submit(task);

        try {
            System.out.format("future done? %b%n", future.isDone());

            Integer result;

            try {
                result = future.get(1, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                System.out.println("get future timeout");
            }

            result = future.get();

            System.out.format("future done? %b%n", future.isDone());
            System.out.format("result: %d%n", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
