package BasicJava;

import java.io.FileNotFoundException;
import java.io.IOException;

class Parenttt{  // if parent have some exception like IO exception here if we override in child and child throwing FileNotFound
                 // does program compile- yes
         // becaz parent have IO exception and child have filenotfound . IO exception is parent it means filenotfound extend
    //   Io exception so it will complile but vice verssa is not impossile
    // parent-> FileNotFound  , child-> IOException compile->NO
    public void start() throws IOException{
        System.out.println(" Hi i am parent ");
    }
}
class childd extends Parenttt{
    @Override
    public void start() throws FileNotFoundException {
        System.out.println(" Hi i am child ");
    }
}
public class InheritanceDemo {
    public static void main(String[] args) throws Exception {
        childd c=new childd();
        c.start();
    }
}
