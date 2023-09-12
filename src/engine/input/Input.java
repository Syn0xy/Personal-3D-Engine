package engine.input;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import engine.file.FileTranslatorCSV;

public class Input implements KeyListener, MouseListener{
    public final static String FILE = "inputs.csv";
    public final static Input INSTANCE = new Input();
    public final static List<InputKeyCode> INPUTS_KEYCODE = InputKeyCode.getInputsKeyCode();
    public final static List<InputAction> INPUTS_ACTION = FileTranslatorCSV.getInputsAction();
    
    public void keyPressed(KeyEvent e){ inputPressed(e); }
    public void keyReleased(KeyEvent e){ inputReleased(e); }
    public void keyTyped(KeyEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){ inputPressed(e); }
    public void mouseReleased(MouseEvent e){ inputReleased(e);}
    
    private static String getKeyboardCode(KeyEvent e){ return KeyEvent.getKeyText(e.getKeyCode()); }
    private static String getMouseCode(MouseEvent e){ return "mouse" + e.getButton(); }

    private static void inputPressed(KeyEvent e){ inputPressed(getKeyboardCode(e));}
    private static void inputPressed(MouseEvent e){ inputPressed(getMouseCode(e));}
    private static void inputPressed(String key){ setKeyCodeDown(key); }
    private static void inputReleased(KeyEvent e){ inputReleased(getKeyboardCode(e));}
    private static void inputReleased(MouseEvent e){ inputReleased(getMouseCode(e));}
    private static void inputReleased(String key){ setKeyCodeUp(key); }

    public final static Point getMouseLocation(){
        return MouseInfo.getPointerInfo().getLocation();
    }

    private static void setKeyCodeDown(String key){
        InputKeyCode input = getInputKeyCode(key);
        if(input == null) return;
        
        if(input.isNothing()) input.setEnter(true);
        input.setStay(true);
        input.setExit(false);
        input.setNothing(false);
    }

    private static void setKeyCodeUp(String key){
        InputKeyCode input = getInputKeyCode(key);
        if(input == null) return;
        
        input.setExit(true);
    }

    protected static InputKeyCode getInputKeyCode(String key){
        KeyCode kc = KeyCode.valueOfStringKey(key);
        if(kc == null) return null;
        return getInputKeyCode(kc);
    }

    protected static InputKeyCode getInputKeyCode(KeyCode kc){
        for(InputKeyCode input : INPUTS_KEYCODE) if(input.getKeyCode().equals(kc)) return input;
        return null;
    }

    private static InputAction getInputAction(String name){
        for(InputAction input : INPUTS_ACTION) if(input.equals(name)) return input;
        return null;
    }

    // --- Boolean ---

    public static boolean getKey(KeyCode kc){
        InputKeyCode input = getInputKeyCode(kc);
        if(input == null) return false;
        return input.isStay();
    }

    public static boolean getKeyDown(KeyCode kc){
        InputKeyCode input = getInputKeyCode(kc);
        if(input == null) return false;
        return input.isEnter();
    }

    public static boolean getKeyUp(KeyCode kc){
        InputKeyCode input = getInputKeyCode(kc);
        if(input == null) return false;
        return input.isExit();
    }

    public static double getAxis(String name){
        InputAction input = getInputAction(name);
        if(input == null) return 0;
        return input.getValue();
    }

    public static boolean getButton(String name){
        InputAction input = getInputAction(name);
        if(input == null) return false;
        return input.isActive();
    }

    public static boolean getButtonDown(String name){
        InputAction input = getInputAction(name);
        if(input == null) return false;
        return input.isEnter();
    }

    public static boolean getButtonUp(String name){
        InputAction input = getInputAction(name);
        if(input == null) return false;
        return input.isExit();
    }

    public static void update(){
        refreshInput();
        refreshKeyCodes();
    }

    private static void refreshInput(){
        for(InputAction i : INPUTS_ACTION){
            i.update();
        }
    }

    private static void refreshKeyCodes(){
        for(InputKeyCode input : INPUTS_KEYCODE){
            input.refresh();
        }
    }
}
