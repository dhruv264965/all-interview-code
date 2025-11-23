package STRING;

public class StringIntChar {
    public static void main(String[] args) {
        String str="abc";
        str+="xyz";
        System.out.println(str);
        str+='r';
        System.out.println(str);
        str+=10;
        System.out.println(str);
        System.out.println("fgh"+10+20);
        System.out.println("fgh"+(10+20));
        System.out.println(10+20+"fgh");
    }
}
