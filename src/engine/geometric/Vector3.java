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

    public double x;
    public double y;
    public double z;
    
    public Vector3(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(){
        this(X, Y, Z);
    }

    public void set(Vector3 v){ set(v.x, v.y, v.z); }
    public void set(double a){ set(a, a, a); }
    public void set(double x, double y, double z){ setX(x); setY(y); setZ(z); }
    public void setX(double x){ this.x = x; }
    public void setY(double y){ this.y = y; }
    public void setZ(double z){ this.z = z; }
    
    public void plus(Vector3 v){ plus(v.x, v.y, v.z); }
    public void plus(double a){ plus(a, a, a); }
    public void plus(double x, double y, double z){ plusX(x); plusY(y); plusZ(z); }
    public void plusX(double x){ this.x += x; }
    public void plusY(double y){ this.y += y; }
    public void plusZ(double z){ this.z += z; }
    
    public void minus(Vector3 v){ minus(v.x, v.y, v.z); }
    public void minus(double a){ minus(a, a, a); }
    public void minus(double x, double y, double z){ minusX(x); minusY(y); minusZ(z); }
    public void minusX(double x){ this.x -= x; }
    public void minusY(double y){ this.y -= y; }
    public void minusZ(double z){ this.z -= z; }
    
    public void multiply(Vector3 v){ multiply(v.x, v.y, v.z); }
    public void multiply(double a){ multiply(a, a, a); }
    public void multiply(double x, double y, double z){ multiplyX(x); multiplyY(y); multiplyZ(z); }
    public void multiplyX(double x){ this.x *= x; }
    public void multiplyY(double y){ this.y *= y; }
    public void multiplyZ(double z){ this.z *= z; }
    
    public void divide(Vector3 v){ divide(v.x, v.y, v.z); }
    public void divide(double a){ divide(a, a, a); }
    public void divide(double x, double y, double z){ divideX(x); divideY(y); divideZ(z); }
    public void divideX(double x){ this.x /= x; }
    public void divideY(double y){ this.y /= y; }
    public void divideZ(double z){ this.z /= z; }

    public Vector3 copy(){ return new Vector3(x, y, z); }
    public Vector3 plusCopy(double a){ Vector3 v = copy(); v.plus(a); return v; }
    public Vector3 plusCopy(Vector3 a){ Vector3 v = copy(); v.plus(a); return v; }
    public Vector3 multiplyCopy(double a){ Vector3 v = copy(); v.multiply(a); return v; }
    public Vector3 multiplyCopy(Vector3 a){ Vector3 v = copy(); v.multiply(a); return v; }
    public Vector3 divideCopy(double a){ Vector3 v = copy(); v.divide(a); return v; }
    public Vector3 divideCopy(Vector3 a){ Vector3 v = copy(); v.divide(a); return v; }
    
    public double distance(Vector3 v){ return Math.sqrt(Math.pow(this.x - v.x, 2) + Math.pow(this.y - v.y, 2) + Math.pow(this.z - v.z, 2)); }
    public double distanceHorizontal(Vector3 v){ return Math.sqrt(Math.pow(this.x - v.x, 2) + Math.pow(this.z - v.z, 2)); }
    public double distanceVertical(Vector3 v){ return Mathf.distance(this.y, v.y); }
    
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
            a.x + (b.x - a.x) * v,
            a.y + (b.y - a.y) * v,
            a.z + (b.z - a.z) * v);
    }
    
    public void rotate(Vector3 rotation){ rotate(this, rotation); }
    public void rotate(Vector3 positionBase, Vector3 rotation){
        MutableNumber<Double> verticeX = new MutableNumber<Double> (positionBase.x);
        MutableNumber<Double> verticeY = new MutableNumber<Double> (positionBase.y);
        MutableNumber<Double> verticeZ = new MutableNumber<Double> (positionBase.z);
        
        Mathf.rotation(verticeY, verticeZ, rotation.x);
        Mathf.rotation(verticeY, verticeX, rotation.z);
        Mathf.rotation(verticeX, verticeZ, rotation.y);

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
        return x == v.x && y == v.y && z == v.z;
    }

    public String toString(){
        return getClass().getSimpleName() + "[x:" + x + ", y:" + y + ", z:" + z + "]";
    }
}