package engine.view;

import java.awt.Point;

import engine.graphics.component.Camera;
import engine.scene.management.GameScene;

public class GameView extends View {
    public static final int WIDTH = (int)(SCREEN_WIDTH * (2.0 / 3.0));
    public static final int HEIGHT = (int)(SCREEN_HEIGHT * (2.0 / 3.0));

    private GameScene gameScene;
    
    public GameView(GameScene gameScene){
        this.gameScene = gameScene;
        init(WIDTH, HEIGHT);
    }

    @Override
    public String title() {
        return "3D Engine";
    }

    @Override
    public void view() {}

    public void addView(Camera camera){
        add(new GameCanvas(camera));
    }
    
    @Override
    public Point position() {
        return center();
    }
}