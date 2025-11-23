package BasicMath;

public class ConvertAllZerosToOneAndOneToZero {
    public static void main(String[] args) {
        int number=51460104;
        String str="";
        String convertString=Integer.toString(number);
        System.out.println(convertString);
        int n=convertString.length();
        for(int i=0;i<n;i++){
            if(convertString.charAt(i)=='0'){
                str=str+'1';
            }
            else if(convertString.charAt(i)=='1'){
                str=str+'0';
            }
            else str=str+convertString.charAt(i);
        }
        System.out.println("Converted number is: "+str);
    }
}
