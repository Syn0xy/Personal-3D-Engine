package engine.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collections;
import java.util.List;

import engine.geometric.Transform;
import engine.geometric.Vector2;
import engine.geometric.Vector3;
import engine.graphics.component.Camera;
import engine.graphics.component.Mesh;
import engine.util.DebugLine;
import engine.util.Mathf;

public class PaintScene {
    public final static List<Triangle> TRIANGLES = Mesh.TRIANGLES_LIST;
    public final static Color BACKGROUND = Color.BLACK;

    public static int windowWidth;
    public static int windowHeight;
    public static int halfWindowWidth;
    public static int halfWindowHeight;
    public static double screenProportion;

    private Camera camera;
    private static Graphics graphics;

    public PaintScene(Camera camera){
        this.camera = camera;
    }

    public static void setGraphics(Graphics graphics){
        PaintScene.graphics = graphics;
    }

    public void setWindowSize(int windowWidth, int windowHeight){
        PaintScene.windowWidth = windowWidth;
        PaintScene.windowHeight = windowHeight;
        PaintScene.halfWindowWidth = windowWidth / 2;
        PaintScene.halfWindowHeight = windowHeight / 2;
        PaintScene.screenProportion = halfWindowWidth / (Mathf.tanInDegrees(camera.getFieldOfView()/2));
    }

    public void drawScene(){
        if(graphics == null) return;
        clearScreen();
        draw();
    }

    public void draw(){
        drawDebugs();
        drawMeshs();
    }

    public void clearScreen(){
        graphics.setColor(BACKGROUND);
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
    
    public void drawMeshs(){
        reloadTriangles();
        sortTriangles();
        drawTriangles();
    }
    
    public void reloadTriangles(){
        for(Triangle t : TRIANGLES){
            t.reload(camera);
        }
    }
    
    public void sortTriangles(){
        Collections.sort(TRIANGLES, new DistanceTriangleComparator(camera.getTransform().getPosition()));
        Collections.reverse(TRIANGLES);
    }
    
    public void drawTriangles(){
        for(Triangle t : TRIANGLES){
            drawTriangle(t);
        }
    }
    
    public void drawTriangle(Triangle t){
        if(!t.isVisible()) return;
        
        Vector2 l1 = t.getPoint1().getLocation(), l2 = t.getPoint2().getLocation(), l3 = t.getPoint3().getLocation();
        drawPolygon(t.getMaterial().getColor().toColorAWT(),
            (int)l1.getX(), (int)l1.getY(),
            (int)l2.getX(), (int)l2.getY(),
            (int)l3.getX(), (int)l3.getY());
    }
    
    public void drawPolygon(Color color, int x1, int y1, int x2, int y2, int x3, int y3){        
        graphics.setColor(color);
        graphics.fillPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
    }

    public void drawDebugs(){
        drawDebugLines();
    }

    public void drawDebugLines(){
        for(DebugLine dl : DebugLine.LINES){
            drawDebugLine(dl);
        }
    }

    public void drawDebugLine(DebugLine line){
        Point p1 = new Point(line.getStart());
        Point p2 = new Point(line.getEnd());
        p1.reload(camera, new Transform(Vector3.ZERO()));
        p2.reload(camera, new Transform(Vector3.ZERO()));
        drawLine(p1.getLocation(), p2.getLocation());
    }

    public String toString(){
        return getClass().getSimpleName() + "[camera:" + camera + ", graphics:" + graphics + ", windowWidth:" + windowWidth + ", windowHeight:" + windowHeight + "]";
    }
}