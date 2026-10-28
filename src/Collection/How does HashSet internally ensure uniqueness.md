# How does HashSet internally ensure uniqueness

```text
Internally hashset use hashmap-
Set<String> set = new HashSet<>();
set.add("Apple");
set.add("Banana");
set.add("Apple"); // This won't be added again

 "Apple" is hashed → inserted.
 Next "Apple" is hashed again → same hash → checks with equals() → already present → ignored.


 HashMap does not allow duplicate keys.
 When you try to add an element to HashSet, it calls map.put(element, PRESENT).
 If the element already exists (i.e., the key is already present in the map), it is not added again.

🔄 How it Works Internally:
hashCode() of the object is computed.
The hash is used to find the bucket (array index).
If the bucket is empty → insert the object.
If the bucket has other elements (hash collision):
It uses equals() to check if the element already exists.
If equals() returns true, it is not added.
Otherwise, it adds the element to the bucket (like a linked list or tree).


```
