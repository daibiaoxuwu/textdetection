import java.io.*;
        import java.net.ServerSocket;
        import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;


class MyReceiver implements Runnable{
    int id;
    private BufferedReader bufferedReader;
    private ServerSocket serverSocket;
    private Socket socket;
    private BlockingQueue<String> blockingQueue;

    public MyReceiver(int id, BlockingQueue<String> blockingQueue) {
        this.id = id;
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(2000 + id);
            socket = serverSocket.accept();
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            new Thread(new MySender(id,blockingQueue,socket)).start();

            while (true){
                blockingQueue.put(bufferedReader.readLine());
                //TODO:send objects
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
class MySender implements Runnable{
    int id;
    private Scanner scanner=new Scanner(System.in);
    private Socket socket;
    private BlockingQueue<String> blockingQueue;
    private HashSet<Integer> contactRequests=new HashSet<>();

    public MySender(int id, BlockingQueue<String> blockingQueue, Socket socket) {
        this.id = id;
        this.blockingQueue = blockingQueue;
        this.socket=socket;
    }

    @Override
    public void run() {
        try {
            PrintStream printStream = new PrintStream(new BufferedOutputStream(socket.getOutputStream()), true);

            while (true) {
                String string = blockingQueue.take();
                String[] strings = string.split("@");
                if (strings[0].equals(Integer.toString(id))) {
                    System.out.println("sent: "+string);
                    printStream.println(string);
                }
                else {
                    blockingQueue.put(string);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
public class MultiThreadServer {


    private static final int NumClients=5;
    public static void main(String[] args){
        BlockingQueue<String> blockingQueue=new LinkedBlockingQueue<>();
        for (int i = 0; i < NumClients; i++) {
            new Thread(new MyReceiver(i,blockingQueue)).start();
        }
    }
}

