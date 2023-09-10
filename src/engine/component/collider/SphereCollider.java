package engine.component.collider;

import engine.geometric.Vector3;

public class SphereCollider extends Collider{
    public final static double RADIUS = 0.5;

    private double radius;

    public SphereCollider(double radius, Vector3 offset, boolean trigger){
        this.radius = radius;
        this.offset = offset;
        this.trigger = trigger;
    }

    public SphereCollider(double radius, Vector3 offset){
        this(radius, offset, TRIGGER);
    }

    public SphereCollider(double radius, boolean trigger){
        this(radius, new Vector3(), trigger);
    }

    public SphereCollider(double radius){
        this(radius, new Vector3());
    }

    public SphereCollider(){
        this(RADIUS);
    }

    public double getRadius(){ return radius; }

    public ColliderType getType(){ return ColliderType.SPHERE; }

    public void setRadius(double radius){ this.radius = radius; }

    public String toString(){
        return getClass().getSimpleName() + "[radius:" + radius + ", offset:" + offset + ", trigger:" + trigger + "]";
    }
}