package STRING;

public class CountNumberOfSubStringOfGivenK {
   static long substrCount (String S, int K) {
      int i=0,j=0;
      int n=S.length();
      int count =0;
      while (j<n){
          if(j-i+1<K){
              j++;
          }
          if(j-i+1==K){
              count++;
              i++;
              j++;
          }
      }
      return count;

    }
    public static void main(String[] args) {
        String S="abaaca";
        int K=3;
        System.out.println(substrCount(S,K));

    }
}
