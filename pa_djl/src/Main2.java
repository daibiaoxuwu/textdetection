import java.io.*;
        import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
        import java.util.Scanner;


public class  Main2 {

    public static ArrayList<String> searchFiles(String enterPath, String searchString) {
        File enterFile = new File(enterPath);
        File[] fileList = enterFile.listFiles();
        System.out.println(Arrays.toString(fileList));

        ArrayList<String> fileNames = new ArrayList<>();
        if(fileList==null) return fileNames;
        for (File file: fileList){
            if (file.isFile() && file.toString().toLowerCase().contains(searchString))
                fileNames.add(file.toString());
            else if(file.isDirectory())
                fileNames.addAll(searchFiles(file.toString(),searchString));
        }
        return fileNames;
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
  //      String searchString=sc.next().toLowerCase();
        String searchString="ad";
        for(String string : searchFiles("/usr/bin",searchString))
            System.out.println(string);
    }
}

