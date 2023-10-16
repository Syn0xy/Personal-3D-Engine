package engine.geometric;

public class Vector2 {
    public final static double X = 0;
    public final static double Y = 0;

    private double x;
    private double y;
    
    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2(){
        this(X, Y);
    }

    public double getX(){ return x; }
    public double getY(){ return y; }

    public void set(Vector2 v){ set(v.getX(), v.getY()); }
    public void set(double a){ set(a, a); }
    public void set(double x, double y){ setX(x); setY(y); }
    public void setX(double x){ this.x = x; }
    public void setY(double y){ this.y = y; }

    public Vector2 copy(){ return new Vector2(x, y); }
    
    public Vector2 plusCopy(double a){ return plusCopy(a, a); }
    public Vector2 plusCopy(double x, double y){ return new Vector2(this.x + x, this.y + y); }
    public void plus(Vector2 v){ plus(v.getX(), v.getY()); }
    public void plus(double a){ plus(a, a); }
    public void plus(double x, double y){ plusX(x); plusY(y); }
    public void plusX(double x){ this.x += x; }
    public void plusY(double y){ this.y += y; }
    
    public Vector2 minusCopy(double a){ return minusCopy(a, a); }
    public Vector2 minusCopy(double x, double y){ return new Vector2(this.x - x, this.y - y); }
    public void minus(Vector2 v){ minus(v.getX(), v.getY()); }
    public void minus(double a){ minus(a, a); }
    public void minus(double x, double y){ minusX(x); minusY(y); }
    public void minusX(double x){ this.x -= x; }
    public void minusY(double y){ this.y -= y; }
    
    public Vector2 multiplyCopy(double a){ return multiplyCopy(a, a); }
    public Vector2 multiplyCopy(double x, double y){ return new Vector2(this.x * x, this.y * y); }
    public void multiply(Vector2 v){ multiply(v.getX(), v.getY()); }
    public void multiply(double a){ multiply(a, a); }
    public void multiply(double x, double y){ multiplyX(x); multiplyY(y); }
    public void multiplyX(double x){ this.x *= x; }
    public void multiplyY(double y){ this.y *= y; }
    
    public Vector2 divideCopy(double a){ return divideCopy(a, a); }
    public Vector2 divideCopy(double x, double y){ return new Vector2(this.x / x, this.y / y); }
    public void divide(Vector2 v){ divide(v.getX(), v.getY()); }
    public void divide(double a){ divide(a, a); }
    public void divide(double x, double y){ divideX(x); divideY(y); }
    public void divideX(double x){ this.x /= x; }
    public void divideY(double y){ this.y /= y; }

    public boolean equals(Vector2 v){
        return x == v.getX() && y == v.getY();
    }
    
    public String toString(){
        return getClass().getSimpleName() + "[x:" + x + ", y:" + y + "]";
    }
}