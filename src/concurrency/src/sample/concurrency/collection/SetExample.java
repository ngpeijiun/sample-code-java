package sample.concurrency.collection;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by eugenis on 27/4/16.
 */
public class SetExample {

    enum Alphabet { A, B, C }

    public static void main(String[] args) {
        // Basic

        Set<Object> set = new HashSet<>(); // Not sure? Just start with this.

        Set<?> veryFastSet = EnumSet.noneOf(SetExample.Alphabet.class);

        // Basic Ordered

        Set<Object> maintainedInsertionOrderSet = new LinkedHashSet<>();

        SortedSet<Object> orderedSet = new TreeSet<>();

        // Basic Navigable

        NavigableSet<Object> navigableSet = new TreeSet<>();

        // Concurrent (read-copy-update)

        Set<Object> readCopyUpdateSet = new CopyOnWriteArraySet<>();

        // Concurrent Ordered (non-blocking)

        SortedSet<Object> concurrentOrderedSet = new ConcurrentSkipListSet<>();

        // Concurrent Navigable (non-blocking)

        NavigableSet<Object> concurrentNavigableSet = new ConcurrentSkipListSet<>();

        // Concurrent Brute Force

        Set<Object> safeSet = Collections.synchronizedSet(new HashSet<>());

        SortedSet<Object> safeOrderedSet = Collections.synchronizedSortedSet(new TreeSet<>());

        NavigableSet<Object> safeNavigableSet = Collections.synchronizedNavigableSet(new TreeSet<>());
    }

}
