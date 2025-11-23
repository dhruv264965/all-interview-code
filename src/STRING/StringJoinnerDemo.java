package STRING;

import java.util.StringJoiner;

public class StringJoinnerDemo {
    public static void main(String[] args) {
        String str=new String();
        StringJoiner stringJoiner=new StringJoiner(",");
        stringJoiner.add("dhruv");
        stringJoiner.add("shukla");
        System.out.println(stringJoiner);

        StringBuilder sb=new StringBuilder();
        sb.append("dhruv");
        sb.append("shukla");
        System.out.println(sb);
    }
}
