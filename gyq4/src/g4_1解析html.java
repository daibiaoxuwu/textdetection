import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {

    public static void main(String[] args) {
        String htmlString=new Scanner(System.in).nextLine();
        System.out.println(htmlString.replaceAll("<.+?>",""));
    }
}
