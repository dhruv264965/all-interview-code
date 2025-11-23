package STRING;

public class ReverseLastWordToFirstWord {
    static String reverseLastWordToFirstWord(String str){
        String [] words=str.split(" ");
        int n= words.length;
        if(n>1){

            StringBuilder result=new StringBuilder();
            // Add the last word first
            result.append(words[n - 1]);
            for(int i=n-2;i>=0;i--){
                result.append(" ").append(words[i]);
            }
            return result.toString();
        }
        else {
            return str;
        }

    }
    public static void main(String[] args) {
     String str="India is very beautiful country";
        System.out.println(reverseLastWordToFirstWord(str));

    }
}
