import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

public class Main22 {
    public static void main(String[] args) throws Exception{
        String encoding = "UTF-8";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("kuka.txt"));
        bufferedWriter.write("abc");
        bufferedWriter.close();

        Scanner sc=new Scanner(new BufferedReader(new FileReader("kuka.txt")));
        System.out.println(sc.nextLine());


    }
}
