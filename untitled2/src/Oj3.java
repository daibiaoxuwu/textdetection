import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Oj3 {

    public static void main(String[] args) {
        Pattern pattern  = Pattern.compile("^[+-]?\\d+(.\\d+)?([eE]\\d+)?$");
        Matcher matcher = pattern.matcher(new Scanner(System.in).nextLine());
        if(matcher.find()){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }
}
