package STRING;

import java.util.Scanner;

public class ToggleTheCharacter {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        StringBuilder str=new StringBuilder("Dhruv SHukla");
        System.out.println(str);
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);       //get charcter
            if(ch==' ')  continue;       // if space  is also there
            boolean flag=true;           //for capital character
            int asci=(int)ch;
            if(asci>=97) flag=false;      //for small character
            if(flag==true){
                asci+=32;  // if character is capital to change to small need to add 32 to its ascii value
                char dh=(char)asci;
                str.setCharAt(i,dh);
            }
            else {
                asci-=32;
                char dh=(char) asci;
                str.setCharAt(i,dh);
            }

        }
        System.out.println(str);

    }
}
