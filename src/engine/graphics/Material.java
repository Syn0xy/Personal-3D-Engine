package engine.graphics;

import java.awt.Color;

public class Material {
    private Texture texture;
    private Color color;

    public Material(Texture texture, Color color){
        this.texture = texture;
        this.color = color;
    }

    public Material(Texture texture){
        this(texture, Colorf.random());
    }

    public Material(){
        this(new Texture());
    }

    public Texture getTexture(){ return texture; }
    public Color getColor(){ return color; }

    public String toString(){
        return getClass().getSimpleName() + "[texture:" + texture + ", color:" + color + "]";
    }
}
