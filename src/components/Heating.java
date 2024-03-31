package src.components;

import src.logic.SimulationParameter;

public class Heating{
    boolean isHeating;

    public Heating(){
        isHeating = false;
    }

    public boolean getIsHeating() {
        return isHeating;
    }

    public void setIsHeating(boolean h){
        this.isHeating = h;
    }
}