package engine.graphics.component;

import java.awt.Paint;

import engine.component.Component;
import engine.graphics.PaintScene;

public class Camera extends Component{
    public final static Camera INSTANCE = new Camera();
    public final static int FIELD_OF_VIEW = 90;
    
    private int fieldOfView;
    // private Cooldown cooldownUpdate;
    // private Cooldown cooldownSecond;

    public Camera(int fieldOfView){
        this.fieldOfView = fieldOfView;
    }

    public Camera(){
        this(FIELD_OF_VIEW);
    }

    public int getFieldOfView(){ return fieldOfView; }

    public void setFieldOfView(int fieldOfView){ this.fieldOfView = fieldOfView; }

    public void xupdate(){
        PaintScene.drawScene();
        // if(Launcher.limitFPS){
        //     if(cooldownUpdate.isValid()){
        //         frames+=1;
        //         PaintScene.drawScene();
        //         nextFrame = 1/framesPerSecond;
        //         cooldownUpdate.set(nextFrame);
        //     }
        // }else{
        //     frames+=1;
        //     PaintScene.drawScene();
        // }
        // if(cooldownSecond.isValid()){
        //     currentFramesPerSecond = (int)(frames * (1 / refreshTime));
        //     frames = 0;
        //     cooldownSecond.set(refreshTime);
        // }
    }

    public String toString(){
        return getClass().getSimpleName() + "[fieldOfView:" + fieldOfView + "]";
    }
}