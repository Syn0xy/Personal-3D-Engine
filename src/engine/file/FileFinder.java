package engine.file;

import java.io.File;

public class FileFinder {
    public final static String PATH = "res" + File.separator;
    
    public static File find(String path, String fileName){
        path += File.separator;
        File folder = new File(path);
        File file = null;

        if(!folder.exists()) return null;
        if(folder.isDirectory()){
            for(File f : folder.listFiles()){
                if(f.isFile() && f.getName().equals(fileName)) file = f;
                if(f.isDirectory()) file = find(path + f.getName(), fileName);
                if(file != null) return file;
            }
        }

        return file;
    }

    public static File find(String fileName){
        return find(PATH, fileName);
    }
}