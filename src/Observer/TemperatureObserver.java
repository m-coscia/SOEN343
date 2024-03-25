package src.Observer;

import src.components.*;
import src.logic.*;
import java.io.IOException;

public class TemperatureObserver implements Observer {

    @Override
    public void update(Event event) throws IOException {
        String eventType = event.getType();
        switch(eventType){
            case "ShutdownAC":

        }

    }

    public void shutdownAC(AC cooling){

    }
}
