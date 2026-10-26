# interview question i am using java 11 .why u are using how it is different fromjava 8,10

```text
✅ How to Answer
🔹 1. Java 11 is a Long-Term Support (LTS) version
“I use Java 11 because it is an LTS (Long-Term Support) release, like Java 8. That means it gets extended support and
updates for several years, which makes it a stable choice for production.”

🔸 Java 8 (LTS) → March 2014
🔸 Java 11 (LTS) → September 2018
🔸 Java 10 → Non-LTS, short-lived (March–September 2018 only)

🔹 2. Performance Improvements Over Java 8
“Java 11 has significant JVM-level improvements and performance optimizations over Java 8, including GC enhancements
and reduced memory usage.”

Improved Garbage Collection (G1 by default)

Faster startup, better throughput than Java 8

🔹 3. Cleaner Code with New Features
“Java 11 supports more concise and modern syntax, making the code cleaner and easier to maintain.”

🌟 Key Language Features from Java 10 & 11:
Feature	Java Version	Description
var keyword (local variables)	Java 10	Type inference: var name = "John";
New String methods	Java 11	isBlank(), lines(), repeat(), strip()
Files.readString(Path)	Java 11	Read files as string easily
HttpClient (standard)	Java 11	Modern, non-blocking HTTP client
Lambda Parameter var	Java 11	Use annotations in lambdas: (var x) -> ...

🔹 4. Modular JDK (from Java 9)
“Starting from Java 9, the JDK was modularized with the Java Platform Module System (JPMS), and Java 11 benefits
from this modular structure — it results in smaller, more secure applications.”

✅ You can even create a custom runtime using jlink in Java 11.

🔹 5. Removed Deprecated APIs (Cleaner Core Library)
“Java 11 removed older, deprecated APIs like Java EE modules (javax.xml.bind, javax.activation, etc.), which
encourages use of modern alternatives.”

✅ Sample Short Answer (for interview)
“I'm using Java 11 because it's an LTS version, ensuring long-term stability and support. Compared to Java 8, it
offers significant performance improvements, modern language features like var, enhanced String and file APIs, and a
better HTTP client. Unlike Java 10, which was a short-term release, Java 11 is stable and production-ready.”

✅ Bonus: New String Methods in Java 11 (compared to Java 8)
Method	Java 11
isBlank()	Yes
lines()	Yes
repeat(n)	Yes
strip(), stripLeading(), stripTrailing()	Yes
Java 8 had only trim()
```
