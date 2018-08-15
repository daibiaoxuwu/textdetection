package com.company;

interface Person
{
    String getName();//返回name
    String getDescription();//返回description
    int changeSomething();
}

class Man implements Person{
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

class SuperMan extends Man{
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

public class Tests {
    public static void main(String[] args){
        Man man=new Man("man","nothing");
        SuperMan superman=new SuperMan("superman","nothing");
        Person pman=new Man("pman","nothing");
        Person psman=new SuperMan("psman","nothing");
        Man msMan=new SuperMan("msMan","nothing");

    }
}
