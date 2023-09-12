package engine.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import engine.input.Input;
import engine.input.InputAction;

public abstract class FileTranslatorCSV {
    public final static String PATH = FileFinder.PATH + "csv" + File.separator;
    public final static String TYPE_CSV = "csv";
    public final static String CSV_DELIMITER = ";";

    public final static String INPUT_TYPE = "TYPE";
    public final static String INPUT_AXIS = "AXIS";
    public final static String INPUT_NAME = "NAME";
    public final static String INPUT_POSITIVE = "POSITIVE";
    public final static String INPUT_NEGATIVE = "NEGATIVE";

    public static LinkedList<List<String>> read(String fileName){
        return read(PATH, fileName);
    }

    public static LinkedList<List<String>> read(String path, String fileName){
        File file = FileFinder.find(path, fileName);

        if(!FileType.isType(fileName, FileType.CSV)) return null;

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            LinkedList<List<String>> data = new LinkedList<>();
            while(br.ready()){
                List<String> l = new ArrayList<>();
                data.add(l);
                Scanner sc = new Scanner(br.readLine()).useDelimiter(CSV_DELIMITER);
                while(sc.hasNext()) l.add(sc.next());
                sc.close();
            }
            br.close();
            return data;
        }catch(Exception e){
            System.out.println("Error : read CSV : " + e.getMessage());
        }
        
        return null;
    }
    
    public static List<InputAction> getInputs(String fileName){
        LinkedList<List<String>> list = read(fileName);
        List<InputAction> inputs = new ArrayList<>();

        if(list == null) return inputs;

        List<String> order = list.removeFirst();
        
        for(List<String> l : list){
            String type = null, axis = null, name = null, positive = null, negative = null;
            if(order.size() < l.size()){
                System.out.println("Error : ligne size != order size");
                continue;
            }

            for(int i = 0; i < l.size(); i++){
                switch(order.get(i)){
                    case INPUT_TYPE : type = l.get(i); break;
                    case INPUT_AXIS : axis = l.get(i); break;
                    case INPUT_NAME : name = l.get(i); break;
                    case INPUT_POSITIVE : positive = l.get(i); break;
                    case INPUT_NEGATIVE : negative = l.get(i); break;
                }
            }
            
            InputAction ia = InputAction.getInputActionOrNull(type, axis, name, positive, negative);
            if(ia != null) inputs.add(ia);
        }
        return inputs;
    }
    
    public static List<InputAction> getInputsAction(){
        return getInputs(Input.FILE);
    }
}