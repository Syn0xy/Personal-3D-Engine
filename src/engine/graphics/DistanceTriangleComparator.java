package engine.graphics;

import java.util.Comparator;

import engine.geometric.Vector3;

public class DistanceTriangleComparator implements Comparator<Triangle>{
    private final Vector3 CAMERA_POSITION;

    public DistanceTriangleComparator(Vector3 cp){
        this.CAMERA_POSITION = cp;
    }

    public int compare(Triangle a, Triangle b){
        return Double.compare(
            CAMERA_POSITION.distance(a.getCenter()),
            CAMERA_POSITION.distance(b.getCenter())
        );
    }
}
