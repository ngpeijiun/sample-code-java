package sample.concurrency.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by eugenis on 27/4/16.
 */
public class ListExample {

    public static void main(String[] args) {
        // Basic

        List<Object> moreReadThanWriteList = new ArrayList<>(); // Not sure? Just start with this.

        List<Object> moreWriteThanReadList = new LinkedList<>();

        // Concurrent (read-copy-update)

        List<Object> readCopyUpdateList = new CopyOnWriteArrayList<>();

        // Concurrent Brute Force

        Collection<Object> safe = Collections.synchronizedCollection(new ArrayList<>());

        List<Object> safeList = Collections.synchronizedList(new ArrayList<>());
    }

}
