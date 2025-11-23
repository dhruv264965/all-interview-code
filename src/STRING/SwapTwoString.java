package STRING;

public class SwapTwoString {
    public static void main(String[] args) {
        String s1="Dhruv";
        String s2="Shukla";
        System.out.println(s1+"  "+s2);
        s1=s1+s2;
        System.out.println("NEW S1 -"+s1);
        s2=s1.substring(0,s1.length()-s2.length());
        System.out.println(s2);
        s1=s1.substring(s2.length());
        System.out.println(s1);
        System.out.println(s1+"  "+s2);
    }
}
