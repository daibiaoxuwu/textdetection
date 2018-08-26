public class Singleton{
    private Singleton(){};
    static int count=0;
    static Singleton instance;
    public static Singleton getInstance(){
        if(count==0){
            instance=new Singleton();
        }
        count++;
        return instance;
    }
}