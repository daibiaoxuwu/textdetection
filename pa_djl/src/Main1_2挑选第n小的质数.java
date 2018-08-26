import java.io.*;
import java.util.*;


public class  Main2 {

    public static ArrayList<String> searchFiles(String enterPath, String searchString) {
        File enterFile = new File(enterPath);
        File[] fileList = enterFile.listFiles();
   //     System.out.println(Arrays.toString(fileList));

        ArrayList<String> fileNames = new ArrayList<>();
        if(fileList==null) return fileNames;
        for (File file: fileList){
//            System.out.println("发现文件:"+file.getName().toLowerCase());
            if (file.isFile() && file.getName().toLowerCase().contains(searchString))
                fileNames.add(file.getName());
        //    else if(file.isDirectory())
          //      fileNames.addAll(searchFiles(file.toString(),searchString));
        }
//        if(fileNames.size()>0) System.out.println(fileNames.toString());
        return fileNames;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String searchString=sc.next().toLowerCase();
        ArrayList<String> fileNames=searchFiles("/usr/bin",searchString);
        Collections.sort(fileNames);
        for(String string : fileNames)
            System.out.println(string);
    }
}

