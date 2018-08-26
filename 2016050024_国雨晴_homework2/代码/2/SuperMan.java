public class SuperMan extends Man{


    SuperMan(String n,String d){ super(n,d); }

    SuperMan(){
        super("superMan","I can fly");
    }
    @Override
    public void move(){
        System.out.println("I am flying...");
    }

    public void fly(){
        System.out.println("fly，I am a SuperMan");
    }

    private int count=0;
    @Override
    public int changeSomething(){ return ++count; }

}
