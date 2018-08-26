import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main4{
    public static void main(String[]args) throws Exception{
        Scanner sc=new Scanner(System.in);
        String inputString=sc.nextLine();
//        String inputString="114";

        URL cs=new URL("http://47.104.89.193/");
        BufferedReader in = new BufferedReader(new InputStreamReader(cs.openStream()));
        String inputLine;
        ArrayList<String> strings=new ArrayList<>();
        Pattern pattern=Pattern.compile("[0-9A-Za-z.]+\\.com");
        while((inputLine=in.readLine())!=null){
            Matcher matcher=pattern.matcher(inputLine);
            while (matcher.find()){
                String findString=matcher.group().toLowerCase();
                if(findString.contains(inputString)){
                    strings.add(findString);
                }
            }
        }

        strings.sort(String::compareTo);

        String oldString="x";
        for(String string:strings){
            if(string.equals(oldString)) continue;
            oldString=string;
            System.out.println(string);
        }
        in.close();
    }
}
