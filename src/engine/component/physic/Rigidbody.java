package engine.component.physic;

import engine.component.Component;
import engine.geometric.Vector3;
import engine.util.Time;

public class Rigidbody extends Component{
    public final static double VELOCITY = 0;
    public final static double GRAVITY = 0.9;

    private Vector3 inertie;
    private double velocity;

    private Rigidbody(Vector3 inertie, double velocity){
        this.inertie = inertie;
        this.velocity = velocity;
    }

    private Rigidbody(Vector3 inertie){
        this(inertie, VELOCITY);
    }

    public Rigidbody(){
        this(new Vector3());
    }

    public Vector3 getInertie(){ return inertie; }
    public double getVelocity(){ return velocity; }

    public void update(){
        transform.getPosition().plus(inertie);
        Vector3 oldInertie = inertie;
        inertie.multiply(GRAVITY);
        velocity = oldInertie.distance(inertie) / Time.getDeltaTime();

        if(velocity < 0.001) velocity = 0;
    }

    public void addForce(Vector3 force){
        inertie.plus(force);
    }

    public void addForceForward(double force){
        inertie.plus(transform.getRotation().forward(force));
    }

    public void addForceSideward(double force){
        inertie.plus(transform.getRotation().sideward(force));
    }

    public String toString(){
        return getClass().getSimpleName() + "[inertie:" + inertie + ", velocity:" + velocity + "]";
    }
}
