package sample.concurrency.deadlock;

/**
 * Created by eugenis on 24/4/16.
 */
public class DeadLockFriend {

    public static void main(String[] args) {
        DeadLockFriend alphonse = new DeadLockFriend("Alphonse");
        DeadLockFriend gaston = new DeadLockFriend("Gaston");

        new Thread(() -> alphonse.acceptBow(gaston)).start();
        new Thread(() -> gaston.acceptBow(alphonse)).start();
    }

    private final String name;

    public DeadLockFriend(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public synchronized void acceptBow(DeadLockFriend bower) {
        System.out.format("%s: %s has bowed to me!%n", this.getName(), bower.getName());

        bower.acceptBowBack(this);
    }

    public synchronized void acceptBowBack(DeadLockFriend bower) {
        System.out.format("%s: %s has bowed back to me!%n", this.getName(), bower.getName());
    }

}
