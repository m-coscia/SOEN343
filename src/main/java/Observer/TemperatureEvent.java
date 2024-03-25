package Observer;

import logic.Profile;
import logic.SimulationParameter;

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
