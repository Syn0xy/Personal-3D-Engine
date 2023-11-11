package engine.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import engine.geometric.Vector3;

public class DebugLine {
    public final static List<DebugLine> LINES = new ArrayList<>();
    public final static Color COLOR = Color.RED;

    private Vector3 start;
    private Vector3 end;
    private Color color;

    private DebugLine(Vector3 start, Vector3 end, Color color){
        this.start = start;
        this.end = end;
        this.color = color;
    }

    private DebugLine(Vector3 start, Vector3 end){
        this(start, end, COLOR);
    }

    public Vector3 getStart(){
        return start;
    }

    public Vector3 getEnd(){
        return end;
    }

    public Color getColor(){
        return color;
    }

    private static void drawLine(DebugLine line){
        LINES.add(line);
    }

    public static void drawLine(Vector3 start, Vector3 end, Color color){
        drawLine(new DebugLine(start, end, color));
    }

    public static void drawLine(Vector3 start, Vector3 end){
        drawLine(new DebugLine(start, end));
    }

    public String toString(){
        return getClass().getSimpleName() + "[start:" + start + ", end:" + end + ", color:" + color + "]";
    }
}