package STRING;

public class CountVowelAndConsonent {
    public static void main(String[] args) {
        String str="abcsweqaszxcbiokljuthbc";
        int vowel=0;
        int consonent=0;
        for (char ch:str.toCharArray()){
            if(Character.isLetter(ch)){
                if("aeiou".indexOf(ch)>=0){
                    vowel++;
                }
                else consonent++;
            }
        }
        System.out.println("vowel "+ vowel +" consonent "+ consonent);
    }
}
