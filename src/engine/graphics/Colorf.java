package engine.graphics;

import java.awt.Color;

import engine.util.Mathf;

public class Colorf {
    public final static int COLOR_MAX = 255;

    public static Color inverse(Color c){
        return new Color(COLOR_MAX - c.getRed(), COLOR_MAX - c.getGreen(), COLOR_MAX - c.getBlue());
    }

    public static Color random(){
        return new Color(randomColor(), randomColor(), randomColor());
    }

    public static int randomColor(){
        return Mathf.random(COLOR_MAX);
    }
}
