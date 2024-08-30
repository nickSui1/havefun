package priv.nick.cbs.topgun.test;

public class testConstructor {
    static {
        System.out.println("topgun first");
    }
    {
        System.out.println("topgun second");
    }
    public static void main(String[] args) {
        System.out.println("Main");
        A a = new b();
        System.out.println("1------");
        A a1 = new b(1);
        System.out.println("2------");
        c();
    }
    public static void c(){
        A a = new b();
    }
    public void d(){
        System.out.println("d------");
    }
}

class F{
    public static void main(String[] args) {
        testConstructor testConstructor = new testConstructor();
        System.out.println("F--------");
    }
}

class A{
    static {
        System.out.println("A constructor static first");
    }
    {
        System.out.println("A 构造代码块");
    }
    A(){
        System.out.println("A constructor");
    }
    A(int a){
        System.out.println("A constructor int,"+a);
    }
    static{
        System.out.println("A constructor static after");
    }
}

class b extends A{
    static {
        System.out.println("B constructor static first");
    }
    b(){
        System.out.println("B constructor");
    }
    b(int b){
        System.out.println("B constructor int,"+b);
    }
    static{
        System.out.println("B constructor static after");
    }
}
