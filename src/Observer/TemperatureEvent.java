package src.Observer;

import src.logic.Profile;

import java.time.LocalDate;

public class TemperatureEvent extends Event{

    private LocalDate date;

    public TemperatureEvent(String type, LocalDate d){
        super(type);
        date = d;
    }

    public LocalDate getDate() {
        return date;
    }
}
