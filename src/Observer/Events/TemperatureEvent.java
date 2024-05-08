package src.Observer.Events;

import src.logic.SimulationParameter;

public class TemperatureEvent extends Event {
    private String eventType;

    public TemperatureEvent(String type, String eventType) {
        super(type);
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }
}
