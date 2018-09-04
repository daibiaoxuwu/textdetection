import java.io.*;


public class Serilize {

    public static void main(String[] args) {
        try {
            Student stu = new Student(1, "s", 1, "d");
            File file=new File("k2.txt");
            if(!file.exists())
                if(!file.createNewFile())
                    System.out.println("file cannot be created");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(stu);
            objectOutputStream.close();

            FileInputStream fileInputStream = new FileInputStream("k2.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Student stu2 = (Student) objectInputStream.readObject();
            objectInputStream.close();

            System.out.println(stu2.name);
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}


