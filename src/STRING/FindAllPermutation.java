package STRING;

public class FindAllPermutation {
    public static void generatePermutations(String str, String prefix) {
        if (str.isEmpty()) {
            System.out.println(prefix);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            String remaining = str.substring(0,i) + str.substring(i+1);
            generatePermutations(remaining, prefix + current);
        }
    }
    public static void main(String[] args) {
        String input = "abc";
        System.out.println("Permutations of the string are:");
        generatePermutations(input, "");
    }
    // Start: generatePermutations("abc", "")

    //Loop:
    //Pick 'a', remaining = "bc", prefix = "a"
    //→ generatePermutations("bc", "a")
    //Pick 'b', remaining = "c", prefix = "ab"
    //→ generatePermutations("c", "ab")
    //Pick 'c', remaining = "", prefix = "abc"
    //Base case → print "abc"
    //Pick 'c', remaining = "b", prefix = "ac"
    //→ "acb"
    //Pick 'b', remaining = "ac", prefix = "b"
    //Generates "bac", "bca"
    //Pick 'c', remaining = "ab", prefix = "c"
    //Generates "cab", "cba"
}
