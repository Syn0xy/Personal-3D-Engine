package engine.view;

import java.awt.Point;

import engine.graphics.component.Camera;
import engine.util.Mathf;
import engine.view.util.Observer;
import engine.view.util.Subject;

public class GameView extends View implements Observer {
    public final static int WIDTH = (int)(SCREEN_WIDTH * (2.0 / 3.0));
    public final static int HEIGHT = (int)(SCREEN_HEIGHT * (2.0 / 3.0));

    public int halfWindowWidth;
    public int halfWindowHeight;
    public double screenProportion;

    private Camera camera;
    private GameCanvas gameCanvas;
    
    public GameView(Camera camera){
        this.camera = camera;
        this.gameCanvas = new GameCanvas(camera);
        init(WIDTH, HEIGHT);
    }

    @Override
    public String title() {
        return "3D Engine";
    }

    @Override
    public void view() {
        add(gameCanvas);
    }
    
    @Override
    public Point position() {
        return center();
    }

    @Override
    public void update(Subject subj) {
        reloadSize();
        repaint();
    }

    @Override
    public void update(Subject subj, Object data) {
        reloadSize();
        repaint();
    }

    public void reloadSize(){
        halfWindowWidth = getWidth() / 2;
        halfWindowHeight = getHeight() / 2;
        screenProportion = halfWindowWidth / (Mathf.tanInDegrees(camera.getFieldOfView()/2));
    }

    public String toString(){
        return getClass().getSimpleName() + "[camera:" + camera + "]";
    }
}