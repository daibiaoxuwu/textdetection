package com.company;

import java.util.*;

public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][][] mem = new int[2][][];
        int[] M=new int[2];
        int[] N=new int[2];

        for (int scan = 0; scan < 2; ++scan) {
            M[scan] = sc.nextInt();
            N[scan] = sc.nextInt();

            mem[scan]=new int[M[scan]][N[scan]];
            for (int i=0;i<M[scan];++i) {
                for (int j=0;j<N[scan];++j) {
                    mem[scan][i][j]=sc.nextInt();
                }
            }
        }

        for (int i=0;i<M[0];++i) {
            for (int j2 = 0; j2 < N[1]; ++j2) {

                int sum = 0;
                for (int j = 0; j < N[0]; ++j) {
                    sum += mem[0][i][j] * mem[1][j][j2];
                }
                System.out.print(sum + " ");
            }
            System.out.println();
        }

    }
}


