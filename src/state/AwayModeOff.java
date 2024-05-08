package src.state;

import src.components.Room;
import src.logic.Profile;

import java.util.ArrayList;

public class AwayModeOff implements State {
    SHP shp;

    public AwayModeOff(SHP shp) {
        this.shp = shp;
    }

    // TO BE IMPLEMENTED
    @Override
    public void openDoors(ArrayList<Room> rooms, Profile profile) {

    }

    @Override
    public void closeDoors(ArrayList<Room> rooms, Profile profile) {

    }

    @Override
    public void openWindows(ArrayList<Room> rooms, Profile profile) {

    }

    @Override
    public void closeWindows(ArrayList<Room> rooms, Profile profile) {

    }

    @Override
    public void checkIsOpen(ArrayList<Room> rooms, Profile profile) {
    }

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
