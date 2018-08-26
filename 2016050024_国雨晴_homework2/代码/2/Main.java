public class Main {
    public static void main(String[] args){
        Man man=new Man("man","nothing");
        System.out.println( man.changeSomething());
        man.getDescription();
        man.getName();
        man.move();

        SuperMan superman=new SuperMan("superman","nothing");
        System.out.println(superman.changeSomething());
        superman.getDescription();
        superman.getName();
        superman.move();
        superman.fly();

        Person pman=new Man("pman","nothing");
        System.out.println(pman.changeSomething());
        pman.getDescription();
        pman.getName();

        Person psman=new SuperMan("psman","nothing");
        System.out.println(psman.changeSomething());
        psman.getDescription();
        psman.getName();

        Man msMan=new SuperMan("msMan","nothing");
        System.out.println(msMan.changeSomething());
        msMan.getDescription();
        msMan.getName();
        msMan.move();

    }
}

