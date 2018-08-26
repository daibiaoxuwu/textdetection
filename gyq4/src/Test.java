public class Test {
    public static void main(String [] args){
        int[] a={1,2,3};
        int[] b=a;
        b[1]=1;
        for(int i:a){
            System.out.print(i+" ");
        }
    }
}
