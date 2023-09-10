package engine.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import engine.geometric.Vector3;
import engine.graphics.MeshData;
import engine.graphics.component.Mesh;

public class FileTranslatorOBJ {
    public final static String PATH = FileFinder.PATH + "obj" + File.separator;
    public final static String DELIMITER = " ";
    public final static String INDICES_DELIMITER = "/";

    public final static String NAME = "o";
    public final static String VERTICES = "v";
    public final static String NORMALS = "vn";
    public final static String INDICES = "f";

    public static MeshData load(String fileName){
        return load(PATH, fileName);
    }
    
    public static MeshData load(String path, String fileName){
        File file = FileFinder.find(path, fileName);

        if(file == null) return null;
        if(!FileType.isType(fileName, FileType.OBJ)) return null;

        String name = null;
        List<Vector3> vertices = new ArrayList<>();
        List<Integer> edges = new ArrayList<>();
        List<Vector3> normals = new ArrayList<>();
        List<Integer> indicesNormal = new ArrayList<>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            while(br.ready()){
                String line = br.readLine();
                Scanner sc = new Scanner(line).useDelimiter(DELIMITER);
                
                if(sc.hasNext()){
                    switch(sc.next()){
                        case NAME : name = toName(line); break;
                        case VERTICES : vertices.add(toVector3(sc)); break;
                        case NORMALS : normals.add(toVector3(sc)); break;
                        case INDICES : addIndices(sc, edges, indicesNormal); break;
                    }
                }
            }
            br.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        // Debug.println("vertices", vertices);
        // Debug.println("edges", edges);
        // Debug.println("normals", normals);
        // Debug.println("indices normals", indicesNormals);

        return new MeshData(
            fileName,
            name,
            vertices.toArray(new Vector3[vertices.size()]),
            edges.toArray(new Integer[edges.size()]),
            normals.toArray(new Vector3[normals.size()]),
            indicesNormal.toArray(new Integer[indicesNormal.size()]));
    }

    private static String toName(String line){
        return line.substring(2, line.length());
    }

    private static Vector3 toVector3(Scanner sc){
        double x = 0, y = 0, z = 0;
        if(sc.hasNext()) x = Double.valueOf(sc.next());
        if(sc.hasNext()) y = Double.valueOf(sc.next());
        if(sc.hasNext()) z = Double.valueOf(sc.next());
        return new Vector3(x, y, z);
    }

    private static void addIndices(Scanner sc, List<Integer> edges, List<Integer> indicesNormal){
        while(sc.hasNext()) addIndices(sc.next(), edges, indicesNormal);
    }

    private static void addIndices(String data, List<Integer> edges, List<Integer> indicesNormal) {
        int indiceEdge = 0;
        int indiceNormal = 0;

        String[] list = data.split(INDICES_DELIMITER);

        try{
            indiceEdge = Integer.valueOf(list[0]) - 1;
            indiceNormal = Integer.valueOf(list[2]) - 1;
        }catch(Exception e){
            System.out.println("Erreur : LoadOBJ - Indices");
        }

        edges.add(indiceEdge);
        indicesNormal.add(indiceNormal);
    }
}