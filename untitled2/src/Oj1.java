import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Oj1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int M = sc.nextInt();
        final int N = sc.nextInt();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(sc.nextInt(), max);
            }
        }
        System.out.println(max);
    }
}

