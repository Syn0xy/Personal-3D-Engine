package engine.view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

import engine.input.Input;

public abstract class View extends JFrame {
    protected static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    protected static final int SCREEN_WIDTH = (int)SCREEN_SIZE.getWidth();
    protected static final int SCREEN_HEIGHT = (int)SCREEN_SIZE.getHeight();

    private Input input;

    public View(){
        input = new Input();
    }

    protected void init(int width, int height){
        view();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocation(position());
        setTitle(title());
        addMouseListener(input);
        addKeyListener(input);
        setVisible(true);
    }

    public abstract String title();
    public abstract Point position();
    public abstract void view();

    public Point center(){
        return new Point((SCREEN_WIDTH - getWidth()) / 2, (SCREEN_HEIGHT - getHeight()) / 2);
    }
}
