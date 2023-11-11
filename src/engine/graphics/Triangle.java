package engine.graphics;

import java.awt.Polygon;

import engine.geometric.MutableNumber;
import engine.geometric.Transform;
import engine.geometric.Vector2Int;
import engine.geometric.Vector3;
import engine.graphics.component.Camera;
import engine.util.Mathf;
import engine.view.GameView;

public class Triangle extends Polygon{
    private static final int SIDES_TRIANGLE = 3;
    public final static boolean VISIBLE = true;
    
    private Transform transform;
    private Vector3 point1;
    private Vector3 point2;
    private Vector3 point3;
    private Vector3 normal;
    private Material material;

    private boolean visible;
    private Vector3 pointSpace1;
    private Vector3 pointSpace2;
    private Vector3 pointSpace3;
    private Vector3 center;

    public Triangle(Transform transform, Vector3 point1, Vector3 point2, Vector3 point3, Vector3 normal, Material material){
        super(new int[SIDES_TRIANGLE], new int[SIDES_TRIANGLE], SIDES_TRIANGLE);
        this.transform = transform;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.normal = normal;
        this.material = material;
        
        this.visible = VISIBLE;
        this.pointSpace1 = new Vector3();
        this.pointSpace2 = new Vector3();
        this.pointSpace3 = new Vector3();
        this.center = new Vector3();
    }

    public Transform getTransform(){ return transform; }
    public Vector3 getNormal(){ return normal; }

    public boolean isVisible(){ return visible; }
    public Vector3 getCenter(){ return center; }
    public Material getMaterial(){ return material; }

    public void reload(Camera camera){
        reloadPositionSpace();
        Vector2Int v1 = reloadLocationInScreen(pointSpace1, camera);
        Vector2Int v2 = reloadLocationInScreen(pointSpace2, camera);
        Vector2Int v3 = reloadLocationInScreen(pointSpace3, camera);
        xpoints[0] = v1.x; ypoints[0] = v1.y;
        xpoints[1] = v2.x; ypoints[1] = v2.y;
        xpoints[2] = v3.x; ypoints[2] = v3.y;
        
        visible = isValidLocation(pointSpace1, v1) || isValidLocation(pointSpace2, v2) || isValidLocation(pointSpace3, v3);
        calculateCenter();
    }

    public void reloadPositionSpace(){
        pointSpace1 = reloadPositionSpace(point1);
        pointSpace2 = reloadPositionSpace(point2);
        pointSpace3 = reloadPositionSpace(point3);
    }
    
    public Vector3 reloadPositionSpace(Vector3 v){
        Vector3 a = v.copy();
        a.multiply(transform.getScale());
        a.rotate(transform.getRotation());
        a.plus(transform.getPosition());
        return a;
    }

    public Vector2Int reloadLocationInScreen(Vector3 v, Camera c){
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
        return Vector2Int.valueOf(v.x * proportion + c.getHalfWindowWidth(), v.y * proportion + c.getHalfWindowwHeight());
    }

    public boolean isValidLocation(Vector3 p, Vector2Int l){
        return p.z >= 0 /* && l.x >= 0 && l.x <= windowWidth && l.y >= 0 && l.y <= windowHeight */;
    }

    public void calculateCenter(){
        Vector3 v = new Vector3();
        v.plus(pointSpace1, pointSpace2, pointSpace3);
        v.divide(3);
        center.set(v);
    }

    public String toString(){
        return getClass().getSimpleName() + "[transform:" + transform + ", point1:" + point1 + ", point2:" + point2 + ", point3:" + point3 + ", normal:" + normal + "]";
    }
}