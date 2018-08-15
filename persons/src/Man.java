public class Man {
    private String name,description;
    private int count=0;
    Man(String n,String d){
        name=n;
        description=d;
    }
    public String getName(){return name;}
    public String getDescription(){return description;}
    public int changeSomething(){
        count=count-1;
        return count;
    }
    public void move(){System.out.println("I am moving...");}
}

