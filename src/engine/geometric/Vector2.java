package engine.geometric;

public class Vector2 {
    public final static double X = 0;
    public final static double Y = 0;

    public double x;
    public double y;
    
    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2(){
        this(X, Y);
    }
    
    public void plus(Vector2 v){ plus(v.x, v.y); }
    public void plus(double a){ plus(a, a); }
    public void plus(double x, double y){ plusX(x); plusY(y); }
    public void plusX(double x){ this.x += x; }
    public void plusY(double y){ this.y += y; }
    
    public void minus(Vector2 v){ minus(v.x, v.y); }
    public void minus(double a){ minus(a, a); }
    public void minus(double x, double y){ minusX(x); minusY(y); }
    public void minusX(double x){ this.x -= x; }
    public void minusY(double y){ this.y -= y; }
    
    public void multiply(Vector2 v){ multiply(v.x, v.y); }
    public void multiply(double a){ multiply(a, a); }
    public void multiply(double x, double y){ multiplyX(x); multiplyY(y); }
    public void multiplyX(double x){ this.x *= x; }
    public void multiplyY(double y){ this.y *= y; }

    public Vector2 copy(){ return new Vector2(x, y); }
    public Vector2 multiplyCopy(double a){ Vector2 v = copy(); v.multiply(a); return v; }
    public Vector2 multiplyCopy(Vector2 a){ Vector2 v = copy(); v.multiply(a); return v; }
    public Vector2 plusCopy(double a){ Vector2 v = copy(); v.plus(a); return v; }
    public Vector2 plusCopy(Vector2 a){ Vector2 v = copy(); v.plus(a); return v; }

    public boolean equals(Vector2 v){
        return x == v.x && y == v.y;
    }

    public String toString(){
        return getClass().getSimpleName() + "[x:" + x + ", y:" + y + "]";
    }
}