package STRING;

public class LagestOddNumberString {
    static void largestOddNumberString(String str){
        int  index=-1;
        for(int i=str.length()-1;i>=0;i--){
            if(str.charAt(i)%2!=0){
                    index=i;
                    break;
            }
        }
        if(index!=-1) {
          //  System.out.println(str.charAt(index));
            System.out.print(str.substring(0, index+1));

        }

    }
    public static String largestOddNumber(String str) {
        for(int i=str.length()-1;i>=0;i--){
            if(str.charAt(i)%2!=0){
                return str.substring(0,i+1);
            }
        }
        return "";

    }
    public static void main(String[] args) {
        String str="165362";
        largestOddNumberString(str);
        //System.out.println(largestOddNumber(str));



    }
}
