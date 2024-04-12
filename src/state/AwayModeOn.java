package src.state;

import src.Observer.Events.ActionEvent;
import src.Observer.Events.DoorEvent;
import src.Observer.Events.WindowEvent;
import src.Observer.Events.Event;
import src.Observer.Events.TemperatureEvent;
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
    public void checkTemp(ArrayList<Room> rooms, Profile profile) {
        // check if any room is above 135C degrees
        for (Room room : rooms) {
            if (room.getTemperature() > 135) {
                // if any room is above 135C degrees, notify user with temp event
                String eventString = "Away Mode - A room is above 135C degrees";
                System.out.println(eventString);
                Event event = new TemperatureEvent("tempEvent", eventString);

                try {
                    shp.notifyConsoleOutputObserver(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

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
        String eventString = "Away Mode - All doors are closed";
        System.out.println(eventString);
        Event event = new DoorEvent("doorEvent", eventString);

        // notify observers
        try {
            shp.notifyConsoleOutputObserver(event);
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
        String eventString = "Away Mode - All windows are closed";
        System.out.println(eventString);

        // notify observers
        try {
            Event event = new WindowEvent("windowEvent", eventString);
            shp.notifyConsoleOutputObserver(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeWindows(ArrayList<Room> rooms, Profile profile) {

    }

    @Override
    public void checkIsOpen(ArrayList<Room> rooms, Profile profile) {
        // check if any doors is open
        for (Room room : rooms) {
            if (room.getDoors().isOpen()) {
                // if any door is open, notify user with door event
                String eventString = "Away Mode - A door is open";
                System.out.println(eventString);
                Event event = new DoorEvent("doorEvent", eventString);

                try {
                    shp.notifyConsoleOutputObserver(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (room.getWindows().isOpen()) {
                // if any window is open, notify user with window event
                String eventString = "Away Mode - A window is open";
                System.out.println(eventString);
                Event event = new WindowEvent("windowEvent", eventString);

                try {
                    shp.notifyConsoleOutputObserver(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public void checkForMotion(ArrayList<Room> rooms, Profile profile){
        for (Room room : rooms) {
            if (room.getMotionDetected()) {

                // Send notification of motion detected
                motionNotification();

                // Delay contacting authorities
                int delay = this.shp.getAlertResponseTime();
                try {
                    Thread.sleep(delay * 1000); // Convert seconds to milliseconds
                    System.out.println("Authorities contacted " + delay + " seconds after motion detected.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    // Sending notification when motion is detected
    private void motionNotification(){
        // if motion detected in any room
        String eventString = "Away Mode - Motion Detected";
        System.out.println(eventString);
        Event event = new ActionEvent("motionEvent", eventString);
        try{
            shp.notifyConsoleOutputObserver(event);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
