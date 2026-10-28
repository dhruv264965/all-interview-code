# Interview Notes Java8

```text
ConcurrentHashMap (Java 8) - HOW TO EXPLAIN IN INTERVIEW
=========================================================

Q1. What is ConcurrentHashMap?
------------------------------
- A thread-safe HashMap from java.util.concurrent.
- Many threads can read and write at the same time WITHOUT locking the whole map.
- Better than Hashtable / Collections.synchronizedMap (they use ONE lock for the whole map).

Q2. Why not HashMap in multi-threading?
---------------------------------------
- Not thread-safe: lost updates, corrupted buckets, wrong size.
- (Java 7) concurrent resize could even cause an infinite loop.
- Hashtable is safe but slow: every get/put takes the same lock.


Q3. Java 7 vs Java 8 (very common question)
-------------------------------------------
Java 7:
  - Map divided into Segments (default 16), each Segment = ReentrantLock + small hashtable.
  - Max concurrency = number of segments (16). Fixed at creation.
Java 8:
  - Segments REMOVED.
  - Uses  CAS + synchronized on the FIRST NODE of a bucket.
  - Lock granularity = 1 bucket. Concurrency grows with table size.
  - Linked list converts to Red-Black Tree when long (O(n) -> O(log n)).


Q4. Data structure
------------------
  Node<K,V>[] table  (volatile)
     [0] -> Node -> Node            (linked list)
     [1] -> null
     [2] -> TreeBin -> red-black tree
  Node has:  final int hash, final K key, volatile V val, volatile Node next
  'volatile' on val / next is why get() needs NO lock.


Q5. How put() works  (say it step by step)
------------------------------------------
 1. key or value null?       -> throw NullPointerException
 2. hash = spread(key.hashCode())      // (h ^ (h >>> 16)) & 0x7fffffff
 3. table not created?       -> initTable()   (CAS on sizeCtl, only one thread initializes)
 4. bucket is EMPTY?         -> insert using CAS. NO LOCK.
 5. bucket head is MOVED?    -> resize is running, this thread HELPS in resize
 6. bucket has nodes         -> synchronized(head node) { update value or append to list/tree }
 7. list length >= 8 (and table >= 64)  -> convert to Red-Black tree
 8. addCount()               -> update size, resize if size > 0.75 * capacity

 One-line answer:
 "Empty bucket -> CAS (lock free). Non-empty bucket -> synchronized on head node only."


Q6. How get() works
-------------------
- NO LOCK at all.
- Reads table (volatile) -> reads bucket -> walks nodes -> returns volatile val.
- If it meets a ForwardingNode (resize going on) it searches the new table.
- So reads never block, and always see the latest completed write (happens-before via volatile).


Q7. Why null key / null value NOT allowed?
------------------------------------------
- map.get(k) == null would be ambiguous: "key absent" OR "value is null"?
- HashMap solves it with containsKey(), but in concurrent code another thread can change
  the map between get() and containsKey() -> race condition.
- So CHM throws NullPointerException.


Q8. Resize (multi-threaded)
---------------------------
- Table doubles when size > 0.75 * capacity.
- Old table split in ranges; each thread claims a range with CAS (transferIndex).
- Migrated bucket gets a ForwardingNode (hash = MOVED = -1) pointing to nextTable.
- A thread doing put() that hits ForwardingNode JOINS the resize.
- New index of a node is either  i  or  i + oldCapacity  (same trick as HashMap).


Q9. Treeification
-----------------
- Bucket chain >= 8 AND table capacity >= 64  -> Red-Black tree (TreeBin).
- Table < 64 -> it resizes instead of treeifying.
- Tree shrinks to <= 6 nodes -> back to linked list.


Q10. How does size() work?
--------------------------
- Like LongAdder: baseCount + CounterCell[] (cells used when CAS on baseCount fails).
- size() = baseCount + sum(cells). It is an ESTIMATE while other threads are modifying.
- Prefer mappingCount() (returns long) for big maps.


Q11. Atomic methods (Java 8) - use these for check-then-act
-----------------------------------------------------------
  putIfAbsent(k, v)
  computeIfAbsent(k, k -> value)
  computeIfPresent(k, (k, v) -> newV)
  compute(k, (k, v) -> newV)
  merge(k, 1, Integer::sum)      <- best for counters
  replace(k, old, new), remove(k, v)

  BAD  (NOT atomic even on CHM):
       if (!map.containsKey(k)) map.put(k, v);
       map.put(k, map.get(k) + 1);
  GOOD:
       map.putIfAbsent(k, v);
       map.merge(k, 1, Integer::sum);

  Rule: each single method is atomic. A SEQUENCE of methods is NOT atomic.


Q12. Iterator behaviour
-----------------------
- Weakly consistent: never throws ConcurrentModificationException.
- May or may not show updates made after the iterator was created.
- HashMap iterator is fail-fast (throws CME).


Q13. Bulk operations (Java 8)
-----------------------------
  forEach(parallelismThreshold, ...), reduce, search, reduceValues ...
  Run in parallel using ForkJoinPool.commonPool() if map size > threshold.


Q14. Pitfalls
-------------
- Do NOT do slow / blocking work inside compute / merge / computeIfAbsent: bucket lock is held.
- Do NOT update the same map from inside those lambdas -> deadlock or
  IllegalStateException("Recursive update").
- Compound logic across 2 keys is NOT atomic (no map-wide lock).
- Pre-size the map if you know the count: new ConcurrentHashMap<>(expectedSize).


Q15. HashMap vs Hashtable vs synchronizedMap vs ConcurrentHashMap
-----------------------------------------------------------------
| Feature        | HashMap     | Hashtable   | synchronizedMap | ConcurrentHashMap        |
|----------------|-------------|-------------|-----------------|--------------------------|
| Thread safe    | No          | Yes         | Yes             | Yes                      |
| Lock           | none        | whole map   | whole map       | per bucket + CAS         |
| Null key/value | 1 key/many  | No          | depends on map  | No                       |
| Iterator       | fail-fast   | fail-fast   | fail-fast       | weakly consistent        |
| Reads          | -           | locked      | locked          | lock free                |


30-SECOND ANSWER (memorize)
---------------------------
"ConcurrentHashMap is a thread-safe map. In Java 8 it has no segments. It uses an array of
buckets. If a bucket is empty it inserts with CAS, no lock. If the bucket has nodes it locks
only the head node with synchronized, so threads on different buckets never block each other.
get() is lock free because value and next are volatile. Resize is done cooperatively by
multiple threads using ForwardingNodes. Long chains (>=8) become red-black trees. It does not
allow null keys or values, and its iterators are weakly consistent. For compound operations
use merge / compute / putIfAbsent, because separate get-then-put is not atomic."

```
