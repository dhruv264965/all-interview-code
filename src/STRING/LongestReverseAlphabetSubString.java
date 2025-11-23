package STRING;

public class LongestReverseAlphabetSubString {
    public static void main(String[] args) {
        String str="BBAASSSSRRPOCCBAAACBABA";
        int max_length=1;
        int curr_Length=1;
        for(int i=1;i<str.length();i++){
            if(str.charAt(i-1)>=str.charAt(i)){
                curr_Length++;
            }
            else {
                curr_Length=1;
            }
            max_length=Math.max(max_length,curr_Length);
        }

        System.out.println(max_length);
    }
}
