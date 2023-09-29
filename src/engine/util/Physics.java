package engine.util;

import engine.geometric.Vector2;
import engine.geometric.Vector3;

public class Physics {
    public static boolean raycast(Vector3 a, Vector3 b){
        return true;
    }

    public static boolean inside(Vector2 p, Vector2 a, Vector2 b, Vector2 c){
        double x = p.getX(), y = p.getY();
        double Ax = a.getX(), Ay = a.getY();
        double Bx = b.getX(), By = b.getY();
        double Cx = c.getX(), Cy = c.getY();

        double denominator = (By - Cy)*(Ax - Cx) + (Cx - Bx)*(Ay - Cy);
        double lambda1 = ((By - Cy)*(x - Cx) + (Cx - Bx)*(y - Cy)) / denominator;
        double lambda2 = ((Cy - Ay)*(x - Cx) + (Ax - Cx)*(y - Cy)) / denominator;
        double lambda3 = 1 - lambda1 - lambda2;
        
        return lambda1 >= 0 && lambda1 <= 1 && lambda2 >= 0 && lambda2 <= 1 && lambda3 >= 0 && lambda3 <= 1;
    }
}