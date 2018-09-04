import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

class MyThread extends Thread{
    BlockingQueue<File> blockingQueue;
    ConcurrentHashMap<Character, Integer> HashMap;
    ConcurrentHashMap<Character, Integer> HashMap2 = new ConcurrentHashMap<>();
    final File poisonPill;


    public MyThread(BlockingQueue<File> blockingQueue, ConcurrentHashMap<Character, Integer> HashMap, File poisonPill) {
        this.blockingQueue = blockingQueue;
        this.HashMap = HashMap;
        this.poisonPill=poisonPill;
    }

    @Override
    public synchronized void run(){
        while(true){
            try {
                File file = blockingQueue.take();
                if(file == poisonPill) break;
//                System.out.println(file.getName());
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String string;

                while ((string = bufferedReader.readLine()) != null) {
                    for (int i = 0; i < string.length(); i++) {
                        int temp = HashMap2.getOrDefault(string.charAt(i), 0);
                        HashMap2.put(string.charAt(i), temp + 1);
                    }
                }

                for (char i = 'a'; i <= 'z'; i++) {
                    int temp = HashMap.getOrDefault(i, 0);
                    HashMap.put(i, temp + HashMap2.getOrDefault(i, 0));
                }
                bufferedReader.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}


public class Oj2 {
    public static void main(String[] args) {
        try {

            BlockingQueue<File> blockingQueue = new LinkedBlockingQueue<>();
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            int N = Integer.parseInt(scanner.nextLine());
            for (int i = 1; i <= N; i++) {
                File file = new File(s + i);
                blockingQueue.put(file);
            }
            File poisonPill = new File(s + (N + 1));
            for (int i = 0; i < 5; i++) {
                blockingQueue.put(poisonPill);
            }
            ConcurrentHashMap<Character, Integer> HashMap2 = new ConcurrentHashMap<>();

            Thread[] threads=new Thread[5];

            for (int i = 0; i < 5; i++) {
                threads[i] = new MyThread(blockingQueue,HashMap2,poisonPill);
            }
            for (int i = 0; i < 5; i++) {
                threads[i].start();
            }
            for (int i = 0; i < 5; i++) {
                threads[i].join();
            }

            int max = -1;
            for (char i = 'a'; i <= 'z'; i++) {
                int temp = HashMap2.getOrDefault(i, 0);
                max=Math.max(max,temp);
            }
            System.out.println(max);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}