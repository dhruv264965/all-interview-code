package Collection.LinkedHashMap;

import java.util.LinkedHashMap;
import java.util.Map;

    class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75f, true); // true enables access order
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity; // Remove least recently used entry when size exceeds capacity
        }
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        System.out.println("Cache before accessing: " + cache);

        cache.get(1); // Access key 1, moving it to the end
        cache.put(4, "D"); // This will remove the least recently used entry (key 2)

        System.out.println("Cache after accessing key 1 and adding key 4: " + cache);
    }
}
