package STRING;

public class HowManyLowerCaseAndUpperCaseCharacter {
    public static void main(String[] args) {
        int lowerCaseCount=0;
        int upperCaseCount=0;
        String str="WelcOme To CHanneL";
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            if(ch>=65 && ch<=90){
                upperCaseCount++;
            }
            else if (ch>=97 && ch<=122){
                lowerCaseCount++;
            }
        }
        System.out.println("Number of lower case "+lowerCaseCount);
        System.out.println("Number of upper case "+upperCaseCount);
    }
}
