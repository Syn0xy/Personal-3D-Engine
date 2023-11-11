package engine.scene;

import java.util.ArrayList;
import java.util.List;

import engine.component.Component;
import engine.geometric.Transform;
import engine.geometric.Vector3;
import engine.graphics.component.Mesh;
import engine.scene.management.GameScene;

public class GameObject {
    public final static PrimitiveType PRIMITIVE_TYPE = PrimitiveType.CUBE;
    public final static String NAME = "GameObject";
    public static int count = 0;
    
    private String name;
    private Transform transform;
    private List<Component> components;

    private GameScene gameScene;

    public GameObject(String name, Transform transform, List<Component> components){
        this.name = name;
        this.transform = transform;
        this.components = components;
    }

    public GameObject(String name, Transform transform){
        this(name, transform, new ArrayList<>());
    }

    public GameObject(String name, Vector3 position){
        this(name, new Transform(position));
    }

    public GameObject(Transform transform){
        this(NAME + count++, transform);
    }

    public GameObject(Vector3 position){
        this(new Transform(position));
    }

    public GameObject(String name){
        this(name, new Transform());
    }

    public GameObject(){
        this(NAME + count++);
    }

    public String getName(){ return name; }
    public Transform getTransform(){ return transform; }
    public List<Component> getComponents(){ return components; }
    public GameScene getGameScene(){ return gameScene; }

    public void setGameScene(GameScene gameScene){ this.gameScene = gameScene; }
    
    public void awake(){ for(Component c : components) c.awake(); }
    public void start(){ for(Component c : components) c.start(); }
    public void update(){ for(Component c : components) c.update(); }
    public void fixedUpdate(){ for(Component c : components) c.fixedUpdate(); }

    public boolean addComponent(Component c){
        c.setGameObject(this);
        return components.add(c);
    }

    public boolean removeComponent(Component c){
        return components.remove(c);
    }

    public <T extends Component> boolean containsComponent(Class<T> type){
        return getComponent(type) != null;
    }

    public <T extends Component> T getComponent(Class<T> type){
        for(Component c : components){
            if(c.getClass().isAssignableFrom(type)){
                return (T)c;
            }
        }
        return null;
    }
    
    public boolean destroy(){
        return destroy(this);
    }

    public boolean destroy(GameObject g){
        return gameScene.destroy(g);
    }

    public boolean destroy(Component c){
        return removeComponent(c);
    }
    
    public static GameObject createPrimitive(){
        return createPrimitive(PRIMITIVE_TYPE);
    }

    public static GameObject createPrimitive(PrimitiveType type){
        GameObject g = new GameObject();
        g.addComponent(Mesh.load(type));
        return g;
    }

    public String toString(){
        return getClass().getSimpleName() + "[name:" + name + ", transform:" + transform + "]";
    }
}