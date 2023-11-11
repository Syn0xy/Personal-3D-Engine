package engine.scene;

public enum PrimitiveType{
    PLANE("plane.obj"),
    CUBE("cube.obj"),
    SPHERE("sphere.obj");

    private String fileName;

    private PrimitiveType(String fileName){
        this.fileName = fileName;
    }

    public String getFileName(){
        return fileName;
    }

    public String toString(){
        return getClass().getSimpleName() + "[name:" + name() + ", fileName:" + fileName + "]";
    }
}