package STRING;

public class MergeTwoStringOneByOne {
    public static void main(String[] args) {
        String str="abf";
        String str1="prc";
        int i=0,j=0;
        StringBuilder sb=new StringBuilder();
        while (i<str.length() || j<str1.length()){
            if(i<str.length()){
               sb.append(str.charAt(i));
                i++;
            }
            if(j<str1.length()){
               sb.append(str1.charAt(j));
                j++;
            }
        }
        System.out.println(sb.toString());
    }
}
