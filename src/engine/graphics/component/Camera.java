package engine.graphics.component;

import java.awt.Color;

import engine.component.Component;
import engine.graphics.Colorf;
import engine.util.Cooldown;
import engine.util.Mathf;
import engine.view.View;

public class Camera extends Component {
    public final static int WIDTH = (int)(View.SCREEN_WIDTH * (2.0 / 3.0));
    public final static int HEIGHT = (int)(View.SCREEN_HEIGHT * (2.0 / 3.0));
    public final static int FIELD_OF_VIEW = 90;
    public final static Color BACKGROUND = Colorf.random();
    
    private int width;
    private int height;
    private int fieldOfView;
    private Color background;
    
    private double screenProportion;
    private Cooldown cooldownUpdate;
    private Cooldown cooldownOneSecond;
    private boolean limitFPS;
    private int frames;
    private int currentFramesPerSecond;
    private int framesPerSecond;
    private int refreshTime;

    public Camera(int width, int height, int fieldOfView, Color background){
        this.width = width;
        this.height = height;
        this.fieldOfView = fieldOfView;
        this.background = background;
        
        this.cooldownUpdate = new Cooldown();
        this.cooldownOneSecond = new Cooldown();
        this.limitFPS = true;
        this.refreshTime = 1;
        this.framesPerSecond = 200;
    }

    public Camera(int width, int height, int fieldOfView){
        this(width, height, fieldOfView, BACKGROUND);
    }

    public Camera(int width, int height, Color background){
        this(width, height, FIELD_OF_VIEW, background);
    }

    public Camera(int width, int height){
        this(width, height, FIELD_OF_VIEW);
    }

    public Camera(int fieldOfView, Color background){
        this(WIDTH, HEIGHT, fieldOfView, background);
    }

    public Camera(int fieldOfView){
        this(fieldOfView, BACKGROUND);
    }

    public Camera(){
        this(FIELD_OF_VIEW);
    }

    public int getWidth(){ return width; }
    public int getHeight(){ return height; }
    public int getFieldOfView(){ return fieldOfView; }
    public Color getBackground(){ return background; }
    public double getScreenProportion(){ return screenProportion; }
    public int getFramesPerSecond(){ return currentFramesPerSecond; }
    public int getCurrentFramesPerSecond(){ return currentFramesPerSecond; }
    
    public void setFieldOfView(int fieldOfView){ this.fieldOfView = fieldOfView; }

    public void start(){
        gameObject.getGameScene().getGameView().addView(this);

        cooldownUpdate.start();
        cooldownOneSecond.start();
    }

    public void update(){
        if(limitFPS){
            if(cooldownUpdate.isValid()){
                frames+=1;
                notifyObservers();
                cooldownUpdate.set(1.0 / framesPerSecond);
            }
        }else{
            notifyObservers();
            frames+=1;
        }
        if(cooldownOneSecond.isValid()){
            currentFramesPerSecond = (int)(frames * (1.0 / refreshTime));
            frames = 0;
            cooldownOneSecond.set(refreshTime);
        }
        reloadSize();
    }

    public void reloadSize(){
        screenProportion = width / (2 * Mathf.tanInDegrees(fieldOfView/2));
    }
    
    public String toString(){
        return getClass().getSimpleName() + "[fieldOfView:" + fieldOfView + "]";
    }
}