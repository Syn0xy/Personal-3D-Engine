package engine.component.collider;

import engine.geometric.Vector3;

public class BoxCollider extends Collider{
    public final static int SIZE_X = 1;
    public final static int SIZE_Y = 1;
    public final static int SIZE_Z = 1;

    private Vector3 size;

    public BoxCollider(Vector3 size, Vector3 offset, boolean trigger){
        this.size = size;
        this.offset = offset;
        this.trigger = trigger;
    }

    public BoxCollider(Vector3 size, Vector3 offset){
        this(size, offset, TRIGGER);
    }

    public BoxCollider(Vector3 size, boolean trigger){
        this(size, new Vector3(), trigger);
    }

    public BoxCollider(Vector3 size){
        this(size, new Vector3());
    }

    public BoxCollider(){
        this(new Vector3(SIZE_X, SIZE_Y, SIZE_Z));
    }

    public Vector3 getSize(){ return size; }

    public ColliderType getType(){ return ColliderType.BOX; }

    public void setSize(Vector3 size){ this.size = size; }

    public String toString(){
        return getClass().getSimpleName() + "[size:" + size + ", offset:" + offset + ", trigger:" + trigger + "]";
    }
}