;

import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int M = sc.nextInt();
            int N = sc.nextInt();

            int[] input = new int[M];
            int max = 0;
            for (int i = 0; i < M; i++) {
                input[i] = sc.nextInt();
                max = Math.max(input[i], max);
            }
//        System.out.println(max);
            List<Integer> outs = new ArrayList<>();
            for (int i = 2; i <= Math.sqrt(max) + 1; i++) {
                for (int j = 0; j < M; j++) {
//                System.out.println(input[j]+" "+(input[j] % i));
                    if (input[j] <= 1 || (input[j] % i == 0 && i != input[j])) input[j] = 0;
                }
            }
            for (int j = 0; j < M; j++) {
                if (input[j] != 0) outs.add(input[j]);
            }
            if (outs.size() < N) {
                System.out.println(-1);
            } else {
                Collections.sort(outs);
//            System.out.println(outs.toString());
                System.out.println(outs.get(N - 1));
            }


        }
    }
}
