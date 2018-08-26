import java.util.ArrayList;
import java.util.Scanner;

class Main2True {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();

        int[][] poiNum = new int[N * 2][3];
        int[] linkTail=new int[N + 1];

        for (int i = 1; i < N; i++) {
            poiNum[i][0] = sc.nextInt();
            poiNum[i][1] = sc.nextInt();
     //       if(linkTail[poiNum[i][0]]!=0) poiNum[i][2]=linkTail[poiNum[i][0]];
            poiNum[i][2]=linkTail[poiNum[i][0]];
            linkTail[poiNum[i][0]]=i;
//            System.out.println(linkTail[poiNum[i][0]]+" " + i);

            int j=i + N - 1;
            poiNum[j][1] = poiNum[i][0];
            poiNum[j][0] = poiNum[i][1];
      //      if(linkTail[poiNum[j][0]]!=0) poiNum[j][2]=linkTail[poiNum[j][0]];
            poiNum[j][2]=linkTail[poiNum[j][0]];
            linkTail[poiNum[j][0]]=j;

        }
//        for (int k = 1; k < N + 1; k++) { System.out.println("linkTail " + k + " " + linkTail[k]); }

        int[] reach=new int[N + 1];
        reach[1]=1;
        int reachNum = 1;
        int[] nextBatch=new int[N];
        nextBatch[0]=1;
        int nextBatchSize=1;
        int[] newBatch=new int[N];
        int newBatchSize=0;

        while(reachNum < N) {
            if(nextBatchSize==0){
                System.out.println("NO");
                return;
            }
//            for (int k = 0; k < N; k++) { System.out.print(nextBatch[k]+" "); }System.out.println();
            for (int i = 0; i < nextBatchSize; i++) {
                int batch = nextBatch[i];
                int linkCursor=linkTail[batch];
//                System.out.println("batch"+batch + "link"+linkCursor);
                while (linkCursor!=0){
//                    if(poiNum[linkCursor][0]!=batch) {System.out.println("error" + linkCursor + poiNum[linkCursor][0]);}
                    int newPoint=poiNum[linkCursor][1];
                    if(reach[newPoint]==0){
                        newBatch[newBatchSize]=newPoint;
                        newBatchSize++;
                        reachNum++;
                        reach[newPoint]=1;
                    }
                    linkCursor=poiNum[linkCursor][2];
                }
            }
            int[] arrayTemp=newBatch;
            newBatch=nextBatch;
            nextBatch=arrayTemp;
            nextBatchSize=newBatchSize;
            newBatchSize=0;
        }
        System.out.println("YES");
    }
}



