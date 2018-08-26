import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String string=sc.nextLine();

        Pattern pattern=Pattern.compile("(.*?)(<.+?>)(.*)");
        Matcher matcher=pattern.matcher(string);
        String outString="";
        while(matcher.find()){
            outString+=matcher.group(1);
            string=matcher.group(3);
//            System.out.println(string+"\n:"+matcher.group(0)+"\n:"+matcher.group(1)+"\n:"+matcher.group(3));
//            sc.nextLine();
            matcher=pattern.matcher(string);
        }
        System.out.println(outString+string);
    }
}
