# Why is Map not part of the Collection interface

```text
Because Map does not represent a collection of individual elements, but instead a collection of key-value
pairs, which is a fundamentally different concept.
| Feature                  | `Collection` (e.g., `List`, `Set`) | `Map`                          |
| ------------------------ | ---------------------------------- | ------------------------------ |
| Data type                | Stores **single elements**         | Stores **key-value pairs**     |
| Interface hierarchy      | Extends `Iterable`                 | Does **not** extend `Iterable` |
| Allows duplicate keys?   | N/A                                | ❌ No, keys must be unique      |
| Allows duplicate values? | ✅ Yes                              | ✅ Yes                         |
| Iteration type           | Elements (e.g., Strings, Integers) | Entries (`Map.Entry<K, V>`)    |

🧠 Design Reason (From Java Architecture Perspective)
Collection is meant for structures like:
List, Set, Queue – all store a group of elements.
Map stores associations between keys and values — not elements.
Including Map in the Collection interface would:
Break the design principle: “interface should define one clear abstraction.”
Force it to implement methods that make no sense for key-value pairs, like:
add(E element) – what would E be? A key or a value?
iterator() – iterate over what, keys, values, or entries?
```
