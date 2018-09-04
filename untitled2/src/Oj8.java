import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Oj8 {
    public static void main(String[] args){

        Scanner sc=new Scanner(System.in);

        String string=sc.nextLine();
        int N = sc.nextInt();
        int[] num=new int[string.length()];
        int numn=0;
        int max=1;
        for(char a='a';a<='z';++a){
            numn=0;
            for (int i = 0; i < string.length() ; i++) {
                if(string.charAt(i)==a){
                    num[numn++]=i;
                }
            }
            for (int i = 0; i < numn ; i++) {
                int sum=N;
                    for(int r = 0; r <= i; ++r){
                        for (int j = i - r; j < i; j++) {
                            sum -= (num[i] - num[j] - 1);
                            if (sum < 0) {
                                max = Math.max(max, i - j);
                                break;
                            }
                        }
                            for (int j = i + 1; j < numn; j++) {
                                sum -= (num[j] - num[i] - 1);
                                if(sum<0){
                                    max=Math.max(max,r+j-i + 1);
                                }
                            }
                    }
                }
            }
            System.out.println(max);
        }
    }
