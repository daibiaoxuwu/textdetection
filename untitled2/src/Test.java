import java.util.ArrayList;
import java.util.List;

public class Test{
    public static void main(String[] args){
        List<String> list=new ArrayList<>();
        list.add("王利虎");
        list.add("张三");
        list.add("李四");
        int size=list.size();
        String[] array = list.toArray(new String[size]);
        for(int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }
    }
}
