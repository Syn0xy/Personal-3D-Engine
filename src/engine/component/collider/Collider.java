package engine.component.collider;

import java.util.ArrayList;
import java.util.List;

import engine.component.Component;
import engine.geometric.Vector3;

public abstract class Collider extends Component{
    public final static List<Collider> COLLIDERS = new ArrayList<>();
    public final static boolean TRIGGER = false;
    
    protected boolean trigger;
    protected Vector3 offset;
    
    public boolean isTrigger(){ return trigger; }
    public Vector3 getOffset(){ return offset; }

    public abstract ColliderType getType();

    public void setTrigger(boolean trigger){ this.trigger = trigger; }
    public void setOffset(Vector3 offset){ this.offset = offset; }
}
