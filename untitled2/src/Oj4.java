import java.util.Scanner;

public class Oj4 {

    private static long n;
    private static long k;

    static int work(String string, int r){
        if(string.length()==n){
            if(r == k){
                System.out.println(string);
                System.exit(0);
            }
//            System.out.println(string + " " + r);
            return ++r;
        }
        for (int i = 1; i <= n; i++) {
            if(!string.contains(Integer.toString(i))){
                r = work(string+i,r);
            }
        }
        return r;
    }
    
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();
        work("",1);
    }
}
