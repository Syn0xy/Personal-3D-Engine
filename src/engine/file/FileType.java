package engine.file;

public enum FileType {
    CSV("csv"),
    OBJ("obj");

    private String type;

    private FileType(String type){
        this.type = type;
    }

    public String getType(){ return type; }
    public String getName(){ return name(); }

    public static boolean isType(String fileName, FileType fileType){
        return fileName.substring(fileName.length() - 4, fileName.length()).equals("." + fileType.getType());
    }

    public String toString(){
        return getClass().getSimpleName() + "[name:" + getName() + ", type:" + type + "]";
    }
}
