import java.util.ArrayList;
import java.util.Date;

public class Test2 {
    static void printArray(ArrayList c){
        for(Object o:c)
            System.out.println(o);
    }

    public static void main(String[] args){

        Byte x=127;

        Date date[] = {new Date(),new Date(),new Date()};
        byte ascii1[] = {91,92,93};
        byte ascii2[] = {91,92,93};
        String s=new String(ascii1);
        System.out.println(s);

        ArrayList<String> strings=new ArrayList<>();
        strings.add("");
        strings.add("1");
        strings.add("1");
        strings.add("1");
        printArray(strings);
    }
}
