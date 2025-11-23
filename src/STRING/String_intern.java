package STRING;

public class String_intern {
    public static void main(String[] args) {
        String s1="Dhruv";
        String s2=new String("Dhruv");
        String s6=new String("Dhruv");
        String s3="Dhruv";
    //    System.out.println(s1);
        System.out.println("10 "+ s1==s2);  //false
        System.out.println("11 "+s2==s6);  //true
        System.out.println("12 "+s1.equals(s2));// true
        System.out.println("13 "+s6.equals(s3));// true


















        System.out.println(" }"+s2==s6);
        System.out.println(s1==s3);////true
        System.out.println(s1==s2);   /// false
        System.out.println(s1.equals(s2));  ///true
        String s4=s1.intern();

        System.out.println(s1==s4);   // true
        String s5=s2.intern();
        System.out.println(s2==s5);   //false

        System.out.println(s2==s6);   //false

        String ss1=new String("Dhruv").intern();
        String ss2=new String("Dhruv").intern();
        System.out.println(ss1==ss2);      // true

// 1. Java Memory Areas
//Java manages memory using the following areas:
//
//✅ Stack Memory: Stores method calls, local variables, and references to objects in the heap.
//✅ Heap Memory: Stores objects created using new keyword.
//✅ String Pool (Part of Heap): Stores string literals ("" notation).
//
//2. How Strings Are Stored in Java
//Strings in Java are immutable and managed in two ways:
//
//String Type	Stored In
//String Literal ("text")	String Pool
//Using new String("text")	Heap Memory (Separate Object)
//Using intern()	Forces the string to be stored in the String Pool
//3. Memory Allocation in Your Code
//Let's analyze line-by-line how memory is allocated:
//

//String s1 = "Dhruv";  // (1)
//String s2 = new String("Dhruv"); // (2)
//String s6 = new String("Dhruv"); // (3)
//String s3 = "Dhruv";  // (4)
//Step 1: Creating s1 and s3

//String s1 = "Dhruv";
//String s3 = "Dhruv";
//✅ Stored in the String Pool (Only one copy is created).
//✅ Both s1 and s3 refer to the same object in the String Pool.
//

//String Pool:
// ┌────────────────┐
// |   "Dhruv"      | ← s1, s3
// └────────────────┘
//👉 s1 == s3 returns true because they point to the same memory location in the String Pool.
//
//Step 2: Creating s2 and s6

//String s2 = new String("Dhruv");
//String s6 = new String("Dhruv");
//✅ Stored in the Heap Memory (Separate objects).
//✅ Even though "Dhruv" already exists in the String Pool, new String("Dhruv") forces a new object to be created in Heap.
//

//Heap Memory:
// ┌────────────────┐
// |   "Dhruv"      | ← s2
// ├────────────────┤
// |   "Dhruv"      | ← s6
// └────────────────┘
//👉 s2 == s6 returns false because they are two different objects in Heap Memory.
//
//Step 3: Using intern()

//String s4 = s1.intern();
//✅ s1 is already in the String Pool, so s4 will point to the same object.
//
//java
//Copy
//Edit
//String s5 = s2.intern();
//✅ s2 is stored in the Heap.
//✅ intern() checks if "Dhruv" already exists in the String Pool. Since it does, s5 will point to that pool object.
//

//String Pool:
// ┌────────────────┐
// |   "Dhruv"      | ← s1, s3, s4, s5
// └────────────────┘
//👉 s2 == s5 returns false because s2 is still in the Heap.
//
//Step 4: Interning Two New Strings
//java
//Copy
//Edit
//String ss1 = new String("Dhruv").intern();
//String ss2 = new String("Dhruv").intern();
//✅ Each new String("Dhruv") creates a new object in Heap, but intern() makes both ss1 and ss2 refer to the same String Pool object.
//
//arduino
//Copy
//Edit
//Heap Memory:
// ┌────────────────┐
// |   "Dhruv"      | (discarded)
// └────────────────┘
//
//String Pool:
// ┌────────────────┐
// |   "Dhruv"      | ← ss1, ss2
// └────────────────┘
//👉 ss1 == ss2 returns true because both refer to the same object in the String Pool.
//
//4. Output Breakdown
//java
//Copy
//Edit
//System.out.println(s1);  // Output: Dhruv
//
//System.out.println(" }" + s2 == s6);
//// False, because "}" + s2 creates a new string, and (String + Object) != (Object)
//
//System.out.println(s1 == s3);  // True (Both refer to the same pool object)
//System.out.println(s1 == s2);  // False (s1 is in Pool, s2 is in Heap)
//System.out.println(s1.equals(s2));  // True (Content is the same)
//
//System.out.println(s1 == s4);  // True (s4 is interned, so it refers to Pool object)
//System.out.println(s2 == s5);  // False (s2 is in Heap, s5 is in Pool)
//System.out.println(s2 == s6);  // False (Different Heap objects)
//
//System.out.println(ss1 == ss2);  // True (Both are interned, so they refer to the same Pool object)
//5. Summary
//String Operation	Memory Location	Comparison Result
//String s1 = "Dhruv";	String Pool
//String s2 = new String("Dhruv");	Heap
//String s3 = "Dhruv";	String Pool (Same as s1)	s1 == s3 → true
//String s4 = s1.intern();	String Pool (Same as s1)	s1 == s4 → true
//String s5 = s2.intern();	String Pool (Same as s1)	s2 == s5 → false
//String ss1 = new String("Dhruv").intern();	String Pool
//String ss2 = new String("Dhruv").intern();	String Pool	ss1 == ss2 → true
//6. Key Takeaways
//📌 String Pool Optimization:
//
//When using string literals (""), Java optimizes memory by storing them in a shared pool.
//intern() forces a string to move to the pool if not already there.
//📌 Heap vs. Pool:
//
//Strings created with new String("text") always create a new object in the Heap.
//Using intern() ensures that only one instance of the string exists in the pool.
//📌 Comparisons (== vs .equals()):
//
//== compares memory addresses (references).
//.equals() compares content.
//📌 Best Practice:
//✅ Use intern() only if you need strict memory optimization.
//✅ For comparison, always use .equals() instead of ==.


    }
}
