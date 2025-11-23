package SlidingWindow;

public class PrintAllPossibleSubStringOfGivenStringOfK {
    static void printAllSubString(String s,int k){
        int i=0,j=0;
        int n=s.length();
        while (j<n){
            if(j-i+1<k){
                j++;
            }
            if(j-i+1==k){
                System.out.println(s.substring(i,j+1));
                i++;
                j++;
            }
        }
    }

    public static void main(String[] args) {
        String s="abaaca";
        int k=3;
        printAllSubString(s,k);
    }
}
