package com.company;
import java.util.*;
public class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input=sc.nextLine();
        String first="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_$";
        String next="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_$0123456789";
        List<String> critic=new ArrayList<>();
        critic.add("abstract");
        critic.add("assert");
        critic.add("boolean");
        critic.add("break");
        critic.add("byte");
        critic.add("case");
        critic.add("catch");
        critic.add("char");
        critic.add("class");
        critic.add("const");
        critic.add("continue");
        critic.add("default");
        critic.add("do");
        critic.add("double");
        critic.add("else");
        critic.add("enum");
        critic.add("extends");
        critic.add("final");
        critic.add("finally");
        critic.add("float");
        critic.add("for");
        critic.add("goto");
        critic.add("if");
        critic.add("implements");
        critic.add("import");
        critic.add("instanceof");
        critic.add("int");
        critic.add("interface");
        critic.add("long");
        critic.add("native");
        critic.add("new");
        critic.add("package");
        critic.add("private");
        critic.add("protected");
        critic.add("public");
        critic.add("return");
        critic.add("short");
        critic.add("static");
        critic.add("strictfp");
        critic.add("super");
        critic.add("switch");
        critic.add("synchronized");
        critic.add("this");
        critic.add("throws");
        critic.add("transient");
        critic.add("try");
        critic.add("void");
        critic.add("volatile");
        critic.add("while");
        boolean flag=false;
        for (int i = 0; i < first.length(); i++) {
            if(input.charAt(0)==first.charAt(i)) flag=true;
        }
        if(!flag) System.out.println(-1); else{
            for (int i = 1; i < input.length(); i++) {
                flag=false;
                for (int j = 0; j < next.length(); j++) {
                    if (input.charAt(i) == next.charAt(j)) flag = true;
                }
                if(!flag){
                    System.out.println(-1);
                    break;
                }
            }
            if(flag){
                for(String t:critic){
                    if(input.equals(t)) flag=false;
                }
                if(flag)System.out.println(0); else System.out.println(-1);
            }
        }
    }
}
