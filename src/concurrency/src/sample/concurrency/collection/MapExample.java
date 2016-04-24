package sample.concurrency.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by eugenis on 27/4/16.
 */
public class MapExample {

    enum Alphabet { A, B, C }

    public static void main(String[] args) {
        // Basic

        Map<Object, Object> map = new HashMap<>(); // Not sure? Just start with this.

        Map<MapExample.Alphabet, Object> veryFastMap = new EnumMap<>(MapExample.Alphabet.class);

        Map<Object, Object> identifyInstanceMap = new IdentityHashMap<>();

        Map<Object, Object> weakReferenceMap = new WeakHashMap<>();

        // Basic Ordered

        Map<Object, Object> maintainedInsertionOrderMap = new LinkedHashMap<>();

        SortedMap<Object, Object> orderedMap = new TreeMap<>();

        // Basic Navigable

        NavigableMap<Object, Object> navigableMap = new TreeMap<>();

        // Concurrent (non-blocking)

        ConcurrentMap<Object, Object> concurrentMap = new ConcurrentHashMap<>();

        // Concurrent Navigable (non-blocking)

        ConcurrentNavigableMap<Object, Object> concurrentNavigableMap = new ConcurrentSkipListMap<>();

        // Concurrent Brute Force

        Map<Object, Object> safeMap = Collections.synchronizedMap(new HashMap<>());

        SortedMap<Object, Object> safeOrderedMap = Collections.synchronizedSortedMap(new TreeMap<>());

        NavigableMap<Object, Object> safeNavigableMap = Collections.synchronizedNavigableMap(new TreeMap<>());
    }

}
