package engine.util;

import java.awt.Color;
import java.util.Collection;

import engine.geometric.Vector3;

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

    public static void drawLine(Vector3 a, Vector3 b, Color c){
        DebugLine.drawLine(a, b, c);
    }
}