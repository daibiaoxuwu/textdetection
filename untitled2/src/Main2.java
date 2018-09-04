import java.io.*;

public class Main2 {
    public static void main(String[] args){
        String encoding = "UTF-8";
        File file = new File("kuka.txt");
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(new String(filecontent, encoding));
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
        }

    }
}
