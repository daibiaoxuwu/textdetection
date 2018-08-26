package com.company;
import java.util.*;

class Main1_4_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        char c = string.charAt(0);
        if (!(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == '_' || c == '$'))
            System.out.println(-1);
        else if(string.length()==1)
            System.out.println(0);
        else for (int i = 1; ; ++i) {
                c = string.charAt(i);
                if (!(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == '_' || c == '$' || c >= '0' && c <= '9')) {
                    System.out.println(-1);
                    break;
                }
                if (i == string.length() - 1) {
                    final String RESERVED_BUFFER = "abstract, assert, boolean, break, byte, case, catch, char, class, const, continue, default, do, double, else, enum, extends, final, finally, float, for, if, implements, import, instanceof, int, interface, long, native, new, package, private, protected, public, return, short, static, strictfp, super, switch, synchronized, this, throw, throws, transient, try, void, volatile, while,byValue, cast, false, future, generic, inner, operator, outer, rest, true, var, goto, const, null";
                    final String[] RESERVED = RESERVED_BUFFER.split(", ");
                    boolean flag=true;
                    for(String stringt : RESERVED){
                        if(stringt.equals(string)){
                            System.out.println(-1);
                            flag=false;
                            break;
                        }
                    }
                    if(flag) System.out.println(0);
                    break;
                }
            }
    }
}
