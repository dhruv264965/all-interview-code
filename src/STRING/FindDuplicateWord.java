package STRING;

import java.util.HashMap;
import java.util.Map;

//Approach- split kar ke array mai convert kar do. then 2 loop laga ke compare kar lunga and count kar lunga and words[i]="0" dal dunga to avoid printing word
public class FindDuplicateWord {
    static void findDuplicateWordInAStringBruteForce(String str){
        int count;
       String s=str.toLowerCase();
        System.out.println(s);
        String[] words=s.split(" ");
        for(String x:words){
            System.out.print(x+" ");
        }
        System.out.println();
        for(int i=0;i< words.length;i++){
              count =1;
            for(int j=i+1;j< words.length;j++){
                if(words[i].equals(words[j])){
                    count++;
                    words[j]="0" ;    ////Set words[j] to 0 to avoid printing visited word
                }
            }
            if(count>1 && words[i]!="0"){
                System.out.println(words[i]);
            }

        }
    }
    static void findDuplicateWordInAStringOptimalForce(String str){
       String s=str.toLowerCase();
       String[] words=s.split(" ");
        for(String x:words){
            System.out.print(x+" ");
        }
        System.out.println();
        HashMap<String,Integer>map=new HashMap<>();
        for(String word:words){
            map.put(word,map.getOrDefault(word,0)+1);
        }
        System.out.println(map);
        for(Map.Entry<String,Integer> entry:map.entrySet()){
            if(entry.getValue()>1){
                System.out.println(entry.getKey());
            }
        }
    }
    public static void main(String[] args) {
        String str="Big black bug bit a  black dog on his big black nose";
      //  findDuplicateWordInAStringBruteForce(str);
        findDuplicateWordInAStringOptimalForce(str);
    }
}
