package engine.component.light;

import java.util.ArrayList;
import java.util.List;

import engine.component.Component;
import engine.graphics.Color;

public class Light extends Component{
    public final static List<Light> LIGHTS = new ArrayList<>();
    public final static double RANGE = 10;
    public final static double INTENSITY = 1;

    private LightType type;
    private double range;
    private double intensity;
    private Color color;

    public Light(LightType type, double range, double intensity, Color color){
        this.type = type;
        this.range = range;
        this.intensity = intensity;
        this.color = color;
    }

    public Light(LightType type, double range, double intensity){
        this(type, range, intensity, new Color());
    }

    public Light(LightType type, double range){
        this(type, range, INTENSITY);
    }

    public Light(LightType type){
        this(type, RANGE);
    }

    public Light(){
        this(LightType.POINT);
    }

    public LightType getType(){ return type; }
    public double getRange(){ return range; }
    public double getIntensity(){ return intensity; }
    public Color getColor(){ return color; }

    public String toString(){
        return getClass().getSimpleName() + "[type:" + type + ", range:" + range + ", intensity:" + intensity + ", color:" + color + "]";
    }
}