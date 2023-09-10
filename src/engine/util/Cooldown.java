package engine.util;

import java.util.ArrayList;
import java.util.List;

public class Cooldown {
    public final static List<Cooldown> COOLDOWNS = new ArrayList<>();
    public final static double DURATION = 10;
    public final static double CURRENT_TIME = 0;

    private double duration;
    private double currentTime;

    public Cooldown(double duration, double currentTime){
        this.duration = duration;
        this.currentTime = currentTime;
        start();
    }

    public Cooldown(double duration){
        this(duration, CURRENT_TIME);
    }

    public Cooldown(){
        this(DURATION);
    }

    public double getDuration(){ return duration; }
    public double getCurrentTime(){ return currentTime; }

    public boolean isValid(){
        if(currentTime >= duration){
            stop();
            return true;
        }
        return false;
    }

    public void set(double duration){
        this.duration = duration;
        start();
    }

    public void add(double duration){
        this.duration += duration;
        start();
    }

    public void start(){
        if(!COOLDOWNS.contains(this)) COOLDOWNS.add(this);
    }

    public void stop(){
        if(COOLDOWNS.contains(this)) COOLDOWNS.remove(this);
    }

    public static void update(){
        for(Cooldown c : COOLDOWNS){
            c.currentTime += Time.getDeltaTime();
        }
    }

    public String toString(){
        return getClass().getSimpleName() + "[duration:" + duration + ", currentTime:" + currentTime + "]";
    }
}
