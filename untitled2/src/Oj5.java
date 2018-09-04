
import java.util.Scanner;
public class Oj5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int M = sc.nextInt();
        final int N = sc.nextInt();

        int[][] poi = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                poi[i][j] = sc.nextInt();
            }
        }
        int maxsize = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (poi[i][j] == 1) {
                    poi[i][j] = 2;
                    int size = 1;
                    int[][] nextBatch = new int[M * N][2];
                    nextBatch[0][0] = i;
                    nextBatch[0][1] = j;
                    int nextBatchSize = 1;
                    int[][] newBatch = new int[M * N][2];
                    int newBatchSize = 0;
                    while (size < M * N && nextBatchSize > 0) {
                        for (int cursor = 0; cursor < nextBatchSize; ++cursor) {
                            int ti = nextBatch[cursor][0];
                            int tj = nextBatch[cursor][1];

                            for (int a = -1; a <= 1; a++) {
                                for (int b = -1; b <= 1; b++) {
                                    if (a + b == 1 || a + b == -1) {
                                        int ni = a + ti;
                                        int nj = b + tj;
                                        if (ni >= M || ni < 0 || nj >= N || nj < 0) continue;
                                        if (poi[ni][nj] == 1) {
                                            poi[ni][nj] = 2;
                                            newBatch[newBatchSize][0] = ni;
                                            newBatch[newBatchSize][1] = nj;
                                            newBatchSize++;
                                            size++;
                                            maxsize = Math.max(maxsize, size);
                                        }
                                    }
                                }
                            }
                        }
                        int[][] arrayTemp = newBatch;
                        newBatch = nextBatch;
                        nextBatch = arrayTemp;
                        nextBatchSize = newBatchSize;
                        newBatchSize = 0;
                    }

                    for (int i2 = 0; i2 < M; i2++) {
                        for (int j2 = 0; j2 < N; j2++) {
//                            System.out.print(poi[i2][j2] + " ");
                            if(poi[i2][j2]==2)
                                poi[i2][j2]=0;
                        }
//                        System.out.println();
                    }
//                    System.out.println(size);
                }
            }
        }
        System.out.println(maxsize);
    }
}


