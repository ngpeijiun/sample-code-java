package sample.concurrency.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by eugenis on 25/4/16.
 */
public class SafeLockFriend {

    @FunctionalInterface
    interface BowLoop {
        Runnable create(SafeLockFriend bowee, SafeLockFriend bower);
    }

    public static void main(String[] args) {
        SafeLockFriend alphonse = new SafeLockFriend("Alphonse");
        SafeLockFriend gaston = new SafeLockFriend("Gaston");

        BowLoop bowLoop = (bowee, bower) -> () -> {
            Random rand = new Random();

            while (true) {
                bowee.acceptBow(bower);

                try {
                    Thread.sleep(rand.nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(bowLoop.create(alphonse, gaston)).start();
        new Thread(bowLoop.create(gaston, alphonse)).start();
    }

    private final String name;

    private final Lock lock = new ReentrantLock();

    public SafeLockFriend(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void acceptBow(SafeLockFriend bower) {
        if (this.tryLock(bower)) {
            System.out.format("%s: %s has bowed to me!%n", this.getName(), bower.getName());

            bower.acceptBowBack(this);

            this.unlock(bower);
        } else {
            System.out.format("%s: %s started to bow to me, but saw that I was already bowing to him.%n",
                    this.getName(), bower.getName());
        }
    }

    public void acceptBowBack(SafeLockFriend bower) {
        System.out.format("%s: %s has bowed back to me!%n", this.getName(), bower.getName());
    }

    private boolean tryLock(SafeLockFriend friend) {
        boolean myLock = this.lock.tryLock();
        boolean yourLock = friend.lock.tryLock();

        if (!(myLock && yourLock)) {
            if (myLock) {
                this.lock.unlock();
            }
            if (yourLock) {
                friend.lock.unlock();
            }
        }

        return myLock && yourLock;
    }

    private void unlock(SafeLockFriend friend) {
        this.lock.unlock();
        friend.lock.unlock();
    }

}
