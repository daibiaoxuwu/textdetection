import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
class  Main3_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Integer> num1=new LinkedList<>();
        LinkedList<Integer> num2=new LinkedList<>();
        LinkedList<Integer> result=new LinkedList<>();

        String string1=scanner.nextLine();
        String string2=scanner.nextLine();
        if(string1.length()<string2.length()){
            String string3=string1;
            string1=string2;
            string2=string3;
        }

        for(String numberString : string1.split(" ")){
            int number=Integer.parseInt(numberString.trim());
            num1.addLast(number);
        }

        for(String numberString : string2.split(" ")){
            int number=Integer.parseInt(numberString.trim());
            num2.addLast(number);
        }

        int lastnum=0;
        while(num2.size()>0){
            lastnum+=num1.getLast();
            lastnum+=num2.getLast();
            num1.removeLast();
            num2.removeLast();
            result.addFirst(lastnum%10);
            lastnum/=10;
        }
        while(num1.size()>0){
            lastnum+=num1.getLast();
            num1.removeLast();
            result.addFirst(lastnum%10);
            lastnum/=10;
        }
        if(lastnum==1) result.addFirst(lastnum);

        System.out.println(result.toString().replace("[","").replace("]","").replace(",",""));
    }
}
/*
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

*/
