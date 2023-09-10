package engine.scene.management;

import java.util.ArrayList;
import java.util.List;

import engine.scene.GameObject;
import engine.util.Debug;

public class GameScene {
    private final static List<GameObject> GAMEOBJECTS = new ArrayList<>();

    public static List<GameObject> getGameObjects(){ return GAMEOBJECTS; }

    public static void awake(){ for(GameObject g : GAMEOBJECTS){ g.awake(); } }
    public static void start(){ for(GameObject g : GAMEOBJECTS){ g.start(); } }
    public static void update(){ for(GameObject g : GAMEOBJECTS){ g.update(); } }
    public static void fixedUpdate(){ for(GameObject g : GAMEOBJECTS){ g.fixedUpdate(); } }

    public static void print(){
        Debug.println("GameObjects", GAMEOBJECTS);
    }
    
    public static void instantiate(GameObject g){
        if(!GAMEOBJECTS.contains(g)) GAMEOBJECTS.add(g);
        else System.out.println("Error : Le GameObject existe déjà");
    }
    
    public static void destroy(GameObject g){
        if(GAMEOBJECTS.contains(g)) GAMEOBJECTS.remove(g);
    }
}