package Collection.Tree;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SynchronizedTreeMap {
    public static void main(String[] args) {
        SortedMap<Integer, String> treeMap = new TreeMap<>();
        SortedMap<Integer, String> syncMap = Collections.synchronizedSortedMap(treeMap);

        syncMap.put(1, "A");
        syncMap.put(2, "B");


        synchronized (syncMap) {
            for (Map.Entry<Integer, String> entry : syncMap.entrySet()) {
                System.out.println(entry.getKey() + " => " + entry.getValue());
            }
        }
    }
}
