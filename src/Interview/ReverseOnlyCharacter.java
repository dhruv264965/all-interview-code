package Interview;

public class ReverseOnlyCharacter {
    public static void main(String[] args) {
        String input = "a1b$c";
        char arr[]=input.toCharArray();
        int left=0,right=arr.length-1;
        while (left<right){
            if(!Character.isLetter(arr[left])){
                left++;
            }
            if(!Character.isLetter(arr[right])){
                right--;
            }
            else {
                char temp=arr[left];
                arr[left]=arr[right];
                arr[right]=temp;
                left++;
                right--;
            }
        }
        System.out.println(new String(arr));
    }
}
