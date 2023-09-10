package engine.input;

public class InputAction {
    private String name;
    private InputKeyCode positive;
    private InputKeyCode negative;

    public InputAction(String name, InputKeyCode positive, InputKeyCode negative){
        this.name = name;
        this.positive = positive;
        this.negative = negative;
    }

    public InputAction(String name, InputKeyCode positive){
        this(name, positive, null);
    }

    public String getName(){ return name; }
    public InputKeyCode getPositive(){ return positive; }
    public InputKeyCode getNegative(){ return negative; }
    public double getValue(){
        int value = 0;
        if(positive != null && positive.isStay()) value += 1;
        if(negative != null && negative.isStay()) value -= 1;
        return value;
    }

    public boolean isActive(){
        return getValue() != 0;
    }

    public boolean isEnter(){
        if(positive != null){
            if(negative != null) return positive.isEnter() || negative.isEnter();
            return positive.isEnter();
        }
        return false;
    }

    public boolean isStay(){
        if(positive != null){
            if(negative != null) return positive.isStay() || negative.isStay();
            return positive.isStay();
        }
        return false;
    }

    public boolean isExit(){
        if(positive != null){
            if(negative != null) return positive.isExit() || negative.isExit();
            return positive.isExit();
        }
        return false;
    }
    
    public static InputAction getInputActionOrNull(String name, String positive, String negative){
        if(name == null) return null;
        try{
            InputKeyCode inputPositive = Input.getInputKeyCode(KeyCode.valueOfString(positive));
            InputKeyCode inputNegative = Input.getInputKeyCode(KeyCode.valueOfString(negative));
            if(inputPositive != null){
                if(inputNegative != null) return new InputAction(name, inputPositive, inputNegative);
                return new InputAction(name, inputPositive);
            }
        }catch(Exception e){
            System.out.println("Error : InputAction : " + e.getMessage());
        }
        return null;
    }

    public boolean equals(String name){
        return this.name.equals(name);
    }

    public String toString(){
        return getClass().getSimpleName() + "[name:" + name + ", positive:" + positive + ", negative:" + negative + "]";
    }
}
