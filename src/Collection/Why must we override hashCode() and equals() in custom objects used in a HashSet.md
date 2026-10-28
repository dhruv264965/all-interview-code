# Why must we override hashCode() and equals() in custom objects used in a HashSet

```text
🔧 Because HashSet uses:
**hashCode()** → to find the bucket where the object might be stored.
**equals()** → to check if two objects are actually equal.

Here's what HashSet does (via the internal HashMap):
It calls myObject.hashCode() to find the bucket.
If the bucket already contains objects (hash collision), it calls myObject.equals(existingObject) to check for logical equality.
If equals() returns true, it does not add the duplicate.


❌ If you don’t override equals() and hashCode - It uses default equal and hashcode method
               // checks if both references point to same object
               Two different objects with the same values will have:
               Different hash codes
               equals() will return false (because this != obj)
               So Java treats them as completely different objects.


Without overriding equals() and hashCode(), custom objects are compared by reference, not by field values.
 So logically equal objects are treated as different in HashSet, and duplicates are not detected.”

```
