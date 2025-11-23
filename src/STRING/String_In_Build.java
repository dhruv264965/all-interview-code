package STRING;

import java.util.Arrays;

public class String_In_Build {
    public static void main(String[] args) {
        int count = 0;
        String s = "Hey,I am coiming ,today";
        String s1 = "dogcat cat dogcatdog";
        System.out.println(s.length());
        System.out.println(s.charAt(6));
        System.out.println(s.substring(2));
        System.out.println(s.substring(2, 7));


        String str[] = s.split(",");
        String str1[] = s1.split("cat");
        int cont = str1.length-1;
        System.out.println("count word"+cont);
        System.out.println(Arrays.toString(str));
        System.out.println(Arrays.toString(str1));

        for (int i = 0; i < str1.length-1; i++) {
        //    System.out.println(str1[i]);
            if(str[i]=="dog")
            {
                count++;
            }

        }
        System.out.println(count);
    }

}
