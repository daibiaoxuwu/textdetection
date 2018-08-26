public class Man implements Person {
    private String name="";
    private String description="";
    private int count;
    Man(String name,String description){
        this.name=name;
        this.description=description;
        this.count=0;
    }

    @Override
    public String getName(){return name;}
    @Override
    public String getDescription(){return description;}
    @Override
    public int changeSomething(){ return --count; }

    public void move(){
        System.out.println("I am moving...");
    }
}

