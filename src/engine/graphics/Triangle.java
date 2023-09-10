package engine.graphics;

import engine.geometric.Transform;
import engine.geometric.Vector3;

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
            new Point(transform, point1),
            new Point(transform, point2),
            new Point(transform, point3),
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

    public void reload(){
        point1.reload();
        point2.reload();
        point3.reload();

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