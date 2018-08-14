package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class  Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        final int M = sc.nextInt();
        List<People> peopleList = new ArrayList<>();

        for (int i = 0; i < N; ++i) {
            String type = sc.next();
            if (type.equals("Teacher")) {
                int num = sc.nextInt();
                char sex = sc.next().charAt(0);
                int age = sc.nextInt();
                String major = sc.next();
                peopleList.add(new Teacher(num, sex, age, major));
            } else if (type.equals("Student")) {
                int num = sc.nextInt();
                String name = sc.next();
                char sex = sc.next().charAt(0);
                int age = sc.nextInt();
                int year = sc.nextInt();
                peopleList.add(new Student(num, sex, age, name, year));
            }
        }
        for (int i = 0; i < M; ++i) {
            String type=sc.next();
            int nextInt=sc.nextInt();
            if(type.equals("S")){
                for(People people:peopleList){
                    if(people instanceof Student && people.getNum()==nextInt){
                        people.addTimes();
                        break;
                    }
                }
            } else{
                for(People people:peopleList){
                    if(people instanceof Teacher && people.getNum()==nextInt){
                        people.addTimes();
                        break;
                    }
                }
            }
        }
        for(People people:peopleList){
            if(people.getTimes()==People.maxTimes){
                System.out.println(people.toString());
            }
        }
    }
}
class People {
    static int maxTimes=0;

    int num;
    char sex;
    int age;
    private int times=0;

    int getNum(){return num;}
    int getTimes(){return times;}
    void addTimes(){
        times++;
        maxTimes=Math.max(times,maxTimes);
    }

}

class Teacher extends People{
    private String major;

    Teacher(int num,char sex,int age,String major){
        this.num=num;
        this.sex=sex;
        this.age=age;
        this.major=major;
    }

    @Override
    public String toString(){
        return "Teacher "+num+" "+sex+" "+age+" "+major;
    }
}
class Student extends People{
    private String name;
    private int year;

    Student(int num,char sex,int age,String name, int year){
        this.num=num;
        this.sex=sex;
        this.age=age;
        this.name=name;
        this.year=year;
    }

    @Override
    public String toString(){
        return "Student "+num+" "+name+" "+sex+" "+age+" "+year;
    }
}
