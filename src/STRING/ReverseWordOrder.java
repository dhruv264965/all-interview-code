package STRING;

public class ReverseWordOrder {
    public static void main(String[] args) {
        String input = "Java is fun";
        String word[]=input.split(" ");
        // 1st appraoch
        int n=word.length;
        if(n>0){
            StringBuilder sb=new StringBuilder();
            sb.append(word[n-1]);
            for(int i=n-2;i>=0;i--){
                sb.append(" ").append(word[i]);
            }
            System.out.println(sb);
        }
       // 2nd appoach
        StringBuilder ss=new StringBuilder();
        for(int i=word.length-1;i>=0;i--){
            ss.append(word[i]).append(" ");
//            if(i!=0){
//                ss.append(" ");
//            }
        }
        System.out.println(ss.toString().trim());

    }
}
