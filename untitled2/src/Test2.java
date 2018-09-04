public class Test2 {
    public static void main(String[] args){
        String a="1@2@@3";
        String[] b=a.split("@");
        for(String c:b) System.out.println(c);
    }
}
