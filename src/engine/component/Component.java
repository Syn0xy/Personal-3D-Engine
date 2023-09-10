package engine.component;

import engine.component.collider.Collision;
import engine.geometric.Transform;
import engine.scene.GameObject;
import engine.scene.management.GameScene;

public abstract class Component {
    protected GameObject gameObject;
    protected Transform transform;
    protected String name;

    public void setGameObject(GameObject gameObject){
        this.gameObject = gameObject;
        this.transform = gameObject.getTransform();
        this.name = gameObject.getName();
    }

    public GameObject getGameObject(){ return gameObject; }
    public Transform getTransform(){ return transform; }
    public String getName(){ return name; }

    public void awake(){}
    public void start(){}
    public void update(){}
    public void fixedUpdate(){}

    public boolean addComponent(Component c){
        return gameObject.addComponent(c);
    }

    public <T extends Component> T getComponent(Class<T> type){
        return gameObject.getComponent(type);
    }

    public void destroy(){ destroy(gameObject); }
    public static void destroy(GameObject g){ GameScene.destroy(g); }
    public static void instantiate(GameObject g){ GameScene.instantiate(g); }

    public void onCollisionEnter(Collision c){}
    public void onCollisionExit(Collision c){}
    public void onCollisionStay(Collision c){}

    public void onTriggerEnter(Collision c){}
    public void onTriggerExit(Collision c){}
    public void onTriggerStay(Collision c){}
}