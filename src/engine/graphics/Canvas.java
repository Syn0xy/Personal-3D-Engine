package engine.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

import engine.geometric.Vector2;

import static application.Launcher.windowWidth;
import static application.Launcher.windowHeight;
import static engine.graphics.PaintScene.halfWindowWidth;
import static engine.graphics.PaintScene.halfWindowHeight;

public class Canvas extends JPanel{
    private Graphics graphics;

    public boolean canDraw(){
        return graphics != null;
    }

    @Override
    public void paint(Graphics g){
        this.graphics = g;
    }

    public void clearScreen(Color color){
        graphics.setColor(color);
        graphics.fillRect(0, 0, windowWidth, windowHeight);
    }

    public void drawLine(Vector2 a, Vector2 b){
        drawLine(windowWidth, windowHeight, halfWindowWidth, halfWindowHeight);
    }

    public void drawLine(double x1, double y1, double x2, double y2){
        drawLine((int)x1, (int)y1, (int)x2, (int)y2);
    }

    public void drawLine(int x1, int y1, int x2, int y2){
        graphics.drawLine(x1 + halfWindowWidth, y1 + halfWindowHeight, x2 + halfWindowWidth, y2 + halfWindowHeight);
    }
    
    public void drawPolygon(Color color, Polygon polygon){
        graphics.setColor(color);
        graphics.fillPolygon(polygon);
    }
    
    public void drawPolygon(Color color, Vector2 a, Vector2 b, Vector2 c){
        drawPolygon(color, (int)a.getX(), (int)a.getY(), (int)b.getX(), (int)b.getY(), (int)c.getX(), (int)c.getY());
    }
    
    public void drawPolygon(Color color, int x1, int y1, int x2, int y2, int x3, int y3){
        graphics.setColor(color);
        graphics.fillPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
    }
}
