package BasicJava;
class AA{
    public int x=5;
    protected int y=6;
    private int z=7;
    public int getZ() {
        return z;
    }
}
class B extends AA {
}

public class AccessModifier {
    public static void main(String[] args) {
        AA aa=new AA();
        System.out.println(aa.x);
        System.out.println(aa.y);
        System.out.println(aa.getZ());
        System.out.println("------------------------------------------");
        B bb=new B();
        System.out.println(bb.x);
        System.out.println(bb.y);
        System.out.println(bb.getZ());
    }
}
