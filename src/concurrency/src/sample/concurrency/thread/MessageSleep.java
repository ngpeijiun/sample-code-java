package sample.concurrency.thread;

/**
 * Created by eugenis on 24/4/16.
 */
public class MessageSleep {

    public static void main(String[] args) throws InterruptedException {
        String[] importantInfo = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };

        for (String info : importantInfo) {
            Thread.sleep(4000);

            System.out.println(info);
        }
    }

}
