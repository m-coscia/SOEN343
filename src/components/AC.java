package src.components;

import src.logic.SimulationParameter;

public class AC{
    boolean isCooling;

    public AC(){
        isCooling = false;
    }

    public boolean getisCooling() {
        return isCooling;
    }

    public void setIsCooling(boolean c){
        this.isCooling = c;
    }
}