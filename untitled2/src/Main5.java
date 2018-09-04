import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main5 {
    public static void main(String[] args) throws IOException {
        String fileName="kuka.txt";
        BufferedWriter writer=new BufferedWriter(new FileWriter(fileName,true));
        writer.write("Hello Kuka:\n");
        writer.write("  My name is coolszy!\n");

        writer.write("  I like you and miss you。");

        writer.close();
    }
}
