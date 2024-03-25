package src.Observer;

import src.logic.SimulationParameter;

public class TimeEvent extends Event{

    private SimulationParameter parameter;

    public TimeEvent(String type, SimulationParameter param){
        super(type);
        parameter = param;
    }

    public SimulationParameter getParam() {
        return parameter;
    }
}
