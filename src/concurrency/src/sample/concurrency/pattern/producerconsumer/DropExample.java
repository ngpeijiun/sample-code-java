package sample.concurrency.pattern.producerconsumer;

import java.util.Random;

/**
 * Created by eugenis on 24/4/16.
 */
public class DropExample {

    public static void main(String[] args) {
        String[] importantInfo = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };

        DropExample drop = new DropExample();

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

    private String message;

    private boolean empty = true;

    public synchronized String take() throws InterruptedException {
        while (this.empty) {
            wait();
        }

        this.empty = true;

        this.notify();

        return this.message;
    }

    public synchronized void put(String message) throws InterruptedException {
        while (!this.empty) {
            wait();
        }

        this.message = message;

        this.empty = false;

        this.notify();
    }

}
