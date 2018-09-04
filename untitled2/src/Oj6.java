
import java.util.*;
class Pair{
    int a;int b;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }
    public int getKey(){return a;}
    public int getValue(){return b;}
}

public class Oj6 {
    static LinkedList<LinkedList<Integer>> linkedLists = new LinkedList<>();
    static Map<Integer, Pair> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int N = scanner.nextInt();

        map.put(0, new Pair(0, 0));
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(0);
        linkedLists.add(linkedList);

        for (int i = 0; i < N; i++) {
            int parent = scanner.nextInt();
            int left = scanner.nextInt();
            int right = scanner.nextInt();

            Pair pair = new Pair(left, right);
            map.put(parent, pair);
        }

        int direction = 0;
        System.out.println(0);
        int[] nextBatch = new int[N];
        nextBatch[0] = 0;
        int nextBatchSize = 1;
        int[] newBatch = new int[N];
        int newBatchSize = 0;
        while (nextBatchSize > 0){
            for (int i = 0; i < nextBatchSize; i++) {
                int cursor = nextBatch[i];
                Pair pair = map.get(cursor);
                if(pair == null) continue;
                if(pair.getKey()!=-1)
                    newBatch[newBatchSize++] = pair.getKey();
                if(pair.getValue()!=-1)
                    newBatch[newBatchSize++] = pair.getValue();
            }
            if(direction == 1) {
                for (int i = 0; i < newBatchSize; i++) {
                    System.out.print(newBatch[i] + " ");
                }
            } else {
                for (int i = 0; i < newBatchSize; i++) {
                    System.out.print(newBatch[newBatchSize - i - 1] + " ");
                }
            }
            direction=1-direction;
            System.out.println();

            int[] arrayTemp = newBatch;
            newBatch = nextBatch;
            nextBatch = arrayTemp;
            nextBatchSize = newBatchSize;
            newBatchSize = 0;
        }
    }
}

