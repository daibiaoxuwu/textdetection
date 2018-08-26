public class Singleton{

    private static boolean flag=false;
    private static Singleton singleton;

    private Singleton(){flag=true;}
    public static Singleton getInstance(){
        if(flag==false) singleton=new Singleton();
        return singleton;
    }
}
