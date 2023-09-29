package engine.graphics.component;

import java.util.ArrayList;
import java.util.List;

import engine.component.Component;
import engine.graphics.PaintScene;
import engine.scene.management.GameScene;
import engine.util.Cooldown;

public class Camera extends Component{
    public final static List<Camera> CAMERAS = new ArrayList<>();
    public final static int FIELD_OF_VIEW = 90;
    
    private int fieldOfView;
    private PaintScene paintScene;

    private Cooldown cooldownUpdate;
    private Cooldown cooldownOneSecond;

    private boolean limitFPS;
    private int frames;
    private int currentFramesPerSecond;
    private int framesPerSecond;
    private int refreshTime;

    public Camera(int fieldOfView){
        this.fieldOfView = fieldOfView;
        this.paintScene = new PaintScene(this);
        init();
    }

    public Camera(){
        this(FIELD_OF_VIEW);
    }

    public int getFieldOfView(){ return fieldOfView; }
    public PaintScene getPaintScene(){ return paintScene; }
    public int getFramesPerSecond(){ return currentFramesPerSecond; }
    public int getCurrentFramesPerSecond(){ return currentFramesPerSecond; }

    public void setFieldOfView(int fieldOfView){ this.fieldOfView = fieldOfView; }

    public void start(){
        CAMERAS.add(this);
    }

    private void init(){
        this.cooldownUpdate = new Cooldown();
        this.cooldownOneSecond = new Cooldown();
        this.limitFPS = false;
        this.refreshTime = 1;
    }

    private void updateScene(){
        if(limitFPS && cooldownUpdate.isValid()){
                frames+=1;
                drawScene();
                cooldownUpdate.set(1 / framesPerSecond);
        }
        else{
            frames+=1;
            drawScene();
        }
        if(cooldownOneSecond.isValid()){
            currentFramesPerSecond = (int)(frames * (1 / refreshTime));
            frames = 0;
            cooldownOneSecond.set(refreshTime);
        }
    }

    public void drawScene(){
        paintScene.drawScene();
    }
    
    public static void updateScenes(){
        for(Camera c : CAMERAS){
            c.updateScene();
        }
    }
    
    public static void setWindowSize(int windowWidth, int windowHeight){
        for(Camera c : CAMERAS){
            c.getPaintScene().setWindowSize(windowWidth, windowHeight);
        }
    }
    
    public String toString(){
        return getClass().getSimpleName() + "[fieldOfView:" + fieldOfView + ", paintScene:" + paintScene + "]";
    }
}