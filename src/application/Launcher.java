package application;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ConcurrentModificationException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.component.PlayerController;
import engine.component.collider.SphereCollider;
import engine.component.light.Light;
import engine.component.physic.Rigidbody;
import engine.graphics.PaintScene;
import engine.graphics.component.Camera;
import engine.input.Input;
import engine.input.KeyCode;
import engine.scene.GameObject;
import engine.scene.PrimitiveType;
import engine.scene.management.GameScene;
import engine.util.Cooldown;
import engine.util.Time;

public class Launcher extends JPanel {
    public final static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public final static int SCREEN_WIDTH = (int)SCREEN_SIZE.getWidth();
    public final static int SCREEN_HEIGHT = (int)SCREEN_SIZE.getHeight();
    public final static Dimension WINDOW_SIZE = new Dimension(900, 600);
    public final static int WINDOW_WIDTH = (int)WINDOW_SIZE.getWidth();
    public final static int WINDOW_HEIGHT = (int)WINDOW_SIZE.getHeight();
    public final static int FRAMES_PER_SECOND = 1000;

    public static int windowWidth = WINDOW_WIDTH;
    public static int windowHeight = WINDOW_HEIGHT;
    public static int framesPerSecond = FRAMES_PER_SECOND;

    public static int getWindowWidth(){ return windowWidth; }
    public static int getWindowHeight(){ return windowHeight; }
    public static int getFramesPerSecond(){ return framesPerSecond; }
    
    public static JLabel label = new JLabel();

    public static void main(String[] args){
        setWindowSize(windowWidth, windowHeight);
        Launcher panel = new Launcher();
        awake();
        GameScene.awake();
        GameScene.start();
        thread(panel);
        launch(panel);
    }

    public static void setWindowSize(int windowWidth, int windowHeight){
        PaintScene.setWindowSize(windowWidth, windowHeight);
    }

    public static void launch(Launcher panel){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("3D Motor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(java.awt.Color.BLACK);
        frame.setSize(WINDOW_SIZE);
        frame.setLocation((SCREEN_WIDTH - WINDOW_WIDTH) / 2, (SCREEN_HEIGHT - WINDOW_HEIGHT) / 2);        
        frame.addKeyListener(Input.INSTANCE);
        frame.addMouseListener(Input.INSTANCE);

        panel.setPreferredSize(frame.getPreferredSize());
        
        frame.add(panel);
        frame.setVisible(true);
    }

    public void paint(Graphics g){
        PaintScene.setGraphics(g);
        Camera.INSTANCE.xupdate();
    }

    public static void thread(Launcher panel){
        Thread engineThread = new Thread("Engine"){
            public void run(){
                while(isAlive() && !isInterrupted()){
                    try{
                        update();
                        Input.update();
                        Time.update();
                        Cooldown.update();
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
                        panel.repaint();
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
        //Debug.println("Inputs Action", Input.INPUTS_ACTION);
        //GameObject cube = GameObject.createPrimitive();
        GameObject cube = GameObject.createPrimitive(PrimitiveType.CUBE);
        cube.getTransform().getPosition().set(0, 0, 10);
        
        GameObject player = new GameObject("Player");
        player.addComponent(Camera.INSTANCE);
        player.addComponent(new SphereCollider());
        player.addComponent(new Rigidbody());
        player.addComponent(new Light());
        player.addComponent(new PlayerController());

        GameScene.instantiate(cube);
        GameScene.instantiate(player);
    }

    public static void update(){
        // Mesh mesh = Main.testPyramid.getComponent(Mesh.class);
        // if(mesh != null && mesh.getTriangleCount() > 0) mesh.getTriangles().get(0).calculateVisibility();

        // for(GameObject g : GameScene.getGameObjects()){
        //     if(g.getName().equals("Cube center")){
        //         g.lookAt(camera.getTransform().getPosition());
        //     }
        // }
    }
}