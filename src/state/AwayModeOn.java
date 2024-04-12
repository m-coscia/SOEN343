package src.state;

import src.Observer.Events.DoorEvent;
import src.Observer.Events.WindowEvent;
import src.commands.CloseDoorsCommand;
import src.commands.CloseWindowsCommand;
import src.components.Room;
import src.logic.Profile;

import java.io.IOException;
import java.util.ArrayList;

public class AwayModeOn implements State {
    SHP shp;

    public AwayModeOn(SHP shp) {
        this.shp = shp;
    }

    // TO BE IMPLEMENTED
    @Override
    public void openDoors(ArrayList<Room> rooms, Profile profile) {

    }

    @Override
    public void closeDoors(ArrayList<Room> rooms, Profile profile) {
        // close every door of layout
        for (Room room : rooms) {
            room.setCommand(new CloseDoorsCommand(room.getDoors(), room.getUsers(), profile));
            try {
                room.executeCommand();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // create doorEvent
        String event = "Away Mode - All doors are closed";
        System.out.println(event);
        shp.setDoorEvent(new DoorEvent("doorEvent", event));

        // notify observers
        try {
            shp.notifyConsoleOutputObserver(shp.getDoorEvent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openWindows(ArrayList<Room> rooms, Profile profile) {
        // close every door of layout
        for (Room room : rooms) {
            room.setCommand(new CloseWindowsCommand(room.getWindows(), room.getUsers(), profile));
            try {
                room.executeCommand();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // create windowEvent
        String event = "Away Mode - All windows are closed";
        System.out.println(event);
        shp.setWindowEvent(new WindowEvent("doorEvent", event));

        // notify observers
        try {
            shp.notifyConsoleOutputObserver(shp.getWindowEvent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeWindows(ArrayList<Room> rooms, Profile profile) {

    }
}
