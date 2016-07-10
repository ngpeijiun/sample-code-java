package sample.concurrency.collection;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by eugenis on 27/4/16.
 */
public class QueueExample {

    public static void main(String[] args) {
        // Basic FIFO

        Queue<Object> fastQueue = new ArrayDeque<>(); // Not sure? Just start with this.

        Queue<Object> nullableQueue = new LinkedList<>();

        Queue<Object> emergencyPatientsQueue = new PriorityQueue<>();

        // Basic FIFO + LIFO

        Deque<Object> fastDeque = new ArrayDeque<>(); // Not sure? Just start with this.

        Deque<Object> nullableDeque = new LinkedList<>();

        // Concurrent FIFO (synchronous)

        BlockingQueue<Object> handOffChannel = new SynchronousQueue<>(); // Not sure? Just start with this.

        TransferQueue<Object> transferQueue = new LinkedTransferQueue<>();

        // Concurrent FIFO (blocking)

        BlockingQueue<Object> morePredictableQueue = new ArrayBlockingQueue<>(10); // Not sure? Just start with this.

        BlockingQueue<Object> higherThroughputQueue = new LinkedBlockingQueue<>();

        BlockingQueue<Object> emergencyPatientsBlockingQueue = new PriorityBlockingQueue<>();

        BlockingQueue<? extends Delayed> itemsExpiringQueue = new DelayQueue<>();

        // Concurrent FIFO (non-blocking)

        Queue<Object> nonBlockingQueue = new ConcurrentLinkedQueue<>();

        // Concurrent FIFO + LIFO (blocking)

        BlockingQueue<Object> blockingDeque = new LinkedBlockingDeque<>();

        // Concurrent FIFO + LIFO (non-blocking)

        Deque<Object> nonBlockingDeque = new ConcurrentLinkedDeque<>();
    }

}
