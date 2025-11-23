package Java_Interview;

import java.util.HashSet;
import java.util.Objects;

public class EqualsAndHashCode {
    private String name;

    public EqualsAndHashCode(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EqualsAndHashCode that = (EqualsAndHashCode) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public static void main(String[] args) {
        HashSet<EqualsAndHashCode>hashSet=new HashSet<>();
        hashSet.add(new EqualsAndHashCode("hello"));
        hashSet.add((new EqualsAndHashCode("hello")));
        System.out.println(hashSet.contains(new EqualsAndHashCode("hello")));
    }
}
