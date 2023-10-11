package application;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ConcurrentModificationException;

import javax.swing.JFrame;

import application.component.PlayerController;
import engine.component.collider.SphereCollider;
import engine.component.light.Light;
import engine.component.physic.Rigidbody;
import engine.graphics.Canvas;
import engine.graphics.WindowComponent;
import engine.graphics.component.Camera;
import engine.input.Input;
import engine.input.KeyCode;
import engine.scene.GameObject;
import engine.scene.management.GameScene;
import engine.util.Cooldown;
import engine.util.Time;

public class Launcher {
    public final static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public final static int SCREEN_WIDTH = (int)SCREEN_SIZE.getWidth();
    public final static int SCREEN_HEIGHT = (int)SCREEN_SIZE.getHeight();
    public final static int WINDOW_WIDTH = 900;
    public final static int WINDOW_HEIGHT = 600;
    public final static int FRAMES_PER_SECOND = 1000;

    public static int windowWidth = WINDOW_WIDTH;
    public static int windowHeight = WINDOW_HEIGHT;
    public static int framesPerSecond = FRAMES_PER_SECOND;
    
    public static void main(String[] args){
        setWindowSize(windowWidth, windowHeight);
        awake();
        GameScene.awake();
        GameScene.start();
        thread();
    }

    public static void setWindowSize(int windowWidth, int windowHeight){
        Launcher.windowWidth = windowWidth;
        Launcher.windowHeight = windowHeight;
        Camera.reloadWindowSize();
    }

    public static void launch(Canvas canvas){
        WindowComponent window = new WindowComponent();

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("3D Motor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(windowWidth, windowHeight);
        frame.setLocation((SCREEN_WIDTH - windowWidth) / 2, (SCREEN_HEIGHT - windowHeight) / 2);   
        frame.addComponentListener(window);
        frame.addKeyListener(window.getInput());
        frame.addMouseListener(window.getInput());
        frame.add(canvas);
        frame.setVisible(true);

        canvas.setPreferredSize(frame.getPreferredSize());
    }

    public static void thread(){
        Thread engineThread = new Thread("Engine"){
            public void run(){
                while(isAlive() && !isInterrupted()){
                    try{
                        Time.update();
                        Cooldown.update();
                        Input.update();
                        update();
                        GameScene.update();
                    }catch(NullPointerException e){
                        System.err.println("Error : " + e.getLocalizedMessage());
                    }catch(ConcurrentModificationException e){
                        System.err.println("Error : " + e.getMessage());
                    }
                }
            }
        };
        Thread graphicsThread = new Thread("Graphics"){
            public void run(){
                while(isAlive() && !isInterrupted()){
                    try{
                        Camera.updateScenes();
                    }catch(NullPointerException e){
                        System.err.println("Error : " + e.getLocalizedMessage());
                    }catch(ConcurrentModificationException e){
                        System.err.println("Error : " + e.getMessage());
                    }
                }
            }
        };
        engineThread.start();
        graphicsThread.start();
    }

    public static void awake(){
        GameObject cube = GameObject.createPrimitive();
        cube.getTransform().getPosition().set(0, 0, 10);
        
        GameObject player = new GameObject("Player");
        player.addComponent(new Camera());
        player.addComponent(new SphereCollider());
        player.addComponent(new Rigidbody());
        player.addComponent(new Light());
        player.addComponent(new PlayerController());
        
        // GameObject other = new GameObject("Other", new Transform(new Vector3(4, 0, 0)));
        // other.addComponent(new Camera());
        
        GameScene.instantiate(cube);
        GameScene.instantiate(player);
        // GameScene.instantiate(other);
    }

    public static void update(){
        if(Input.getKey(KeyCode.A)){
            System.out.println("test");
        }
        // Mesh mesh = Main.testPyramid.getComponent(Mesh.class);
        // if(mesh != null && mesh.getTriangleCount() > 0) mesh.getTriangles().get(0).calculateVisibility();

        // for(GameObject g : GameScene.getGameObjects()){
        //     if(g.getName().equals("Cube center")){
        //         g.lookAt(camera.getTransform().getPosition());
        //     }
        // }
    }
}