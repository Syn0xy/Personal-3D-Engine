package engine.input;

import java.util.ArrayList;
import java.util.List;

public class InputKeyCode {
    private KeyCode keyCode;
    private boolean enter;
    private boolean stay;
    private boolean exit;
    private boolean nothing;

    public InputKeyCode(KeyCode keyCode){
        this.keyCode = keyCode;
        this.enter = false;
        this.stay = false;
        this.exit = false;
        this.nothing = true;
    }

    public KeyCode getKeyCode(){ return keyCode; }
    public boolean isEnter(){ return enter; }
    public boolean isStay(){ return stay; }
    public boolean isExit(){ return exit; }
    public boolean isNothing(){ return nothing; }

    public void setEnter(boolean enter){ this.enter = enter; }
    public void setStay(boolean stay){ this.stay = stay; }
    public void setExit(boolean exit){ this.exit = exit; }
    public void setNothing(boolean nothing){ this.nothing = nothing; }

    public void update(){
        if(nothing) return;
        if(enter) enter = false;
        if(exit) reset();
    }

    public void reset(){
        this.enter = false;
        this.stay = false;
        this.exit = false;
        this.nothing = true;
    }

    public static List<InputKeyCode> getInputsKeyCode(){
        List<InputKeyCode> list = new ArrayList<>();
        for(KeyCode k : KeyCode.values()) list.add(new InputKeyCode(k));
        return list;
    }

    public boolean equals(KeyCode keyCode){ return this.keyCode == keyCode; }
    public boolean equals(String key){ return this.keyCode.equals(key); }

    public String toString(){
        return getClass().getSimpleName() + "[keycode:" + keyCode + ", enter:" + enter + ", stay:" + stay + ", exit:" + exit + "]";
    }
}
