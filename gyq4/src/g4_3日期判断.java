import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main3 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String string=sc.nextLine();
        Pattern pattern=Pattern.compile("((199\\d)|(200\\d)|(201[0-8]))-((((0[13578])|(1[02]))-31)|(((0[13456789])|(1[0-2]))-((29)|(30)))|(((0[1-9])|(1[0-2]))-((0[1-9])|(1\\d)|(2[0-8]))))");

        Matcher matcher=pattern.matcher(string);

        boolean hasMatch=matcher.find();
        if(!hasMatch)
            System.out.println("NotMatch");
        while(hasMatch){
            System.out.println(matcher.group(0));

            string=string.substring(matcher.start()+1);
            if(string.length()==0) return;
            matcher.reset(string);
            hasMatch=matcher.find();
        }
    }
}
