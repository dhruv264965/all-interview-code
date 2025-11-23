package STRING;
//The StringBuilder in Java is used for storing the mutable (changeable) sequence which means we can update the
// elements of the StringBuilder class without creating a new StringBuilder sequence in memory.
//StringBuilder class operations are faster than StringBuffer because StringBuilder class is not thread-safe.
// The StringBuilder class provides no guarantee of synchronization, however StringBuffer class operations are
// synchronized. However, in most cases, operations on a string are performed on the same thread,
// hence StringBuilder class can be used. StringBuilder class is preferred over StringBuffer class due to its faster operations.
public class Builder {
    public static void main(String[] args) {
        StringBuilder sb=new StringBuilder("dhruv");
        sb.append("shukla");
        System.out.println(sb);
        sb.setCharAt(1,'H');
        System.out.println(sb);
        sb.insert(0,'D');
        System.out.println(sb);

    }
}
