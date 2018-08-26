package com.company;

import java.util.Scanner;

class Main1_1_new {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str1 = sc.next();
            String str2 = sc.next();

            int tot=Math.abs(str2.length()-str1.length());
            char[] strbuff=new char[tot];

            for(int i=0;i<tot;++i)
                strbuff[i]='0';

            if(str1.length()>str2.length())
                str2=new String(strbuff).concat(str2);
            else
                str1=new String(strbuff).concat(str1);

            int[] result=new int[5005];
            int add=0;

            for(int i=0;i<str1.length();++i) {
                int sum=(str1.charAt(str1.length()-i-1)-48)+(str2.charAt(str2.length()-i-1))-48+add;
                if(sum>9){
                    sum-=10;
                    add=1;
                }
                else add=0;
                result[5004-i]=sum;
                //  System.out.println(sum+" "+(5004-i));
            }
            result[5004-str1.length()]=add;
            int i;
            for(i=0;i<5005;++i)
                if(result[i]!=0)break;

            if(i==5005) i=5004;
            for(;i<5005;++i)
                System.out.print(result[i]);
            System.out.println();
        }
    }
}
