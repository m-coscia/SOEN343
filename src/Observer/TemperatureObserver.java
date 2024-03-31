package src.Observer;

import src.components.*;
import src.logic.*;
import java.io.IOException;
import java.util.ArrayList;

public class TemperatureObserver implements Observer {

    @Override
    public void update(Event event) throws IOException {
        String eventType = event.getType();
        switch(eventType){
            case "ShutdownAC":
                shutdownAC_condition((TempEvent) event);
                break;
            case "OpenWindows":
                openWindows_condition((TempEvent) event);
                break;
        }

    }

    public void shutdownAC_condition(TempEvent e){
        if(e.getOutdoorTemp() < e.getIndoorTemp() && e.getIsSummer()==true){
            AC ac = e.getSimulationParameter().getCooler();
            ac.setIsCooling(false);
        }
    }

    public void openWindows_condition(TempEvent e){
        if(e.getOutdoorTemp() < e.getIndoorTemp() && e.getIsSummer()==true){
            ArrayList<Room> rooms = e.getSimulationParameter().getLayout().getRooms();
            for(Room r : rooms){
                Windows windows = r.getWindows();
                if(!windows.getObstructed()) windows.openWindowsCommand();
            }
        }
    }
}
