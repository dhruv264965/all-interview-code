package STRING;

public class ToggleTheCharacterOnly {
    static boolean isEnglish(char c){
         return ((c>=65 && c<=90) || ( c>=97 && c<=122)) ;
    }
    static void toggleTheCharacter(String str){
        StringBuilder st=new StringBuilder(str);
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            int ascii=(int) ch;
            if(isEnglish(ch) && (ch>=65 && ch<=90)){
                ascii+=32;
                char dh=(char) ascii;
                st.setCharAt(i,dh);
            }
            else if(isEnglish(ch) && ( ch>=97 && ch<=122))  {
                ascii-=32;
                char dh=(char) ascii;
                st.setCharAt(i,dh);
            }

        }
        System.out.println(st);
    }
    public static void main(String[] args) {
        String str="Dhruv123Shukla";
        toggleTheCharacter(str);

    }
}
