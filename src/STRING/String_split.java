package STRING;

public class String_split {
    public static void main(String[] args) {
        String str="dogcat cat dogcatdog";
        int count=0;
        String[] tokens=str.split("cat\\s*");
        for(String token:tokens){
            count++;
            System.out.println(token);
        }
        System.out.println(count);
    }
}
