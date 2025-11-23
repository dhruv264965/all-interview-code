package STRING;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeprateLetterAndNumber {
    public static void main(String[] args) {
        String str="g1b4v2c7m9n4";

        StringBuilder digit=new StringBuilder();
        StringBuilder character=new StringBuilder();
         for(char ch:str.toCharArray()){
            if(Character.isDigit(ch)){
                digit.append(ch);
            }
            else if(Character.isLetter(ch)){
                character.append(ch);
            }
        }
        System.out.println(digit);
        System.out.println(character);


        String letters=str.replaceAll("\\d","");
        System.out.println(letters);
        String numbers=str.replaceAll("\\D","");
        System.out.println(numbers);
        List<String> resultList = new ArrayList<>();
        resultList.add(letters);
        resultList.add(numbers);

        // Print the list
        System.out.println(resultList);

        //sort and print
        char[] sortCharacterArray=letters.toCharArray();
        Arrays.sort(sortCharacterArray);
        String sortedLetters = new String(sortCharacterArray);

        char[] sortNumberArray=numbers.toCharArray();
        Arrays.sort(sortNumberArray);
        String sortedNumbers = new String(sortNumberArray);

        List<String> resultList1 = new ArrayList<>();
        resultList1.add(sortedLetters);
        resultList1.add(sortedNumbers);

        // Print the sorted list
        System.out.println(resultList1);
    }
}
