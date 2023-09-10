package engine.graphics;

import java.util.ArrayList;
import java.util.List;

import engine.file.FileTranslatorOBJ;
import engine.geometric.Vector3;
import engine.graphics.component.Mesh;

public class MeshData {
    private String fileName;
    private String name;
    private Vector3[] vertices;
    private Integer[] indicesVertice;
    private Vector3[] normals;
    private Integer[] indicesNormal;

    public MeshData(String fileName, String name, Vector3[] vertices, Integer[] indicesVertice, Vector3[] normals, Integer[] indicesNormal){
        this.fileName = fileName;
        this.name = name;
        this.vertices = vertices;
        this.indicesVertice = indicesVertice;
        this.normals = normals;
        this.indicesNormal = indicesNormal;
    }

    public String getFileName(){ return fileName; }
    public String getName(){ return name; }
    public Vector3[] getVertices(){ return vertices; }
    public Integer[] getindicesVertice(){ return indicesVertice; }
    public Vector3[] getNormals(){ return normals; }
    public Integer[] getindicesNormal(){ return indicesNormal; }

    public int getVerticeCount(){ return vertices.length; }
    public int getindiceVerticeCount(){ return indicesVertice.length; }
    public int getNormalCount(){ return normals.length; }
    public int getIndiceNormalCount(){ return indicesNormal.length; }

    public static MeshData load(String fileName){
        return FileTranslatorOBJ.load(fileName);
    }

    public String toString(){
        return getClass().getSimpleName() +
            "[name:" + name +
            ", vertices:" + getVerticeCount() +
            ", indicesVertice:" + getindiceVerticeCount() +
            ", normals:" + getNormalCount() +
            ", indicesNormal:" + getIndiceNormalCount() + "]";
    }
}