package src.components;

public class Lights {
    private boolean switchedOn = false;
    private IconType icon;
    private boolean isAutoMode = false;

    public Lights() {
        switchedOn = false;
        isAutoMode = false;
    }
    
    public void switchLightsOn() {
        switchedOn = true;
    }

    public void switchLightsOff() {
        switchedOn = false;
    }

    public boolean isSwitchedOn() {
        return this.switchedOn;
    }

    public boolean getIsAutoMode() {
        return isAutoMode;
    }

    public void setIsAutoMode(boolean isAutoMode) {
        this.isAutoMode = isAutoMode;
    }
}
