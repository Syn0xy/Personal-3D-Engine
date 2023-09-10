package engine.graphics;

import engine.util.Mathf;

public class Color {
    public final static Color WHITE = new Color(1, 1, 1);
    public final static Color BLACK = new Color(0, 0, 0);
    public final static Color RED = new Color(1, 0, 0);
    public final static Color GREEN = new Color(0, 1, 0);
    public final static Color BLUE = new Color(0, 0, 1);
    public final static double HUE = 0;
    public final static double SATURATION = 1;
    public final static double LUMINOSITY = 0;

    private double red;
    private double green;
    private double blue;
    private double hue;
    private double saturation;
    private double luminosity;

    private Color(double red, double green, double blue, double hue, double saturation, double luminosity){
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.hue = hue;
        this.saturation = saturation;
        this.luminosity = luminosity;
    }

    public Color(double red, double green, double blue, double hue, double saturation){
        this(red, green, blue, hue, saturation, LUMINOSITY);
    }

    public Color(double red, double green, double blue, double hue){
        this(red, green, blue, hue, SATURATION);
    }

    public Color(double red, double green, double blue){
        this(red, green, blue, HUE);
    }

    public Color(java.awt.Color color){
        this(color.getRed(), color.getGreen(), color.getBlue());
    }

    public Color(){
        this(0, 0, 0);
    }

    public static Color random(){
        return new Color(
            Mathf.random(),
            Mathf.random(),
            Mathf.random(),
            Mathf.random(),
            Mathf.random(),
            Mathf.random());
    }

    public java.awt.Color toColorAWT(){
        return new java.awt.Color((float)red, (float)green, (float)blue);
    }

    public String toString(){
        return getClass().getSimpleName() + "[red:" + red + ", green:" + green + ", blue:" + blue + ", hue:" + hue + ", saturation:" + saturation + ", luminosity:" + luminosity + "]";
    }
}