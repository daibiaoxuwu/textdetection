import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class BlockingQueueTest {
    public static void main(String[] args){
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingDeque<>(10);
        for(int i=0;i<5;++i){
            new NumbersProducer(Integer.MAX_VALUE,1,blockingQueue,i).start();
            new NumbersProducer(Integer.MAX_VALUE,0,blockingQueue,i).start();
            new NumbersConsumer(Integer.MAX_VALUE,1,blockingQueue,i).start();
        }
    }
}
class NumbersConsumer extends Thread{
    private final int poisonPill;
    private final int poisonPillNumber;
    private BlockingQueue<Integer> blockingQueue;
    private final int threadNumber;

    public NumbersConsumer(int poisonPill, int poisonPillNumber, BlockingQueue<Integer> blockingQueue, int threadNumber) {
        this.poisonPill = poisonPill;
        this.poisonPillNumber = poisonPillNumber;
        this.blockingQueue = blockingQueue;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run(){
        try{
            int takeNumber = blockingQueue.take();
            while (takeNumber != poisonPill) {
                System.out.println("Thread " + threadNumber + " taken " + takeNumber);
                takeNumber = blockingQueue.take();
            }
            System.out.println("Thread "+threadNumber+" Poisoned!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

class NumbersProducer extends Thread{
    private final int poisonPill;
    private final int poisonPillNumber;
    private BlockingQueue<Integer> blockingQueue;
    private final int threadNumber;

    public NumbersProducer(int poisonPill, int poisonPillNumber, BlockingQueue<Integer> blockingQueue, int threadNumber) {
        this.poisonPill = poisonPill;
        this.poisonPillNumber = poisonPillNumber;
        this.blockingQueue = blockingQueue;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run(){
        try{
            for (int i = 0; i < 100; i++) {
                blockingQueue.put(i+1000*threadNumber);
            }
            for (int i = 0; i < poisonPillNumber; i++) {
                blockingQueue.put(poisonPill);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}