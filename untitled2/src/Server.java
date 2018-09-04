import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private static BufferedReader bufferedReader;
    private static PrintStream printStream;
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static Scanner scanner;

    public static void main(String[] args){
        try {
            serverSocket = new ServerSocket(2000);
            socket = serverSocket.accept();
            //way 1
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printStream = new PrintStream(new BufferedOutputStream(socket.getOutputStream()),true);

            scanner = new Scanner(System.in);
            String string="";
            while(string!=null && !string.equals("bye")){
                string = scanner.nextLine();
                printStream.println(string);
                System.out.println("sent: " + string);
                string = bufferedReader.readLine();
                System.out.println("received: "+string);
                        //TODO:send objects
            }


//            PrintWriter printWriter=new PrintWriter(socket.getOutputStream(),true);
            printStream.close();
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
