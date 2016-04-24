package sample.concurrency.thread;

/**
 * Created by eugenis on 24/4/16.
 */
public class MessageInterrupt implements Runnable {

    public static void main(String[] args) {
        String[] importantInfo = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };

        Thread t = new Thread(new MessageInterrupt(importantInfo));

        t.start();

        t.interrupt();
    }

    private String[] importantInfo;

    public MessageInterrupt(String[] importantInfo) {
        this.importantInfo = importantInfo;
    }

    public void run() {
        for (String info : this.importantInfo) {
            this.heavyCrunch(info);

            if (Thread.interrupted()) {
                return;
            }
        }
    }

    private void heavyCrunch(String input) {
        System.out.println(input);
    }

}
