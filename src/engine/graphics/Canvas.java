package engine.graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class Canvas extends JPanel{
    @Override public abstract void paint(Graphics g);

    protected void clearScreen(Graphics g, Color c){
        g.setColor(c);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
