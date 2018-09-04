

import java.util.Comparator;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class Pair2{
    int a;int b;

    public Pair2(int a, int b) {
        this.a = a;
        this.b = b;
    }
    public int getKey(){return a;}
    public int getValue(){return b;}
}

public class Oj7 {
    public static void main(String[] args) {
        ArrayList<Pair2> arrayList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        final int N = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < N; i++) {
            arrayList.add(new Pair2(scanner.nextInt(), scanner.nextInt()));
        }
        arrayList.sort(new Comparator<Pair2>() {
            @Override
            public int compare(Pair2 o1, Pair2 o2) {
                if (o1.getKey() > o2.getKey()) return 1;
                if (o1.getKey() < o2.getKey()) return -1;
                return 0;
            }
        });
        for (int i = 0; i < N; i++) {
            boolean flag=true;
            for (int j = i +1; j < N; j++) {
                if(arrayList.get(i).getValue()<arrayList.get(j).getValue()) {
                    flag=false;
                    break;
                }
            }
            if(flag) System.out.println(arrayList.get(i).getKey()+" "+arrayList.get(i).getValue());
        }

    }
}

