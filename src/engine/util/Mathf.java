package engine.util;

import engine.geometric.MutableNumber;

public class Mathf {
    public static double cosInDegrees(double a){ return cosInRadian(deg2rad(a)); }
    public static double cosInRadian(double a){ return StrictMath.cos(a); }
    public static double sinInDegrees(double a){ return sinInRadian(deg2rad(a)); }
    public static double sinInRadian(double a){ return StrictMath.sin(a); }
    public static double tanInDegrees(double a){ return tanInRadian(deg2rad(a)); }
    public static double tanInRadian(double a){ return StrictMath.tan(a); }

    public static double atan2ToDegrees(double y, double x){ return rad2deg(atan2ToRadian(y, x)); }
    public static double atan2ToRadian(double y, double x){ return StrictMath.atan2(y, x); }

    public static double deg2rad(double a){ return Math.toRadians(a); }
    public static double rad2deg(double a){ return Math.toDegrees(a); }

    public static double hypotenuse(double a, double b){ return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2)); }

    public static double distance(double a, double b){ return abs(a - b); }
    public static int distance(int a, int b){ return abs(a - b); }
    public static double abs(double a){ return Math.abs(a); }
    public static int abs(int a){ return Math.abs(a); }
    
    public static boolean between(double max, double min, double value){ return value >= min && value <= max || value >= max && value <= min; }
    public static double max(double a1, double a2){ if(a1 > a2) return a1; else return a2; }
    public static double min(double a1, double a2){ if(a1 < a2) return a1; else return a2; }

    public static double random(){ return Math.random(); }
    public static double random(double max){ return random(0, max); }
    public static double random(double min, double max){ return min + Math.random() * (max - min); }
    public static int random(int max){ return random(0, max); }
    public static int random(int min, int max){ return min + (int)(Math.random() * (max - min + 1)); }

    public static <T extends Comparable<T>> T clamp(T val, T min, T max){
        if (val.compareTo(min) < 0) return min;
        else if (val.compareTo(max) > 0) return max;
        else return val;
    }

    public static double lerp(double a, double b, double v){
        if(v == 0) return a;
        else if(v == 1) return b;
        else return a + (b - a) * v;
    }
    
    public static void rotation(MutableNumber<Double> a, MutableNumber<Double> b, double rotation){
        double rotRadian = atan2ToRadian(a.getValue(), b.getValue()) + deg2rad(rotation);
        double distance = hypotenuse(a.getValue(), b.getValue());

        a.setValue(distance * sinInRadian(rotRadian));
        b.setValue(distance * cosInRadian(rotRadian));
    }
}
