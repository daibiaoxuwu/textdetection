import java.util.ArrayList;
        import java.util.Scanner;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

public class Main3 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        StringBuffer stringBuffer=new StringBuffer(sc.nextLine());
//        StringBuffer stringBuffer=new StringBuffer("2001-04-2017-04-26等都是值得中国人永远铭记的日子。");

        Pattern pattern=Pattern.compile("(((199)|(200))\\d|(201[0-8]))-((((0([13578]))|(10|2))-((0[1-9])|(1\\d)|(2\\d)|(30|1)))|(((0([469]))|11)-((0[1-9])|(1\\d)|(2\\d)|(30)))|(02-((0[1-9])|(1\\d)|(2\\[0-8]))))");
        Matcher matcher=pattern.matcher(stringBuffer);

        boolean flag=false;
        ArrayList<String> strings=new ArrayList<>();
        while(matcher.find()){
            flag=true;
            strings.add(matcher.group(0));
            System.out.println(matcher.group(0));
            stringBuffer.setCharAt(matcher.start(),'x');
            matcher.reset();
        }
        if(!flag)
            System.out.println("NotMatch");
//        strings.sort(String::compareTo);
//        for (String string : strings) {
//            System.out.println(string);
//        }
    }
}
