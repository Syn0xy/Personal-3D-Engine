package engine.geometric;

public class Transform {
    public final static int SCALE_X = 1;
    public final static int SCALE_Y = 1;
    public final static int SCALE_Z = 1;

    private Vector3 position;
    private Vector3 rotation;
    private Vector3 scale;

    public Transform(Vector3 position, Vector3 rotation, Vector3 scale){
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Transform(Vector3 position, Vector3 rotation){
        this(position, rotation, new Vector3(SCALE_X, SCALE_Y, SCALE_Z));
    }

    public Transform(Vector3 position){
        this(position, new Vector3());
    }

    public Transform(){
        this(new Vector3());
    }

    public Vector3 getPosition(){ return position; }
    public Vector3 getRotation(){ return rotation; }
    public Vector3 getScale(){ return scale; }

    public void setPosition(Vector3 position){ this.position = position; }
    public void setRotation(Vector3 rotation){ this.rotation = rotation; }
    public void setScale(Vector3 scale){ this.scale = scale; }

    public Transform copy(){
        return new Transform(position.copy(), rotation.copy(), scale.copy());
    }

    public String toString(){
        return getClass().getSimpleName() + "[position:" + position + ", rotation:" + rotation + ", scale:" + scale + "]";
    }
}