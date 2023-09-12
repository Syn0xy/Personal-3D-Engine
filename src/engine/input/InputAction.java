package engine.input;

import engine.geometric.Vector2;

public class InputAction {
    private InputActionType type;
    private AxisType axis;
    private String name;
    private InputKeyCode positive;
    private InputKeyCode negative;
    private double value;

    public InputAction(InputActionType type, AxisType axis, String name, InputKeyCode positive, InputKeyCode negative){
        this.type = type;
        this.axis = axis;
        this.name = name;
        this.positive = positive;
        this.negative = negative;
    }

    public InputAction(InputActionType type, String name, InputKeyCode positive, InputKeyCode negative){
        this(type, null, name, positive, negative);
    }

    public InputAction(InputActionType type, String name, InputKeyCode positive){
        this(type, name, positive, null);
    }

    public InputAction(InputActionType type, AxisType axis, String name){
        this(type, axis, name, null, null);
    }

    public InputActionType getType(){ return type; }
    public AxisType getAxis(){ return axis; }
    public String getName(){ return name; }
    public InputKeyCode getPositive(){ return positive; }
    public InputKeyCode getNegative(){ return negative; }
    public double getValue(){ return value; }

    public boolean isActive(){
        return value != 0;
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
    
    public static InputAction getInputActionOrNull(String type, String axis, String name, String positive, String negative){
        if(name == null) return null;
        try{
            InputActionType inputActionType = InputActionType.valueOf(type);
            InputKeyCode inputPositive = Input.getInputKeyCode(KeyCode.valueOfString(positive));
            InputKeyCode inputNegative = Input.getInputKeyCode(KeyCode.valueOfString(negative));
            if(inputPositive != null){
                if(inputNegative != null) return new InputAction(inputActionType, name, inputPositive, inputNegative);
                return new InputAction(inputActionType, name, inputPositive);
            }else{
                AxisType axisType = AxisType.valueOf(axis);
                return new InputAction(inputActionType, axisType, name);
            }
        }catch(Exception e){
            System.out.println("Error : InputAction : " + e.getMessage());
        }
        return null;
    }

    public void update(){
        switch(type){
            case KEY :
                value = 0;
                if(positive != null && positive.isStay()) value += 1;
                if(negative != null && negative.isStay()) value -= 1;
                break;
            case MOUSE :
                java.awt.Point p = Input.getMouseLocation();
                switch(axis){
                    case X : value = p.getX(); break;
                    case Y : value = p.getY(); break;
                }
                break;
        }
    }

    public boolean equals(String name){
        return this.name.equals(name);
    }

    public String toString(){
        return getClass().getSimpleName() + "[type:" + type + ", name:" + name + ", positive:" + positive + ", negative:" + negative + "]";
    }
}
