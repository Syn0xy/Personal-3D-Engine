package engine.util;

import java.util.Collection;

public class Debug {
    public static <T> void println(String name, Collection<? extends T> list){
        if(list == null) return;
        println(name, list.toArray());
    }
    public static <T> void println(String name, T[] list){
        if(list == null) return;
        println("[" + list.length + "] - " + name + " :");
        for(int i = 0; i < list.length; i++) println("\t" + (i + 1) + " - " + list[i]);
        println();
    }

    public static void println(){ println(""); }
    public static void println(String s){ print(s + "\n"); }
    public static void print(){ print(""); }
    public static void print(String s){ System.out.print(s); }

}