package engine.graphics.component;

import java.util.ConcurrentModificationException;

import engine.component.Component;
import engine.util.Cooldown;
import engine.view.GameView;
import engine.view.util.Subject;

public class Camera extends Component{
    public final static int FIELD_OF_VIEW = 90;
    
    private int fieldOfView;
    private GameView gameView;

    private Cooldown cooldownUpdate;
    private Cooldown cooldownOneSecond;
    private boolean limitFPS;
    private int frames;
    private int currentFramesPerSecond;
    private int framesPerSecond;
    private int refreshTime;

    public Camera(int fieldOfView){
        this.fieldOfView = fieldOfView;
        this.gameView = new GameView(this);
        this.cooldownUpdate = new Cooldown();
        this.cooldownOneSecond = new Cooldown();
        this.limitFPS = true;
        this.refreshTime = 1;
        this.framesPerSecond = 500;
    }

    public Camera(){
        this(FIELD_OF_VIEW);
    }

    public int getFieldOfView(){ return fieldOfView; }
    public int getFramesPerSecond(){ return currentFramesPerSecond; }
    public int getCurrentFramesPerSecond(){ return currentFramesPerSecond; }
    
    public int getHalfWindowWidth(){ return gameView.halfWindowWidth; }
    public int getHalfWindowwHeight(){ return gameView.halfWindowHeight; }
    public double getScreenProportion(){ return gameView.screenProportion; }

    public void setFieldOfView(int fieldOfView){ this.fieldOfView = fieldOfView; }

    public void start(){
        cooldownUpdate.start();
        cooldownOneSecond.start();
                
        new Thread("Graphics"){
            public void run(){
                while(isAlive() && !isInterrupted()){
                    try{
                        updateScene();
                    }catch(NullPointerException | ConcurrentModificationException e){
                        System.err.println("Error Graphics Thread : " + e.getMessage());
                    }
                }
            }
        }.start();
    }

    private void updateScene(){
        if(limitFPS){
            if(cooldownUpdate.isValid()){
                frames+=1;
                drawScene();
                cooldownUpdate.set(1.0 / framesPerSecond);
            }
        }else{
            drawScene();
            frames+=1;
        }
        if(cooldownOneSecond.isValid()){
            currentFramesPerSecond = (int)(frames * (1.0 / refreshTime));
            frames = 0;
            cooldownOneSecond.set(refreshTime);
        }
    }

    public void drawScene(){
        gameView.update(new Subject() {});
    }
    
    public String toString(){
        return getClass().getSimpleName() + "[fieldOfView:" + fieldOfView + ", gameView:" + gameView + "]";
    }
}