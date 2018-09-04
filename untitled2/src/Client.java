import java.io.*;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.util.Scanner;

public class Client {

    private static BufferedReader bufferedReader;
    private static PrintStream printStream;
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static Scanner scanner;

    public static void main(String[] args){
        try {
            socket = new Socket("127.0.0.1",2000);
            //way 1
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printStream = new PrintStream(new BufferedOutputStream(socket.getOutputStream()),true);

            scanner = new Scanner(System.in);
            String string=bufferedReader.readLine();
            while(string!=null && !string.equals("bye")){
                System.out.println("client received: " + string);
                string = scanner.nextLine();
                printStream.println(string);
                System.out.println("client sent: " + string);
                string =bufferedReader.readLine();
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
