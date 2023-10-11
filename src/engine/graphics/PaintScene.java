package engine.graphics;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import engine.geometric.Transform;
import engine.geometric.Vector2;
import engine.geometric.Vector3;
import engine.graphics.component.Camera;
import engine.util.DebugLine;
import engine.util.Mathf;

import static engine.graphics.component.Mesh.TRIANGLES_LIST;
import static java.awt.Color.BLACK;
import static application.Launcher.windowWidth;
import static application.Launcher.windowHeight;

public class PaintScene {
    public static int halfWindowWidth;
    public static int halfWindowHeight;
    public static double screenProportion;

    private Camera camera;
    private Canvas canvas;
    
    public PaintScene(Camera camera){
        this.camera = camera;
        this.canvas = new Canvas();
        reloadWindowSize();
    }

    public Camera getCamera(){ return camera; }
    public Canvas getCanvas(){ return canvas; }
    
    public void reloadWindowSize(){
        PaintScene.halfWindowWidth = windowWidth / 2;
        PaintScene.halfWindowHeight = windowHeight / 2;
        PaintScene.screenProportion = halfWindowWidth / (Mathf.tanInDegrees(camera.getFieldOfView()/2));
    }

    public void update(){
        canvas.repaint();
        drawScene();
    }

    public void drawScene(){
        if(!canvas.canDraw()) return;
        canvas.clearScreen(BLACK);
        draw();
    }

    public void draw(){
        // drawDebugs();
        drawMeshs();
    }
    
    public void drawMeshs(){
        reloadTriangles();
        sortTriangles();
        drawTriangles();
    }
    
    public void reloadTriangles(){
        for(Triangle t : TRIANGLES_LIST){
            t.reload(camera);
        }
    }
    
    public void sortTriangles(){
        List<Triangle> copy = new CopyOnWriteArrayList<>(TRIANGLES_LIST);
        Collections.sort(copy, new DistanceTriangleComparator(camera.getTransform().getPosition()));
        Collections.reverse(copy);
    }
    
    public void drawTriangles(){
        for(Triangle t : TRIANGLES_LIST){
            drawTriangle(t);
        }
    }
    
    public void drawTriangle(Triangle t){
        if(!t.isVisible()) return;
        
        Vector2 l1 = t.getPoint1().getLocation(), l2 = t.getPoint2().getLocation(), l3 = t.getPoint3().getLocation();
        canvas.drawPolygon(t.getMaterial().getColor().toColorAWT(),
            (int)l1.getX(), (int)l1.getY(),
            (int)l2.getX(), (int)l2.getY(),
            (int)l3.getX(), (int)l3.getY());
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
        canvas.drawLine(p1.getLocation(), p2.getLocation());
    }

    public String toString(){
        return getClass().getSimpleName() + "[camera:" + camera + ", canvas:" + canvas + "]";
    }
}