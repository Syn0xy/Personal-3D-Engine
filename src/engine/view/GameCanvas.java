package engine.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Arrays;

import javax.swing.JPanel;

import engine.graphics.Colorf;
import engine.graphics.DistanceTriangleComparator;
import engine.graphics.Triangle;
import engine.graphics.component.Camera;
import engine.graphics.component.Mesh;
import engine.view.util.Observer;
import engine.view.util.Subject;

public class GameCanvas extends JPanel implements Observer {    
    private Camera camera;

    public GameCanvas(Camera camera){
        this.camera = camera;
        camera.attach(this);
    }

    @Override
    public void update(Subject subj) {
        repaint();
    }

    @Override
    public void update(Subject subj, Object data) {
        repaint();
    }

    @Override
    public void paint(Graphics g){
        clearScreen(g);
        drawMeshs(g, Mesh.allTriangles);
        drawDebugs(g);
    }

    private void clearScreen(Graphics g){
        g.setColor(camera.getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
    
    private void drawMeshs(Graphics g, Triangle[] triangles){
        reloadTriangles(triangles);
        sortTriangles(triangles);
        drawTriangles(g, triangles);
    }

    private void reloadTriangles(Triangle[] triangles){
        for(int i = 0; i < triangles.length; i++){
            triangles[i].reload(camera);
        }
    }
    
    private void sortTriangles(Triangle[] t){
        Arrays.sort(t, new DistanceTriangleComparator(camera.getTransform().getPosition()));
        reverse(t);
    }

    private <T> void reverse(T[] array){
        for(int i = 0; i < array.length / 2; i++){
            T tmp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = tmp;
        }
    }
    
    private void drawTriangles(Graphics g, Triangle[] triangles){
        for(int i = 0; i < triangles.length; i++){
            Triangle t = triangles[i];
            if(!t.isVisible()) return;
            g.setColor(t.getMaterial().getColor());
            g.fillPolygon(t);
        }
    }

    private void drawPolygon(Graphics g, Polygon p){
        if(inside(p)){
            g.fillPolygon(p);
        }else{
            Polygon p1 = new Polygon(p.xpoints, p.ypoints, p.npoints);
            Polygon p2 = new Polygon(p.xpoints, p.ypoints, p.npoints);
            if(!inside(p, 0)){
                int x = p.xpoints[0] - (p.xpoints[0] - getWidth());
                if(p.xpoints[0] == p.xpoints[1] || p.ypoints[0] == p.ypoints[1]) return;
                if(p.xpoints[0] == p.xpoints[2] || p.ypoints[0] == p.ypoints[2]) return;
                if(p.xpoints[1] == p.xpoints[2] || p.ypoints[1] == p.ypoints[2]) return;
                int y1 = ((p.ypoints[1] - p.ypoints[0]) * (x - p.xpoints[0])) / (p.xpoints[1] - p.xpoints[0]) + p.ypoints[0];
                int y2 = ((p.ypoints[2] - p.ypoints[0]) * (x - p.xpoints[0])) / (p.xpoints[2] - p.xpoints[0]) + p.ypoints[0];

                p1.xpoints[0] = x;
                p1.ypoints[0] = y1;

                p2.xpoints[0] = x;
                p2.ypoints[0] = y1;
                p2.xpoints[1] = x;
                p2.ypoints[1] = y2;

                // 50   x   90
                // 10   y   20
                // x = 
                // y = ((20-10) * (55-50)) / (90-50) + 10
            }
            g.setColor(Color.GREEN);
            g.fillPolygon(p1);
            g.setColor(Color.RED);
            g.fillPolygon(p2);
        }
    }

    private boolean inside(Polygon p){
        for (int i = 0; i < p.npoints; i++) {
            if(!inside(p, i)) return false;
        }
        return true;
    }

    private boolean inside(Polygon p, int i){
        return p.xpoints[i] >= 0 && p.xpoints[i] < getWidth() && p.ypoints[i] >= 0 && p.ypoints[i] < getHeight();
    }

    private void drawDebugs(Graphics g){
        drawDebugInfos(g);
        // drawDebugLines();
    }

    private void drawDebugInfos(Graphics g){
        String s = "fps : " + String.format("% ,d" ,camera.getCurrentFramesPerSecond());
        g.setColor(Colorf.inverse(camera.getBackground()));
        g.drawString(s, 30, 30);
    }

    // private void drawDebugLines(){
    //     for(DebugLine dl : DebugLine.LINES){
    //         drawDebugLine(dl);
    //     }
    // }

    // private void drawDebugLine(DebugLine line){
    //     Point p1 = new Point(line.getStart());
    //     Point p2 = new Point(line.getEnd());
    //     p1.reload(camera, new Transform(Vector3.ZERO()));
    //     p2.reload(camera, new Transform(Vector3.ZERO()));
    //     canvas.drawLine(p1.getLocation(), p2.getLocation());
    // }
}
