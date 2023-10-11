package engine.graphics;

import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import application.Launcher;
import engine.input.Input;

public class WindowComponent implements ComponentListener{
    private Input input;

    public WindowComponent(){
        this.input = new Input();
    }

    public Input getInput(){
        return input;
    }
    
    @Override public void componentHidden(ComponentEvent e) {}
    @Override public void componentMoved(ComponentEvent e) {}
    @Override public void componentResized(ComponentEvent e) {
        Component c = e.getComponent();
        Launcher.setWindowSize(c.getWidth(), c.getHeight());
    }
    @Override public void componentShown(ComponentEvent e) {}
}
