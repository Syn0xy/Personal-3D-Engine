package engine.graphics;

import java.awt.Polygon;

import engine.geometric.Transform;
import engine.geometric.Vector3;
import engine.graphics.component.Camera;

import static engine.graphics.PaintScene.halfWindowWidth;
import static engine.graphics.PaintScene.halfWindowHeight;

public class Triangle {
    public final static boolean VISIBLE = true;
    
    private Transform transform;
    private Point point1;
    private Point point2;
    private Point point3;
    private Vector3 normal;

    private boolean visible;
    private Vector3 center;
    private Material material;

    public Triangle(Transform transform, Point point1, Point point2, Point point3, Vector3 normal, boolean visible, Vector3 center, Material material){
        this.transform = transform;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.normal = normal;
        this.visible = visible;
        this.center = center;
        this.material = material;
    }

    public Triangle(Transform transform, Vector3 point1, Vector3 point2, Vector3 point3, Vector3 normal){
        this(
            transform,
            new Point(point1),
            new Point(point2),
            new Point(point3),
            normal,
            VISIBLE,
            new Vector3(),
            new Material());
    }

    public Transform getTransform(){ return transform; }
    public Point getPoint1(){ return point1; }
    public Point getPoint2(){ return point2; }
    public Point getPoint3(){ return point3; }
    public Vector3 getNormal(){ return normal; }

    public boolean isVisible(){ return visible; }
    public Vector3 getCenter(){ return center; }
    public Material getMaterial(){ return material; }

    public Polygon getPolygon(){
        int x1 = (int)point1.getLocation().getX() + halfWindowWidth;
        int x2 = (int)point2.getLocation().getX() + halfWindowWidth;
        int x3 = (int)point3.getLocation().getX() + halfWindowWidth;
        int y1 = (int)point1.getLocation().getY() + halfWindowHeight;
        int y2 = (int)point2.getLocation().getY() + halfWindowHeight;
        int y3 = (int)point3.getLocation().getY() + halfWindowHeight;
        return new Polygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
    }

    public void reload(Camera camera){
        point1.reload(camera, transform);
        point2.reload(camera, transform);
        point3.reload(camera, transform);

        calculateCenter();
        calculateVisibility();
    }

    public void calculateCenter(){
        center.set(
            (point1.getPositionSpace().getX() + point2.getPositionSpace().getX() + point3.getPositionSpace().getX()) / 3,
            (point1.getPositionSpace().getY() + point2.getPositionSpace().getY() + point3.getPositionSpace().getY()) / 3,
            (point1.getPositionSpace().getZ() + point2.getPositionSpace().getZ() + point3.getPositionSpace().getZ()) / 3);
    }

    public void calculateVisibility(){
        visible = point1.isVisible() || point2.isVisible() || point3.isVisible();
    }

    public String toString(){
        return getClass().getSimpleName() + "[transform:" + transform + ", point1:" + point1 + ", point2:" + point2 + ", point3:" + point3 + ", normal:" + normal + "]";
    }
}