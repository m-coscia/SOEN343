package src.components;

public class Lights {
    private boolean switchedOn = false;
    private IconType icon;
    
    public void switchLightsOn() {
        switchedOn = true;
    }

    public void switchLightsOff() {
        switchedOn = false;
    }
}
