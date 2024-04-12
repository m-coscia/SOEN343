package src.Observer;

import src.Observer.Events.Event;
import src.logic.SimulationParameter;
import java.time.LocalDate;

public class TempEvent extends Event{
    private double indoorTemp, outdoorTemp;
    private boolean isSummer;

    private SimulationParameter simulationParameter;

    public TempEvent(String type, SimulationParameter sp) {
        super(type);
        this.indoorTemp = sp.getWeatherInside();
        this.outdoorTemp = sp.getWeatherOutside();
        this.simulationParameter = sp;

        String date = sp.getDate().toString();
        int month = Integer.parseInt(date.substring(5,7));
        if(month >= 6 && month <=9) isSummer = true;
        else isSummer = false;
    }

    public double getIndoorTemp() {
        return indoorTemp;
    }

    public double getOutdoorTemp() {
        return outdoorTemp;
    }

    public boolean getIsSummer() {
        return isSummer;
    }


    public SimulationParameter getSimulationParameter() {
        return simulationParameter;
    }
}
