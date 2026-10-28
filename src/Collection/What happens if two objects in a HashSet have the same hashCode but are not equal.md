# What happens if two objects in a HashSet have the same hashCode but are not equal

```text
They will both be stored in the HashSet.

🔧 Why?
HashSet uses a HashMap internally.
When you add an object:
It first uses hashCode() to find the bucket.
Then, it checks all existing objects in that bucket using equals().
If no existing object is equal (i.e., equals() returns false), it adds the new object — even if the hash code
 is the same.

 “If two objects have the same hash code but are not equal, HashSet will place them in the same bucket but treat
  them as distinct — so both will be stored. That’s why it’s important that hashCode() is consistent with equals()
   to avoid unexpected behavior.”
```
