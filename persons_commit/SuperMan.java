public class SuperMan extends Man{
    private int count=0;

    SuperMan(String n,String d){
        super(n,d);
    }
    SuperMan(){
        super("superMan","I can fly");
    }
    @Override
    public void move(){System.out.println("I am flying...");}

    public void fly(){System.out.println("fly，I am a SuperMan");}

    @Override
    public int changeSomething(){
        count=count+1;
        return count;
    }

}
