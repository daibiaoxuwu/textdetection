import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Test3 {
    public static void main(String[] args){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL("http://news.qq.com/world_index.shtml").openStream()));
            String string;
            while ((string=bufferedReader.readLine())!=null)
                System.out.println(string);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
