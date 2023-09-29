package engine.graphics;

import engine.geometric.MutableNumber;
import engine.geometric.Transform;
import engine.geometric.Vector2;
import engine.geometric.Vector3;
import engine.graphics.component.Camera;
import engine.util.Mathf;

public class Point {
    public final static boolean VISIBLE = false;

    private Vector3 position;
    
    private Vector3 positionSpace;
    private Vector2 location;
    private boolean visible;

    private MutableNumber<Double> positionX;
    private MutableNumber<Double> positionY;
    private MutableNumber<Double> positionZ;

    private Point(Vector3 position, Vector3 positionSpace, Vector2 location, boolean visible, MutableNumber<Double> positionX, MutableNumber<Double> positionY, MutableNumber<Double> positionZ){
        this.position = position;
        this.positionSpace = positionSpace;
        this.location = location;
        this.visible = visible;
        this.positionX = positionX;
        this.positionY = positionY;
        this.positionZ = positionZ;
    }

    public Point(Vector3 position){
        this(
            position,
            new Vector3(),
            new Vector2(),
            VISIBLE,
            new MutableNumber<>(),
            new MutableNumber<>(),
            new MutableNumber<>());
    }

    public Vector3 getPosition(){ return position; }
    public Vector3 getPositionSpace(){ return positionSpace; }
    public Vector2 getLocation(){ return location; }
    public boolean isVisible(){ return visible; }

    public void reload(Camera camera, Transform transform){
        reloadPositionSpace(transform);
        reloadLocation(camera);
        reloadVisibility();
    }

    public void reloadPositionSpace(Transform transform){
        Vector3 v = position.copy();
        v.multiply(transform.getScale());
        v.rotate(transform.getRotation());
        v.plus(transform.getPosition());
        positionSpace.set(v);
    }

    public void reloadLocation(Camera camera){
        Vector3 cp = camera.getTransform().getPosition();
        Vector3 cr = camera.getTransform().getRotation();
        
        positionX.setValue(positionSpace.getX() - cp.getX());
        positionY.setValue(cp.getY() - positionSpace.getY());
        positionZ.setValue(positionSpace.getZ() - cp.getZ());
        
        Mathf.rotation(positionX, positionZ, -cr.getY());
        Mathf.rotation(positionY, positionX, cr.getZ());
        Mathf.rotation(positionY, positionZ, cr.getX());

        double proportion = PaintScene.screenProportion / positionZ.getValue();

        location.set(
            positionX.getValue() * proportion + PaintScene.halfWindowWidth,
            positionY.getValue() * proportion + PaintScene.halfWindowHeight);
    }

    public void reloadVisibility(){
        visible = positionZ.getValue() >= 0 &&
            location.getX() >= 0 && location.getX() <= PaintScene.windowWidth &&
            location.getY() >= 0 && location.getY() <= PaintScene.windowHeight;
    }

    public String toString(){
        return getClass().getSimpleName() + "[location:" + location + "]";
    }
}
