package com.company;

import java.util.*;


class Main1_3_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        int[][] mat1 = new int[M][N];
        for (int i = 0; i < M; ++i)
            for (int j = 0; j < N; ++j)
                mat1[i][j] = sc.nextInt();

        N = sc.nextInt();
        int P = sc.nextInt();
        int[][] mat2 = new int[N][P];
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < P; ++j)
                mat2[i][j] = sc.nextInt();

        int[][] mat3 = new int[M][P];
        for (int i = 0; i < M; ++i)
            for (int j = 0; j < P; ++j)
                for (int r = 0; r < N; ++r)
                    mat3[i][j] += (mat1[i][r] * mat2[r][j]);

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < P - 1; ++j)
                System.out.print(mat3[i][j] + " ");
            System.out.println(mat3[i][P - 1]);
        }

    }
}
