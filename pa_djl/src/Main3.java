import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
//TODO:q
class  Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<LinkedList<Integer>> bigNums = new ArrayList<>();
        for (int i = 0; i < 3; ++i) bigNums.add(new LinkedList<>());

        for (int iter = 0; iter < 2; ++iter) {
            String[] strings = sc.nextLine().split(" ");
            for (String string : strings) {
                bigNums.get(iter).add(Integer.parseInt(string));
            }
        }
        int size1 = bigNums.get(0).size();
        int size2 = bigNums.get(1).size();
        for (int i = 0; i < Math.abs(size1 - size2); ++i)
            if (size1 > size2) bigNums.get(1).addFirst(0);
            else bigNums.get(0).addFirst(0);

        int addup = 0;
        while (bigNums.get(0).size() > 0) {
            int sum = addup;
            sum += bigNums.get(0).removeLast();
            sum += bigNums.get(1).removeLast();

            if (sum > 9) {
                sum -= 10;
                addup = 1;
            } else addup = 0;

            bigNums.get(2).addFirst(sum);
        }
        if (addup == 1) bigNums.get(2).addFirst(1);
//        for (int i : bigNums.get(2))
//            System.out.print(i+" ");
        while(bigNums.get(2).size()>1)
            System.out.print(bigNums.get(2).removeFirst()+" ");
        System.out.println(bigNums.get(2).removeFirst());
    }
}

