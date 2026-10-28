# How get and put method internally works

```text
✅ Example
HashMap<String, Integer> map = new HashMap<>();

map.put("dhruv", 100);

int value = map.get("dhruv");


You asked:

👉 How does map.get("dhruv") fetch data?

🧠 Internal Working of HashMap (Simple Flow)

When you call:

map.get("dhruv");


Java performs these steps internally 👇

✅ Step 1 — Calculate Hash Code

Java calls:

"dhruv".hashCode()


Every object in Java has a hashCode() method.

Example:

"dhruv".hashCode() → some integer value (example: 9584321)


This number represents the key.

✅ Step 2 — Find Bucket Index

HashMap converts hashcode into array index:

index = hashcode % arraySize;


Example:

9584321 % 16 = 5


👉 So data is searched in bucket 5.

(HashMap internally stores data in an array of buckets.)

✅ Step 3 — Go to That Bucket

HashMap directly jumps to bucket index 5.

⚡ This is why HashMap is fast — no full searching.

✅ Step 4 — Compare Keys (Important)

Inside that bucket, there may be multiple entries (collision case).

So Java checks:

key.equals("dhruv")


NOT ==

It uses .equals() to match the exact key.

✅ Step 5 — Return Value

When key matches:

("dhruv", 100)


It returns:

100

🔎 Visual Representation
HashMap Array
--------------------------------
Index 0
Index 1
Index 2
Index 3
Index 4
Index 5  → ("dhruv",100)
Index 6
...


get("dhruv") directly jumps to index 5.

⚡ Why HashMap is Fast?

Because of:

✅ Hashing
✅ Direct index access

Average Time Complexity:

get() → O(1)
put() → O(1)

⚠️ Important Interview Points
1️⃣ HashMap uses:

hashCode() → find bucket

equals() → confirm correct key

2️⃣ What if two keys have same hash?

👉 Called Collision

Java stores them as:

Linked List (Java 7)

Linked List → Balanced Tree (Java 8+ when large)

🔥 Interview One-Line Answer

“HashMap uses the key’s hashCode to locate a bucket and then uses equals() to find the exact entry and return the value.”

If you want, next I can show you internal diagram of HashMap put() vs get() — once you see it, HashMap becomes permanently clear.

yes please show the diagram

Perfect 👍
Here is a clear visual diagram of how HashMap works internally for both put() and get().

🧠 HashMap Internal Structure (Diagram)

A HashMap internally looks like an array of buckets.

        HashMap (Internal Array)
        ---------------------------------
Index   Data Stored
---------------------------------
0       null
1       null
2       ("ram", 50)
3       null
4       ("amit", 80)
5       ("dhruv", 100)  ---> target bucket
6       null
7       null
...

✅ 1️⃣ When put("dhruv",100) is called
Step-by-step flow:
put("dhruv",100)
        |
        v
1. hashCode("dhruv")  → 9584321
        |
        v
2. index = hash % arraySize
           9584321 % 16 = 5
        |
        v
3. Go to bucket index 5
        |
        v
4. Store Entry
   ("dhruv",100)

Internal Entry Object

Each element stored is actually an object:

Node {
   hash
   key
   value
   next (for collision)
}

✅ 2️⃣ When get("dhruv") is called
get("dhruv")
        |
        v
1. hashCode("dhruv")
        |
        v
2. Calculate index → 5
        |
        v
3. Jump directly to bucket 5
        |
        v
4. Compare keys using equals()
        |
        v
5. Match found → return 100


⚡ Notice:
No full search → Direct access.

✅ Collision Case (Very Important)

Sometimes two keys go to same index.

Example:

Index 5
   |
   v
("dhruv",100) → ("rahul",200) → ("john",300)


This is called collision.


How Java handles it:

Java 7 → Linked List

Java 8+ → Linked List becomes Balanced Tree (if large)

🔥 Full Visual Flow
KEY → hashCode() → bucket index → equals() check → value returned

⚡ Why HashMap is O(1)?

Because it does:

✅ Direct array access
❌ Not linear searching

🎯 Interview Pro Tip

If interviewer asks:

👉 “Why both hashCode() and equals()?”

Answer:

hashCode() finds the bucket quickly, and equals() finds the exact key inside that bucket.
```
