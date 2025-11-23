package ARRAY;

public class ThirdBitUsingBitManupulation {
    public static void main(String[] args) {
        int num = 13;   // binary 1101
        int thirdBit = (num >> 2) & 1;  // shift right 2, mask with 1
        System.out.println("3rd bit: " + thirdBit);
    }
}
