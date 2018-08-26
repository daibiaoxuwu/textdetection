package com.company;
import java.util.*;

import java.lang.reflect.Array;
import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int M = sc.nextInt();
            int N = sc.nextInt();

            Integer numberBuffer[]=new Integer[M];
            for(int i=0;i<M;++i)
                numberBuffer[i]=sc.nextInt();

            List<Integer> numbers=Arrays.asList(numberBuffer);
//            int maxnum = (int) Math.sqrt(Collections.max(numbers));
            int maxnum = Collections.max(numbers)+1;

            boolean[] notPrime=new boolean[maxnum];
            for (int i=2; i<Math.sqrt(maxnum); i++)
            {
                if (!notPrime[i])
                    for (int j=i+i; j<maxnum; j+=i)
                        notPrime[j]=true;
            }
//            for (int i=2;i<maxnum;++i) if(!notPrime[i]) System.out.print(i+" ");
            notPrime[1]=true;
            Collections.sort(numbers);
//            System.out.println(numbers);
            for(int i=0;i<M;++i)
            {
                if(!notPrime[numbers.get(i)]) N--;
                if(N==0){
                    System.out.println(numbers.get(i));
                    break;
                }
            }
            if(N>0) System.out.println(-1);
        }
    }
}
