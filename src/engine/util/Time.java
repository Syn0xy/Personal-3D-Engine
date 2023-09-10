package engine.util;

public class Time {
    public final static int FIXED_TIME_PER_SECOND = 5;
    public final static double FIXED_TIME = 1 / (double)FIXED_TIME_PER_SECOND;

    private static double deltaTime;
    private static double oldDeltaTime = 0;

    public static double getDeltaTime(){
        return deltaTime;
    }

    public static void update(){
        long time = System.currentTimeMillis();
        deltaTime = (time - oldDeltaTime) / 1000;
        oldDeltaTime = time;
    }
}
