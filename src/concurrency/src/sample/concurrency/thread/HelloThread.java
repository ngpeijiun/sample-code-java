package sample.concurrency.thread;

/**
 * Created by eugenis on 24/4/16.
 */
public class HelloThread extends Thread {

    public static void main(String[] args) {
        (new HelloThread()).start();
    }

    public void run() {
        System.out.println("Hello from a thread!");
    }

}
