package HashMap;

public class CountFrequencyOfStringBruteForce {
    static void countFreq(String str){
        int freq[]=new int[254];
        for (int i=0;i<str.length();i++){
            freq[str.charAt(i)]++;
        }
        for (int i=0;i<freq.length;i++){
            if(freq[i]!=0){
                System.out.println((char) i+" "+freq[i]);
            }
        }
    }

    public static void main(String[] args) {
        countFreq("dhruv shukla");
    }
}
