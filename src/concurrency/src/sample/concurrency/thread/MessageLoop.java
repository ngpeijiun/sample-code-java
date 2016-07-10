package sample.concurrency.thread;

/**
 * Created by eugenis on 24/4/16.
 */
public class MessageLoop implements Runnable {

    public static void main(String[] args) {
        long patience = 10000;

        if (args.length > 0) {
            try {
                patience = Long.parseLong(args[0]) * 1000;
            } catch (NumberFormatException e) {
                System.err.println("Argument must be an integer.");
                System.exit(1);
            }
        }

        String[] importantInfo = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };

        Thread t = new Thread(new MessageLoop(importantInfo));

        threadMessage("Starting MessageLoop thread");

        long startTime = System.currentTimeMillis();

        t.start();

        threadMessage("Waiting for MessageLoop thread to finish");

        while (t.isAlive()) {
            try {
                threadMessage("String waiting...");

                t.join(1000);

                if ((System.currentTimeMillis() - startTime) > patience && t.isAlive()) {
                    threadMessage("Tired of waiting!");

                    t.interrupt();

                    t.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        threadMessage("Finally!");
    }

    private static void threadMessage(String message) {
        String name = Thread.currentThread().getName();

        System.out.format("%s: %s%n", name, message);
    }

    private String[] importantInfo;

    public MessageLoop(String[] importantInfo) {
        this.importantInfo = importantInfo;
    }

    public void run() {
        try {
            for (String info : importantInfo) {
                Thread.sleep(4000);

                threadMessage(info);
            }
        } catch (InterruptedException e) {
            threadMessage("I wasn't done!");
        }
    }

}
