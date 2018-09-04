import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleClient {

    private static BufferedReader bufferedReader;
    private static PrintStream printStream;
    private static Socket socket;
    private static Scanner scanner;
    private static int id;
    private static int status=0;
//    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args){
        try {
            scanner = new Scanner(System.in);


            id = Integer.parseInt(scanner.nextLine());
            socket = new Socket("127.0.0.1", 2000 + id);
            System.out.println("opened connection at id: " + id);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printStream = new PrintStream(new BufferedOutputStream(socket.getOutputStream()), true);
        }catch (Exception e){
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public synchronized void run() {

//                reentrantLock.lock();
                try {
                    while (true) {
                        while (status != 0) Thread.sleep(100);
                        System.out.println("input receiver: ");
                        String string=scanner.nextLine();
                        if(string.charAt(0)>='0' && string.charAt(0)<='9') {
                            int receiver = Integer.parseInt(string);
                            System.out.println("input message: ");
                            string = receiver + "@" + id + "@" + scanner.nextLine();
                            System.out.println("message sending...");
                            printStream.println(string);
                            System.out.println("message sent.");
                        }
                        status = 1;
                        Thread.sleep(100);
                        status = 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                reentrantLock.unlock();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public synchronized void run() {

                try{

                    String string=bufferedReader.readLine();
                    while(string!=null){
                        while (status == 0) Thread.sleep(100);
                        System.out.println("client received: " + string);
                        String[] strings = string.split("@");
                        if(strings[0].equals(Integer.toString(id))){
                            int sender = Integer.parseInt(strings[1]);
                            if(true){
                                System.out.println("Recived from contact "+strings[1]);
                                System.out.println("Text: "+strings[2]);
                            }
                        }
                        //TODO:send objects
                        status = 0;
                        Thread.sleep(100);
                        string=bufferedReader.readLine();
                    }

//            PrintWriter printWriter=new PrintWriter(socket.getOutputStream(),true);
                    printStream.close();
                    socket.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
