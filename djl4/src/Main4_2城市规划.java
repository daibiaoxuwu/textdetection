import java.util.ArrayList;
import java.util.Scanner;

class Main2 {
    private static int N;
    private static boolean[] arrive;
    private static int arriveNum;
    private static ArrayList<ArrayList<Integer>> paths = new ArrayList<>();

    private static int stackTop;
    private static int[] stack;

    private static boolean DFS() {
        while (true) {
            if(stackTop==0) return false;

            int pos=stack[--stackTop];
            for (int nextPos : paths.get(pos)) {
                if (!arrive[nextPos]) {
                    arrive[nextPos] = true;
                    arriveNum++;
                    //System.out.println(arriveNum+" "+pos+" "+nextPos);
                    if (arriveNum == N) return true;

                    stack[stackTop++]=nextPos;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
//        N=100000;

        for (int i = 0; i < N; i++) {
            paths.add(new ArrayList<>());
        }

        //input
        for (int i = 1; i < N; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
//            int a=i-1;
//            int b=i;

            paths.get(a).add(b);
            paths.get(b).add(a);
        }

        //arrived points
        arrive = new boolean[N];
        arrive[0] = true;
        arriveNum = 1;

        //DFS
        stack=new int[N];
        stack[0]=0;
        stackTop=1;
        if(DFS())
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}


