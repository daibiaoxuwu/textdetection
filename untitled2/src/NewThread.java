import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class NewThread {
    private static BufferedReader bufferedReader;
    private static PrintStream printStream;
    private static Socket socket;
    private static Scanner scanner;
    private static HashSet<Integer> contactRequests;
    private static HashSet<Integer> contacts;
    private static int id;
    private static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
//    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {

        try {
            scanner = new Scanner(System.in);
            id = Integer.parseInt(scanner.nextLine());
            contacts = new HashSet<>();
            contactRequests = new HashSet<>();
            socket = new Socket("127.0.0.1", 2000 + id);
            System.out.println("opened connection at id: " + id);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printStream = new PrintStream(new BufferedOutputStream(socket.getOutputStream()), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        blockingQueue.put(bufferedReader.readLine());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            try {
                thread.join(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(blockingQueue.isEmpty()){

                        System.out.println("input receiver: ");
                        String string=scanner.nextLine();
                        if(string.charAt(0)>='0' && string.charAt(0)<='9') {
                            int receiver = Integer.parseInt(string);
                            if(!contacts.contains(receiver)){
                                contactRequests.add(receiver);
                            }
                            System.out.println("input message: ");
                            string = receiver + "@" + id + "@" + scanner.nextLine();
                            System.out.println("message sending...");
                            printStream.println(string);
                            System.out.println("message sent.");
                        }

            } else {

                try{
                    String string=blockingQueue.take();
                        System.out.println("client received: " + string);
                        String[] strings = string.split("@");
                        if(strings[0].equals(Integer.toString(id))){
                            int sender = Integer.parseInt(strings[1]);
                            if(contacts.contains(sender)){
                                System.out.println("Recived from contact "+strings[1]);
                                System.out.println("Text: "+strings[2]);
                            }
                            else{
                                System.out.println("Recived from stranger "+strings[1]);
                                if(strings[2].equals("_")){
                                    if(contactRequests.contains(sender)) {
                                        System.out.println(strings[1] + " has agreed to be your contact.");
                                        contacts.add(sender);
                                        contactRequests.remove(sender);
                                        printStream.println(strings[1]+"@"+ id +"@_@");
                                    }
                                    else{
                                        System.out.println(strings[1] + " has received your agreement.");
                                        contacts.add(sender);
                                    }
                                }
                                else{
//                            reentrantLock.lock();
                                    System.out.println("Text: "+strings[2]);
                                    System.out.println("Agree? Y/N");
                                    String s = scanner.nextLine();
                                    while (!s.equals("Y") && !s.equals("N")){
                                        System.out.println("Agree? Y/N");
                                        s = scanner.nextLine();
                                    }
                                    if(s.equals("Y")){
                                        printStream.println(strings[1]+"@"+ id +"@_@");
                                    }
//                            reentrantLock.unlock();
                                }
                            }
                        }
                        //TODO:send objects

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}

