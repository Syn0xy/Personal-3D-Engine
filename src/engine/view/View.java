package engine.view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

import engine.input.Input;

public abstract class View extends JFrame {
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int SCREEN_WIDTH = (int)SCREEN_SIZE.getWidth();
    public static final int SCREEN_HEIGHT = (int)SCREEN_SIZE.getHeight();
    
    protected void init(int width, int height){
        view();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocation(position());
        setTitle(title());
        addMouseListener(Input.getInstance());
        addKeyListener(Input.getInstance());
    }

    public abstract String title();
    public abstract Point position();
    public abstract void view();

    public Point center(){
        return new Point((SCREEN_WIDTH - getWidth()) / 2, (SCREEN_HEIGHT - getHeight()) / 2);
    }
}
