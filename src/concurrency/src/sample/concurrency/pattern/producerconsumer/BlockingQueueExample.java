package sample.concurrency.pattern.producerconsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by eugenis on 25/4/16.
 */
public class BlockingQueueExample {

    public static void main(String[] args) {
        String[] importantInfo = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };

        BlockingQueue<String> drop = new SynchronousQueue<>();

        Runnable producer = () -> {
            Random rand = new Random();

            try {
                for (String info : importantInfo) {
                    drop.put(info);

                    Thread.sleep(rand.nextInt(5000));
                }

                drop.put("DONE");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable consumer = () -> {
            Random rand = new Random();

            String message;

            try {
                while (!(message = drop.take()).equals("DONE")) {
                    System.out.println(message);

                    Thread.sleep(rand.nextInt(5000));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }

}
