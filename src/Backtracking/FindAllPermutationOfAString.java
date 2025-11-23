package Backtracking;

public class FindAllPermutationOfAString {
    public static String swap(String str, int i,int j){
        char[] ch=str.toCharArray();
        char temp=ch[i];
        ch[i]=ch[j];
        ch[j]=temp;
        return String.valueOf(ch);
    }
    public static void generatePermulation(String str, int start,int end){
        if(start==end){ //base case
            System.out.println(str); //print all permutation
            return;
        }
        for(int i=start;i<=end;i++){
            str=swap(str,start,i);  // swap the current character to start character
            generatePermulation(str,start+1,end);// recursion of the next char.  start+1-> fixed start element
            str=swap(str,start,i); // backtrack to store the original order
        }

    }
    public static void main(String[] args) {
        String str="ABC";
        generatePermulation(str,0,str.length()-1);
    }
}
