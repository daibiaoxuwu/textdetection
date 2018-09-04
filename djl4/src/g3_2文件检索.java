import java.io.*;
import java.util.*;


class G3_2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        s = s.toLowerCase();

        File f = new File("/usr/bin");
        File[] files = f.listFiles();

        Queue<String> priorityQueue=new PriorityQueue<>();

        if(files!=null) for (int i = 0; i < files.length; ++i) {
            String fileName = files[i].getName();
            fileName = fileName.toLowerCase();
            if (fileName.contains(s) && files[i].isFile())
//                System.out.println(files[i].getName());
                priorityQueue.add(files[i].getName());
        }
        while(priorityQueue.size()>0)
            System.out.println(priorityQueue.remove());
    }
}