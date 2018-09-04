import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class People {
    int num, age, card;
    String sex, idString;

    int getNum(){
        return num;
    }

    int getCard(){
        return card;
    }

    void hitCard(){
        ++card;
    }

    @Override
    public String toString(){
        return idString;
    }

}

class Teacher extends People{
    String major;
    Teacher(String s){
        String[] information=s.split(" ");
        num=Integer.parseInt(information[1]);
        sex=information[2];
        age=Integer.parseInt(information[3]);
        major=information[4];
        idString=s;
        card=0;
    }

}
class Student2 extends People{
    String name;
    int year;
    Student2(String s) {
        String[] information = s.split(" ");
        num = Integer.parseInt(information[1]);
        name = information[2];
        sex = information[3];
        age = Integer.parseInt(information[4]);
        year = Integer.parseInt(information[5]);
        idString=s;
        card = 0;
    }
}

class Main4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        final int M = sc.nextInt();
        sc.nextLine();
        People[] people=new People[N];

        for (int i = 0; i < N; ++i) {
            String string = sc.nextLine();
            if(string.charAt(0)=='T')
                people[i]=new Teacher(string);
            else if(string.charAt(0)=='S')
                people[i]=new Student2(string);
        }
        for (int i = 0; i < M; ++i) {
            String string=sc.next();
            int nextInt=sc.nextInt();
            for(People somePeople:people)
                if ((somePeople instanceof Student2 ^ string.equals("T")) && somePeople.getNum() == nextInt)
                    somePeople.hitCard();
        }
        int max=0;
        for(People somePeople:people)
            max=Math.max(max,somePeople.getCard());

        for(People somePeople:people)
            if(somePeople.getCard()==max)
                System.out.println(somePeople);
    }
}
