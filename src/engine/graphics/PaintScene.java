package engine.graphics;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import engine.geometric.Transform;
import engine.geometric.Vector3;
import engine.graphics.component.Camera;
import engine.util.DebugLine;
import engine.util.Mathf;

import static engine.graphics.component.Mesh.TRIANGLES_LIST;
import static java.awt.Color.BLACK;
import static application.Launcher.windowWidth;
import static application.Launcher.windowHeight;

public class PaintScene extends Canvas{
    public static int halfWindowWidth;
    public static int halfWindowHeight;
    public static double screenProportion;

    private Camera camera;
    
    public PaintScene(Camera camera){
        this.camera = camera;
        reloadWindowSize();
    }

    public Camera getCamera(){ return camera; }
    
    public void reloadWindowSize(){
        PaintScene.halfWindowWidth = windowWidth / 2;
        PaintScene.halfWindowHeight = windowHeight / 2;
        PaintScene.screenProportion = halfWindowWidth / (Mathf.tanInDegrees(camera.getFieldOfView() / 2));
    }

    public void update(){
        repaint();
        drawScene();
    }

    public void drawScene(){
        if(canDraw()){
            clearScreen(BLACK);
            draw();
        }
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
            if(t.isVisible()) drawTriangle(t);
        }
    }
    
    public void drawTriangle(Triangle t){
        drawPolygon(t.getMaterial().getColor(), t.getPolygon());
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
        return getClass().getSimpleName() + "[camera:" + camera + "]";
    }
}