package src.Observer;

import src.Observer.Events.Event;
import src.components.AC;
import src.components.Room;
import src.components.Windows;

import java.io.IOException;
import java.util.ArrayList;

public class TemperatureObserver implements Observer {


    @Override
    public void update(Event event) throws IOException {
        String eventType = event.getType();
        switch(eventType){
            case "ShutdownAC":
                //shutdownAC_condition((Event) event);
                break;
            case "OpenWindows":
                //openWindows_condition((TempEvent) event);
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
