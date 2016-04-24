package sample.concurrency.executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by eugenis on 27/4/16.
 */
public class ForkJoinPoolExample {

    public static void main(String[] args) {
        List<Callable<String>> callables = Arrays.asList(
                callable("task1", 1),
                callable("task2", 1),
                callable("task3", 3)
        );

        ExecutorService executor = Executors.newWorkStealingPool();

        try {
            System.out.println("invoke all");
            executor.invokeAll(callables)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            throw new IllegalStateException(e);
                        }
                    })
                    .forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("invoke any");
            String result = executor.invokeAny(callables);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            System.out.format("%s wake up%n", result);
            return result;
        };
    }

}
