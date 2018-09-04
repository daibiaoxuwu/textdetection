import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test{

    public static void main(String[] args) throws IOException {

        try {
            File path = new File(".");
            File[] files= path.listFiles();
            if(files!=null)
            for(File file:files){
                StringBuilder fileName=new StringBuilder(file.getName());
                if(Character.isLowerCase(fileName.charAt(0))){
                    fileName.setCharAt(0,Character.toUpperCase(fileName.charAt(0)));
                    if(!file.renameTo(new File(fileName.toString())))
                        System.out.println("Rename "+fileName+" Failed!");

                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader=new BufferedReader(fileReader);
                    bufferedReader.read();

                }
            }
            /*



            BufferedReader bufferedReader = new BufferedReader(fileReader);

            File file2 = new File("test.out");
            if (!file2.exists()) {
                file2.createNewFile();
            }
            FileWriter fileWriter=new FileWriter(file2);

            String[] string = bufferedReader.readLine().split(" ");
            final int N = Integer.parseInt(string[0]);
            final int M = Integer.parseInt(string[1]);

            List<People> peopleList = new ArrayList<>();

            for (int i = 0; i < N; ++i) {
                string = bufferedReader.readLine().split(" ");
                String type = string[0];
                if (type.equals("Teacher")) {
                    int num = Integer.parseInt(string[1]);
                    char sex = string[2].charAt(0);
                    int age = Integer.parseInt(string[3]);
                    String major = string[4];
                    peopleList.add(new Teacher(num, sex, age, major));
                } else if (type.equals("Student")) {
                    int num = Integer.parseInt(string[1]);
                    String name = string[2];
                    char sex = string[3].charAt(0);
                    int age = Integer.parseInt(string[4]);
                    int year = Integer.parseInt(string[5]);
                    peopleList.add(new Student(num, sex, age, name, year));
                }
            }
            for (int i = 0; i < M; ++i) {
                string = bufferedReader.readLine().split(" ");
                String type=string[0];
                int nextInt=Integer.parseInt(string[1]);
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
                    fileWriter.write(people.toString());
                }
            }
            fileWriter.flush();
            fileWriter.close();
            */
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
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
*/
