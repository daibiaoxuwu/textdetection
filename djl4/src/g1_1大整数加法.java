import java.util.Scanner;
class Main1_1大整数加法{
    public static void main(String []args){
        Scanner in=new Scanner(System.in);
        while(in.hasNext()){

            String num1=in.next();
            String num2=in.next();

            int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
            String res = "";
            while (i >= 0 || j >= 0) {
                if (i >= 0)
                    carry += num1.charAt(i--) - '0';
                if (j >= 0)
                    carry += num2.charAt(j--) - '0';
                res = Integer.toString(carry % 10) + res;
                carry /= 10;
            }
            if(carry!=0){
                System.out.println("1"+res);
                continue;
            }
            System.out.println(res);
        }
    }
}
