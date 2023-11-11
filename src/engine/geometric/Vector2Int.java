package engine.geometric;

public class Vector2Int {
    private static final int X = 0;
    private static final int Y = 0;

    public int x;
    public int y;

    public Vector2Int(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Vector2Int(){
        this(X, Y);
    }

    public static final Vector2Int valueOf(double x, double y){
        return new Vector2Int((int)x, (int)y);
    }
}
