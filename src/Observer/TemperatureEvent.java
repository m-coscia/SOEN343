package src.Observer;

import src.logic.Profile;
import src.logic.SimulationParameter;

import java.time.LocalDate;

public class TemperatureEvent extends Event{

    private SimulationParameter parameter;

    public TemperatureEvent(String type, SimulationParameter param){
        super(type);
        parameter = param;
    }

    public SimulationParameter getParam() {
        return parameter;
    }
}
