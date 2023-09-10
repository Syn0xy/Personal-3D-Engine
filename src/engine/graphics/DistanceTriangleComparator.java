package engine.graphics;

import java.util.Comparator;

import engine.geometric.Vector3;

public class DistanceTriangleComparator implements Comparator<Triangle>{
    private final Vector3 cameraPosition;

    public DistanceTriangleComparator(Vector3 cameraPosition){
        this.cameraPosition = cameraPosition;
    }

    public int compare(Triangle a, Triangle b){
        return Double.compare(
            a.getCenter().distance(cameraPosition),
            b.getCenter().distance(cameraPosition)
        );
    }
}
