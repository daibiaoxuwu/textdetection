import java.io.*;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Date;

class Main {
    public static void main(String args[]) throws IOException {
        File path=new File(".");
        File[] files=path.listFiles();
        for(File file:files){
            if(file.isFile()){
//                new BufferedReader(new FileReader(file)).r;
            }
        }

    }

}
