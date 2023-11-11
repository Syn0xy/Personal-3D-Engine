package engine.view;

import static engine.graphics.component.Mesh.TRIANGLES_LIST;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import engine.graphics.Canvas;
import engine.graphics.Colorf;
import engine.graphics.DistanceTriangleComparator;
import engine.graphics.Triangle;
import engine.graphics.component.Camera;

public class GameCanvas extends Canvas {
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    
    private Camera camera;

    public GameCanvas(Camera camera){
        this.camera = camera;
    }

    @Override
    public void paint(Graphics g){
        clearScreen(g, BACKGROUND_COLOR);
        drawMeshs(g);
        drawDebugs(g);
    }
    
    public void drawMeshs(Graphics g){
        List<Triangle> l = new ArrayList<>(TRIANGLES_LIST);

        reloadTriangles(l);
        sortTriangles(l);
        drawTriangles(g, l);
    }
    
    public void reloadTriangles(List<Triangle> l){
        for(Triangle t : l){
            t.reload(camera);
        }
    }
    
    public void sortTriangles(List<Triangle> l){
        Collections.sort(l, new DistanceTriangleComparator(camera.getTransform().getPosition()));
        Collections.reverse(l);
    }
    
    public void drawTriangles(Graphics g, List<Triangle> l){
        for(Triangle t : l){
            drawTriangle(g, t);
        }
    }
    
    public void drawTriangle(Graphics g, Triangle t){
        if(!t.isVisible()) return;
        g.setColor(t.getMaterial().getColor());
        g.fillPolygon(t);
    }

    public void drawDebugs(Graphics g){
        drawDebugInfos(g);
        // drawDebugLines();
    }

    public void drawDebugInfos(Graphics g){
        String s = "fps : " + String.format("% ,d" ,camera.getCurrentFramesPerSecond());
        g.setColor(Colorf.inverse(BACKGROUND_COLOR));
        g.drawString(s, 30, 30);
    }

    // public void drawDebugLines(){
    //     for(DebugLine dl : DebugLine.LINES){
    //         drawDebugLine(dl);
    //     }
    // }

    // public void drawDebugLine(DebugLine line){
    //     Point p1 = new Point(line.getStart());
    //     Point p2 = new Point(line.getEnd());
    //     p1.reload(camera, new Transform(Vector3.ZERO()));
    //     p2.reload(camera, new Transform(Vector3.ZERO()));
    //     canvas.drawLine(p1.getLocation(), p2.getLocation());
    // }
}
