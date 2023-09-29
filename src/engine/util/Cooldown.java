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

    public void plusDeltaTime(){ this.currentTime += Time.getDeltaTime(); }

    public boolean isValid(){
        if(currentTime >= duration) return stop();
        return false;
    }

    public void set(double duration){
        this.currentTime = 0;
        this.duration = duration;
    }

    public void add(double duration){
        this.currentTime = 0;
        this.duration += duration;
    }

    public boolean start(){
        if(!COOLDOWNS.contains(this)) return COOLDOWNS.add(this);
        return false;
    }

    public boolean stop(){
        if(COOLDOWNS.contains(this)) return COOLDOWNS.remove(this);
        return false;
    }

    public static void update(){
        for(Cooldown c : COOLDOWNS){
            c.plusDeltaTime();
        }
    }

    public String toString(){
        return getClass().getSimpleName() + "[duration:" + duration + ", currentTime:" + currentTime + "]";
    }
}
