package engine.scene.management;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import engine.input.Input;
import engine.scene.GameObject;
import engine.util.Cooldown;
import engine.util.Debug;
import engine.util.Time;
import engine.view.GameView;

public class GameScene {
    private List<GameObject> gameObjects;
    private GameView gameView;

    public GameScene(){
        this.gameObjects = new ArrayList<>();
        this.gameView = new GameView(this);
    }

    public List<GameObject> getGameObjects(){ return gameObjects; }
    public GameView getGameView(){ return gameView; }

    public void start(){
        new Thread("Engine"){
            public void run(){
                while(isAlive() && !isInterrupted()){
                    try{
                        Time.update();
                        Cooldown.update();
                        Input.update();
                        update();
                    }catch(NullPointerException | ConcurrentModificationException e){
                        System.err.println("Error Engine Thread : " + e.getMessage());
                    }
                }
            }
        }.start();
        gameView.setVisible(true);
    }

    private void update(){ for(GameObject g : gameObjects){ g.update(); } }
    // private void fixedUpdate(){ for(GameObject g : gameObjects){ g.fixedUpdate(); } } TODO
    
    public void instantiate(GameObject g){
        if(!gameObjects.contains(g)){
            g.setGameScene(this);
            g.awake();
            g.start();
            gameObjects.add(g);
        }
        else System.out.println("Error : Le GameObject existe déjà");
    }
    
    public boolean destroy(GameObject g){
        g.destroy();
        return gameObjects.remove(g);
    }

    public void print(){
        Debug.println("GameObjects", gameObjects);
    }
}