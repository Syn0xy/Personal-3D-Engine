package engine.graphics;

import java.awt.Polygon;

import engine.geometric.MutableNumber;
import engine.geometric.Transform;
import engine.geometric.Vector2Int;
import engine.geometric.Vector3;
import engine.graphics.component.Camera;
import engine.util.Mathf;

public class Triangle extends Polygon{
    private static final int SIDES_TRIANGLE = 3;
    private static final boolean VISIBLE = false;
    
    private Transform transform;
    private Vector3[] points;
    private Vector3 normal;
    private Material material;

    private boolean visible;
    private Vector3[] spacePoints;
    private Vector3 center;

    public Triangle(Transform transform, Vector3 point1, Vector3 point2, Vector3 point3, Vector3 normal, Material material){
        super(new int[SIDES_TRIANGLE], new int[SIDES_TRIANGLE], SIDES_TRIANGLE);
        this.transform = transform;
        this.points = new Vector3[SIDES_TRIANGLE];
        this.points[0] = point1;
        this.points[1] = point2;
        this.points[2] = point3;
        this.normal = normal;
        this.material = material;
        
        this.visible = VISIBLE;
        this.spacePoints = new Vector3[SIDES_TRIANGLE];
        this.center = new Vector3();
        init();
    }

    private void init(){
        for(int i = 0; i < spacePoints.length; i++){
            spacePoints[i] = new Vector3();
        }
    }

    public Transform getTransform(){ return transform; }
    public Vector3 getNormal(){ return normal; }

    public boolean isVisible(){ return visible; }
    public Vector3 getCenter(){ return center; }
    public Material getMaterial(){ return material; }

    public void reload(Camera c){
        for(int i = 0; i < spacePoints.length; i++){
            reloadPositionSpace(i);
            reloadLocationInScreen(c, i);
        }
        calculateVisibility(c);
        calculateCenter();
    }
    
    private void reloadPositionSpace(int index){
        Vector3 a = points[index].copy();
        a.multiply(transform.getScale());
        a.rotate(transform.getRotation());
        a.plus(transform.getPosition());
        spacePoints[index] = a;
    }

    private void reloadLocationInScreen(Camera c, int index){
        Vector3 v = spacePoints[index];
        Vector3 cp = c.getTransform().getPosition();
        Vector3 cr = c.getTransform().getRotation();
        
        MutableNumber<Double>
            px = new MutableNumber<>(v.x - cp.x),
            py = new MutableNumber<>(cp.y - v.y),
            pz = new MutableNumber<>(v.z - cp.z);
        
        Mathf.rotation(px, pz, - cr.y);
        Mathf.rotation(py, px, cr.z);
        Mathf.rotation(py, pz, cr.x);

        v.set(px.getValue(), py.getValue(), pz.getValue());

        double proportion = c.getScreenProportion() / v.z;
        xpoints[index] = (int)(v.x * proportion + c.getWidth() / 2);
        ypoints[index] = (int)(v.y * proportion + c.getHeight() / 2);
    }

    private boolean isValidLocation(Camera c, int index){
        return spacePoints[index].z >= 0 && xpoints[index] >= 0 && xpoints[index] <= c.getWidth() && ypoints[index] >= 0 && ypoints[index] <= c.getHeight();
    }

    private void calculateVisibility(Camera c){
        visible = isValidLocation(c, 0) || isValidLocation(c, 1) || isValidLocation(c, 2);
    }

    private void calculateCenter(){
        Vector3 v = new Vector3();
        for(int i = 0; i < spacePoints.length; i++){
            v.plus(spacePoints[i]);
        }
        v.divide(spacePoints.length);
        center.set(v);
    }

    public String toString(){
        return getClass().getSimpleName() + "[transform:" + transform + ", points:" + points.length + ", normal:" + normal + "]";
    }
}