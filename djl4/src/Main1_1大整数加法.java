package com.company;

import java.util.Scanner;

class  Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String string=sc.nextLine();
            int stringPointer=string.length()-1;

            final int SUM_SIZE=5002;
            int[] sum=new int[SUM_SIZE];
            int sumPointer=SUM_SIZE-1;
            for(;stringPointer>=0;--stringPointer,--sumPointer){
                char digit=string.charAt(stringPointer);
                if(digit==' '){
                    sumPointer=SUM_SIZE;
                    continue;
                }
                sum[sumPointer]+=(digit-48);
                if(sum[sumPointer]>9){
                    sum[sumPointer]-=10;
                    ++sum[sumPointer-1];
                }
            }
            if(sum[sumPointer]==1) System.out.print(1);
            ++sumPointer;
            for(;sumPointer<SUM_SIZE;++sumPointer) {
                System.out.print(sum[sumPointer]);
            }
            System.out.println();
        }
    }

}
