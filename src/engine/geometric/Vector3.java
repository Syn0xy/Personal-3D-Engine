package engine.geometric;

import engine.util.Mathf;

public class Vector3 {
    public final static Vector3 ZERO(){ return new Vector3(0, 0, 0); }
    public final static Vector3 ONE(){ return new Vector3(1, 1, 1); }
    public final static Vector3 FORWARD(){ return new Vector3(0, 0, 1); }
    public final static Vector3 BACKWARD(){ return new Vector3(0, 0, -1); }
    public final static Vector3 UP(){ return new Vector3(0, 1, 0); }
    public final static Vector3 DOWN(){ return new Vector3(0, -1, 0); }
    public final static Vector3 RIGHT(){ return new Vector3(1, 0, 0); }
    public final static Vector3 LEFT(){ return new Vector3(-1, 0, 0); }

    public final static double X = 0;
    public final static double Y = 0;
    public final static double Z = 0;

    private double x;
    private double y;
    private double z;
    
    public Vector3(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(){
        this(X, Y, Z);
    }

    public double getX(){ return x; }
    public double getY(){ return y; }
    public double getZ(){ return z; }

    public void set(Vector3 v){ set(v.getX(), v.getY(), v.getZ()); }
    public void set(double a){ set(a, a, a); }
    public void set(double x, double y, double z){ setX(x); setY(y); setZ(z); }
    public void setX(double x){ this.x = x; }
    public void setY(double y){ this.y = y; }
    public void setZ(double z){ this.z = z; }
    
    public void plus(Vector3 v){ plus(v.getX(), v.getY(), v.getZ()); }
    public void plus(double a){ plus(a, a, a); }
    public void plus(double x, double y, double z){ plusX(x); plusY(y); plusZ(z); }
    public void plusX(double x){ this.x += x; }
    public void plusY(double y){ this.y += y; }
    public void plusZ(double z){ this.z += z; }
    
    public void minus(Vector3 v){ minus(v.getX(), v.getY(), v.getZ()); }
    public void minus(double a){ minus(a, a, a); }
    public void minus(double x, double y, double z){ minusX(x); minusY(y); minusZ(z); }
    public void minusX(double x){ this.x -= x; }
    public void minusY(double y){ this.y -= y; }
    public void minusZ(double z){ this.z -= z; }
    
    public void multiply(Vector3 v){ multiply(v.getX(), v.getY(), v.getZ()); }
    public void multiply(double a){ multiply(a, a, a); }
    public void multiply(double x, double y, double z){ multiplyX(x); multiplyY(y); multiplyZ(z); }
    public void multiplyX(double x){ this.x *= x; }
    public void multiplyY(double y){ this.y *= y; }
    public void multiplyZ(double z){ this.z *= z; }

    public Vector3 copy(){ return new Vector3(x, y, z); }
    public Vector3 multiplyCopy(double a){ Vector3 v = copy(); v.multiply(a); return v; }
    public Vector3 multiplyCopy(Vector3 a){ Vector3 v = copy(); v.multiply(a); return v; }
    public Vector3 plusCopy(double a){ Vector3 v = copy(); v.plus(a); return v; }
    public Vector3 plusCopy(Vector3 a){ Vector3 v = copy(); v.plus(a); return v; }
    
    public double distance(Vector3 v){ return Math.sqrt(Math.pow(this.x - v.getX(), 2) + Math.pow(this.y - v.getY(), 2) + Math.pow(this.z - v.getZ(), 2)); }
    public double distanceHorizontal(Vector3 v){ return Math.sqrt(Math.pow(this.x - v.getX(), 2) + Math.pow(this.z - v.getZ(), 2)); }
    public double distanceVertical(Vector3 v){ return Mathf.distance(this.y, v.getY()); }
    
    public static double distance(Vector3 v1, Vector3 v2){ return v1.distance(v2); }
    public static double distanceHorizontal(Vector3 v1, Vector3 v2){ return v1.distanceHorizontal(v2); }
    public static double distanceVertical(Vector3 v1, Vector3 v2){ return v1.distanceVertical(v2); }

    public Vector2 toVector2(){
        return new Vector2(x, y);
    }

    public static Vector3 lerp(Vector3 a, Vector3 b, double v){
        if(v == 0) return a.copy();
        else if(v == 1) return b.copy();
        
        return new Vector3(
            a.getX() + (b.getX() - a.getX()) * v,
            a.getY() + (b.getY() - a.getY()) * v,
            a.getZ() + (b.getZ() - a.getZ()) * v);
    }
    
    public void rotate(Vector3 rotation){ rotate(this, rotation); }
    public void rotate(Vector3 positionBase, Vector3 rotation){
        MutableNumber<Double> verticeX = new MutableNumber<Double> (positionBase.getX());
        MutableNumber<Double> verticeY = new MutableNumber<Double> (positionBase.getY());
        MutableNumber<Double> verticeZ = new MutableNumber<Double> (positionBase.getZ());
        
        Mathf.rotation(verticeY, verticeZ, rotation.getX());
        Mathf.rotation(verticeY, verticeX, rotation.getZ());
        Mathf.rotation(verticeX, verticeZ, rotation.getY());

        set(verticeX.getValue(), verticeY.getValue(), verticeZ.getValue());
    }

    public Vector3 forward(double distance){ Vector3 v = forward(); v.multiply(distance); return v; }
    public Vector3 sideward(double distance){ Vector3 v = sideward(); v.multiply(distance); return v; }

    public Vector3 forward(){
        // return new Vector3(
        //     Mathf.sinInDegrees(y),
        //     Mathf.sinInDegrees(x),
        //     Mathf.cosInDegrees(y) * Mathf.cosInDegrees(x));
        return new Vector3(
            Mathf.cosInDegrees(x) * Mathf.sinInDegrees(y),
            Mathf.sinInDegrees(x),
            Mathf.cosInDegrees(x) * Mathf.cosInDegrees(y));
    }

    public Vector3 sideward(){
        return new Vector3(Mathf.cosInDegrees(-y), 0, Mathf.sinInDegrees(-y));
    }

    public boolean equals(Vector3 v){
        return x == v.getX() && y == v.getY() && z == v.getZ();
    }

    public String toString(){
        return getClass().getSimpleName() + "[x:" + x + ", y:" + y + ", z:" + z + "]";
    }
}