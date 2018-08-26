public class Main{
    public static void main(String[] args){
        Singleton s;
        s=Singleton.getInstance();
        System.out.println(s);
        s=Singleton.getInstance();
        System.out.println(s);
    }
}