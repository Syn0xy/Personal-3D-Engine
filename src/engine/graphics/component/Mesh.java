package engine.graphics.component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import engine.component.Component;
import engine.geometric.Vector3;
import engine.graphics.Material;
import engine.graphics.MeshData;
import engine.graphics.Triangle;
import engine.scene.PrimitiveType;

public class Mesh extends Component{
    public final static List<Mesh> MESHS = new CopyOnWriteArrayList<>();
    public final static List<Triangle> TRIANGLES_LIST = new ArrayList<>();
    public final static String FILE = "cube.obj";
    public final static int LOD = 0;

    private MeshData data;
    private String fileName;
    private int lod;

    private final List<Triangle> TRIANGLES;
    
    private Mesh(String fileName, MeshData data, int lod, List<Triangle> TRIANGLES){
        this.fileName = fileName;
        this.data = data;
        this.lod = lod;
        this.TRIANGLES = TRIANGLES;
    }

    private Mesh(String fileName, MeshData data, int lod){
        this(fileName, data, lod, new ArrayList<>());
    }

    private Mesh(String fileName, MeshData data){
        this(fileName, data, LOD);
    }

    public String getFileName(){ return fileName; }
    public MeshData getData(){ return data; }
    public int getLod(){ return lod; }
    public List<Triangle> getTRIANGLES(){ return TRIANGLES; }

    public void setFileName(String fileName){ this.fileName = fileName; }
    public void setData(MeshData data){ this.data = data; }
    public void setLod(int lod){ this.lod = lod; }

    public static Mesh load(){
        return load(FILE);
    }

    public static Mesh load(PrimitiveType type){
        return load(type.getFileName());
    }

    public static Mesh load(String fileName){
        return new Mesh(fileName, MeshData.load(fileName));
    }
    
    public void loadTriangles(MeshData data){
        if(data == null) return;

        Vector3[] vertices = data.getVertices();
        Integer[] indicesVertice = data.getindicesVertice();
        Vector3[] normals = data.getNormals();
        Integer[] indicesNormal = data.getindicesNormal();
        
        if(vertices == null) return;
        if(indicesVertice == null) return;
        if(normals == null) return;
        if(indicesNormal == null) return;

        List<Triangle> list = new ArrayList<>();
        
        int nbIndicesVertice = data.getindicesVertice().length;
        if(nbIndicesVertice >= 3 && nbIndicesVertice % 3 == 0){
            for(int i = 0; i < nbIndicesVertice; i+=3){
                Vector3 v1 = vertices[indicesVertice[i]];
                Vector3 v2 = vertices[indicesVertice[i+1]];
                Vector3 v3 = vertices[indicesVertice[i+2]];
                
                Triangle triangle = new Triangle(transform, v1, v2, v3, normals[indicesNormal[i]], new Material());
                
                list.add(triangle);
            }
        }

        // Debug.println("Triangles", list);

        TRIANGLES.addAll(list);
        TRIANGLES_LIST.addAll(TRIANGLES);
    }

    public void awake(){
        loadTriangles(data);
    }

    public String toString(){
        return getClass().getSimpleName() + "[data:" + data + ", lod:" + lod + "]";
    }
}