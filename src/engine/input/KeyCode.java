package engine.input;

public enum KeyCode {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h"),
    I("i"),
    J("j"),
    K("k"),
    L("l"),
    M("m"),
    N("n"),
    O("o"),
    P("p"),
    Q("q"),
    R("r"),
    S("s"),
    T("t"),
    U("u"),
    V("v"),
    W("w"),
    X("x"),
    Y("y"),
    Z("z"),
    ALPHA_0("0"),
    ALPHA_1("1"),
    ALPHA_2("2"),
    ALPHA_3("3"),
    ALPHA_4("4"),
    ALPHA_5("5"),
    ALPHA_6("6"),
    ALPHA_7("7"),
    ALPHA_8("8"),
    ALPHA_9("9"),
    UP_ARROW("up"),
    DOWN_ARROW("down"),
    RIGHT_ARROW("right"),
    LEFT_ARROW("left"),
    NUMPAD_0("numpad-0"),
    NUMPAD_1("numpad-1"),
    NUMPAD_2("numpad-2"),
    NUMPAD_3("numpad-3"),
    NUMPAD_4("numpad-4"),
    NUMPAD_5("numpad-5"),
    NUMPAD_6("numpad-6"),
    NUMPAD_7("numpad-7"),
    NUMPAD_8("numpad-8"),
    NUMPAD_9("numpad-9"),
    F1("f1"),
    F2("f2"),
    F3("f3"),
    F4("f4"),
    F5("f5"),
    F6("f6"),
    F7("f7"),
    F8("f8"),
    F9("f9"),
    F10("f10"),
    F11("f11"),
    F12("f12"),
    LESS("less"),
    CAPS_LOCK("caps lock"),
    CTRL("ctrl"),
    ALT("alt"),
    SHIFT("shift"),
    SPACE("space"),
    ENTER("enter"),
    ESCAPE("escape"),
    MOUSE_1("mouse1"),
    MOUSE_2("mouse2"),
    MOUSE_3("mouse3"),
    MOUSE_4("mouse4"),
    MOUSE_5("mouse5");

    private String key;
    
    private KeyCode(String key){
        this.key = key;
    }

    public String getKey(){
        return key;
    }

    public String getName(){
        return name();
    }

    public static KeyCode valueOfString(String s){
        try{
            return KeyCode.valueOf(s);
        }catch(Exception e){}
        return null;
    }

    public static KeyCode valueOfStringKey(String s){
        try{
            return fromString(s);
        }catch(InvalidKeyCodeExpection e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public static KeyCode fromString(String s) throws InvalidKeyCodeExpection{
        for(KeyCode kc : values()) if(kc.equals(s)) return kc;
        throw new InvalidKeyCodeExpection("Error : Cette touche n'a pas été attribuée : [" + s + "]");
     }
    
    public boolean equals(String s){
        if(s == null) return false;
        if(key.toLowerCase().equals(s.toLowerCase())) return true;
        return false;
    }

    public String toString(){
        return getClass().getSimpleName() + "[name:" + getName() + ", key:" + key + "]";
    }
}
