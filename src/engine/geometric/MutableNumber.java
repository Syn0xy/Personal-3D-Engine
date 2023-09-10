package engine.geometric;

public class MutableNumber<T>{
    private T value;

    public MutableNumber(T value){
        this.value = value;
    }

    public MutableNumber(){
        this(null);
    }

    public T getValue(){ return value; }
    public void setValue(T value){ this.value = value; }

    public String toString(){
        return getClass().getSimpleName() + "[value:" + value + "]";
    }
}