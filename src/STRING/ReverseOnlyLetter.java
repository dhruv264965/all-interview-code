package STRING;

public class ReverseOnlyLetter {
    public static String reverseOnlyLetters(String s){
        char[] arr=s.toCharArray();
        System.out.println(arr);
        int i=0;
        int j=s.length()-1;
        while (i<j) {
            if (isEnglish(arr[i]) && isEnglish(arr[j])) {
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                i++;
                j--;
            }
            else if (!isEnglish(arr[i])) {
                i++;
            } else if (!isEnglish(arr[j])) {
                j--;
            }
        }
        return new String(arr);

    }
    public static void reverseOnlyLetterss(String s){
       char[] arr= s.toCharArray();
       int left=0;
       int right= arr.length-1;
       while (left<right){
           if(!Character.isLetter(arr[left])){
               left++;
           }
           else if(!Character.isLetter(arr[right])){
               right--;
           }
           else {
               char temp=arr[left];
               arr[left]=arr[right];
               arr[right]=temp;
               left++;
               right--;
           }
       }
        System.out.println(new String(arr));



    }
    static boolean isEnglish(char c){
        return (65<=c && c<=90) || (97<=c && c<=122);
    }
    public static void main(String[] args) {
        String s="a-bC-dEf-ghIj";
//        String s="a@b@c@d@e@f";
        System.out.println(reverseOnlyLetters(s));
       reverseOnlyLetterss(s);


    }
}
