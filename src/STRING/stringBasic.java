package STRING;

public class stringBasic {
    public static void main(String[] args) {
        String str="dhruv";
        String gtr="daruv";
        System.out.println(str.charAt(1));
        System.out.println(str.indexOf("d"));
        System.out.println(str.compareTo(gtr));
        System.out.println(str.contains("dhruv"));
        System.out.println(str.contains("d"));
        System.out.println(str.startsWith("dh"));
        System.out.println(str.concat(gtr));
    }
}
